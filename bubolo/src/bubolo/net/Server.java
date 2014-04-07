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
import java.util.ArrayList;
import java.util.List;
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

	private final Executor sender;

	// Reference to the network system.
	private final Network network;

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
	 * @param world
	 *            reference to the game world.
	 * @param clientCount
	 *            the number of clients to wait for until the game beings.
	 * 
	 * @throws NetworkException
	 *             if a network error occurs.
	 */
	void startServer(World world, int clientCount) throws NetworkException
	{
		try
		{
			socket = new ServerSocket(NetworkInformation.GAME_PORT);
			clients = new ArrayList<ClientSocket>(clientCount);

			for (int i = 0; i < clientCount; ++i)
			{
				// TODO (cdc - 3/23/2013): Move accept() into a different thread.
				clients.add(new ClientSocket(socket.accept()));
				clients.get(i).getClient().setTcpNoDelay(true);

				// Start the network reader thread.
				new Thread(new ClientReader(clients.get(i), this, network, shutdown)).start();
			}

			StartGame startGameCommand = new StartGame(new SendMap(world));
			send(startGameCommand);
		}
		catch (IOException e)
		{
			throw new NetworkException(e);
		}
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
