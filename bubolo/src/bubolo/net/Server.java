/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import bubolo.net.command.SendMap;
import bubolo.net.command.StartGame;
import bubolo.world.World;

import com.badlogic.gdx.Gdx;

/**
 * The game server.
 * 
 * @author BU CS673 - Clone Productions
 */
class Server implements NetworkSubsystem
{
	private ServerSocket socket;

	// The list of connected clients.
	private List<ClientSocket> clients;

	// Specifies whether the server has shut down.
	private AtomicBoolean shutdown = new AtomicBoolean(false);

	// Specifies whether the game has started.
	private AtomicBoolean gameStarted = new AtomicBoolean(false);

	private final Executor sender;

	// Reference to the network system.
	private final Network network;

	private Thread clientAcceptor;

	/**
	 * Constructs a Server object.
	 * 
	 * @param network
	 *            reference to the network system.
	 */
	Server(Network network)
	{
		this.network = network;
		this.sender = Executors.newSingleThreadExecutor();
	}

	/**
	 * Identifies this player as the game server, and begins accepting connections from other
	 * players. <code>startServer</code> must be called before calling <code>connect</code>. There
	 * should only be one game server per game.
	 * 
	 * @throws NetworkException
	 *             if a network error occurs.
	 */
	void startServer() throws NetworkException
	{
		try
		{
			socket = new ServerSocket(NetworkInformation.GAME_PORT);
			clients = new CopyOnWriteArrayList<ClientSocket>();

			clientAcceptor = new Thread(
					new ClientAcceptor(shutdown, gameStarted, clients, socket, this, network));
		}
		catch (IOException e)
		{
			throw new NetworkException(e);
		}
	}

	/**
	 * Notifies clients that the game is ready to start.
	 * 
	 * @param world
	 *            reference to the game world.
	 */
	void startGame(World world)
	{
		gameStarted.set(true);
		clientAcceptor.interrupt();

		StartGame startGameCommand = new StartGame(new SendMap(world));
		send(startGameCommand);
	}

	@Override
	public void send(NetworkCommand command)
	{
		send(command, null);
	}

	@Override
	public void dispose()
	{
		shutdown.set(true);
	}

	/**
	 * Removes the client
	 * 
	 * @param client
	 */
	void removeClient(ClientSocket client)
	{
		client.dispose();
		clients.remove(client);
	}

	/**
	 * Queues a network command to be sent to the other players.
	 * 
	 * @param command
	 *            the network command to send.
	 * @param clientToIgnore
	 *            the client to ignore (i.e., the client that will not receive the command), or null
	 *            if all clients should receive the command.
	 */
	void send(NetworkCommand command, ClientSocket clientToIgnore)
	{
		for (ClientSocket client : clients)
		{
			if ((clientToIgnore == null) || (clientToIgnore != null && client != clientToIgnore))
			{
				sender.execute(new NetworkSender(client.getOutputStream(), command));
			}
		}
	}

	/**
	 * A client connection acceptor.
	 * 
	 * @author BU CS673 - Clone Productions
	 */
	private static class ClientAcceptor implements Runnable
	{
		private final AtomicBoolean shutdown;
		private final AtomicBoolean gameStarted;
		private final List<ClientSocket> clients;
		private final ServerSocket socket;
		private final Server server;
		private final Network network;

		/**
		 * Constructs a client connection acceptor.
		 * 
		 * @param shutdown
		 *            reference to the shutdown atomic boolean.
		 * @param gameStarted
		 *            reference to the gameStarted atomic boolean.
		 * @param clients
		 *            the instantiated list of clients.
		 * @param socket
		 *            the server socket.
		 * @param server
		 *            reference to the server.
		 * @param network
		 *            reference to the network system.
		 */
		ClientAcceptor(AtomicBoolean shutdown, AtomicBoolean gameStarted,
				List<ClientSocket> clients, ServerSocket socket, Server server, Network network)
		{
			this.shutdown = shutdown;
			this.gameStarted = gameStarted;
			this.clients = clients;
			this.socket = socket;
			this.server = server;
			this.network = network;
		}

		@Override
		public void run()
		{
			int clientCount = 0;
			// Continue accepting connections until the network has been shut down, the game has
			// been started, or this thread has received an interrupt.
			while (!shutdown.get() && !gameStarted.get() && !Thread.interrupted())
			{
				try
				{
					ClientSocket clientSocket = new ClientSocket(socket.accept());
					if (!shutdown.get() && !gameStarted.get() && !Thread.interrupted())
					{
						clients.add(clientSocket);
						clients.get(clientCount).getClient().setTcpNoDelay(true);
					}
					else
					{
						clientSocket.dispose();
					}
				}
				catch (IOException e)
				{
					throw new NetworkException(e);
					// TODO (cdc - 4/7/2014): Pass this to the main thread.
				}

				// Start the network reader thread.
				new Thread(new ClientReader(
						clients.get(clientCount), server, network, shutdown)).start();
				++clientCount;
			}
		}
	}

	/**
	 * Reads data from client connections.
	 * 
	 * @author BU CS673 - Clone Productions
	 */
	private static class ClientReader implements Runnable
	{
		private final AtomicBoolean shutdown;
		private final Network network;
		private final ClientSocket client;
		private final Server server;

		/**
		 * Constructs a new ClientReader.
		 * 
		 * @param client
		 *            the connected ClientSocket object.
		 * @param server
		 *            the Server object.
		 * @param network
		 *            reference to the network system.
		 * @param shutdown
		 *            reference to the server's shutdown object.
		 */
		ClientReader(ClientSocket client, Server server, Network network, AtomicBoolean shutdown)
		{
			if (client == null)
			{
				throw new IllegalStateException(
						"Unable to run server; the network system has not been started.");
			}

			this.client = client;
			this.network = network;
			this.shutdown = shutdown;
			this.server = server;
		}

		@Override
		public void run()
		{
			try (ObjectInputStream inputStream = new ObjectInputStream(
					client.getClient().getInputStream()))
			{
				while (!shutdown.get())
				{
					NetworkCommand command = (NetworkCommand)inputStream.readObject();
					server.send(command, client);
					network.postToGameThread(command);
				}
			}
			catch (IOException | ClassNotFoundException e)
			{
				// TODO: Pass this exception to the primary thread, and eliminate the
				// stack track.
				e.printStackTrace();
				throw new NetworkException(e);
			}
			finally
			{
				class RemoveClient implements Runnable
				{
					private final Server serverSocket;
					private final ClientSocket clientSocket;

					RemoveClient(Server server, ClientSocket client)
					{
						this.clientSocket = client;
						this.serverSocket = server;
					}

					@Override
					public void run()
					{
						serverSocket.removeClient(clientSocket);
					}
				}

				Gdx.app.postRunnable(new RemoveClient(server, client));
			}
		}
	}

	/**
	 * Wraps a client Socket connection and a client stream.
	 * 
	 * @author BU CS673 - Clone Productions
	 */
	private static class ClientSocket
	{
		private final Socket client;
		private final ObjectOutputStream clientStream;

		/**
		 * Constructs a new ClientSocket.
		 * 
		 * @param client
		 *            the connected client Socket object.
		 */
		ClientSocket(Socket client)
		{
			this.client = client;
			try
			{
				this.clientStream = new ObjectOutputStream(client.getOutputStream());
			}
			catch (IOException e)
			{
				throw new NetworkException(e);
			}
		}

		/**
		 * Returns a reference to the underlying client Socket.
		 * 
		 * @return a reference to the underlying client Socket.
		 */
		Socket getClient()
		{
			return client;
		}

		/**
		 * Returns the client's object output stream.
		 * 
		 * @return the client's object output stream.
		 */
		ObjectOutputStream getOutputStream()
		{
			return clientStream;
		}

		/**
		 * Closes the connection.
		 */
		void dispose()
		{
			try
			{
				clientStream.close();
			}
			catch (IOException e)
			{
			}
		}
	}
}
