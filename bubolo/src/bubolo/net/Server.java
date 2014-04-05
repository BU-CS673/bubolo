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

/**
 * The game server.
 * 
 * @author BU CS673 - Clone Productions
 */
class Server implements NetworkSubsystem
{
	private ServerSocket socket;

	// The list of connected clients.
	private List<Socket> clients;

	private ObjectOutputStream clientStream;
	
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
	 * @param clientCount
	 *            the number of clients to wait for until the game beings.
	 * 
	 * @throws NetworkException
	 *             if a network error occurs.
	 */
	void startServer(int clientCount) throws NetworkException
	{
		try
		{
			socket = new ServerSocket(NetworkInformation.GAME_PORT);
			clients = new ArrayList<Socket>(clientCount);
			
			for (int i = 0; i < clientCount; ++i)
			{
				// TODO (cdc - 3/23/2013): Move accept() into a different thread.
				clients.add(socket.accept());
				clients.get(i).setTcpNoDelay(true);
	
				clientStream = new ObjectOutputStream(clients.get(i).getOutputStream());
	
				// Start the network reader thread.
				new Thread(this).start();
			}
		}
		catch (IOException e)
		{
			throw new NetworkException(e);
		}
	}

	@Override
	public void send(NetworkCommand command)
	{
		sender.execute(new NetworkSender(clientStream, command));
	}

	@Override
	public void dispose()
	{
		shutdown.set(true);
	}

	private static class ClientReader implements Runnable
	{
		@Override
		public void run()
		{
			if (clients == null)
			{
				throw new IllegalStateException(
						"Unable to run server; the network system has not been started.");
			}
	
			try (ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream()))
			{
				while (!shutdown.get())
				{
					NetworkCommand command = (NetworkCommand)inputStream.readObject();
					network.postToGameThread(command);
				}
			}
			catch (IOException | ClassNotFoundException e)
			{
				// TODO: Pass this exception to the primary thread, and eliminate the stack track.
				e.printStackTrace();
				throw new NetworkException(e);
			}
			finally
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
}
