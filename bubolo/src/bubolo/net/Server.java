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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.common.base.Preconditions;

/**
 * The game server.
 * 
 * @author BU CS673 - Clone Productions
 */
class Server implements NetworkSubsystem, Runnable
{
	private ServerSocket socket;

	// TODO (cdc - 3/23/2014): Expand this to allow multiple connections.
	private Socket client;

	// Specifies whether the server has shut down.
	private AtomicBoolean shutdown = new AtomicBoolean(false);

	private final Executor sender;

	// Reference to the network system.
	private final Network network;
	
	private ObjectOutputStream clientStream;

	/**
	 * Constructs a Server object.
	 * 
	 * @param network
	 *            reference to the network system.
	 */
	Server(Network network)
	{
		this.network = network;
		this.sender = Executors.newCachedThreadPool();
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

			// TODO (cdc - 3/23/2013): Move accept() into a different thread.
			client = socket.accept();
			client.setTcpNoDelay(true);
			
			clientStream = new ObjectOutputStream(client.getOutputStream());

			// Start the network reader thread.
			new Thread(this).start();
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

	@Override
	public void run()
	{
		Preconditions.checkState(client != null,
				"Unable to run server; the network system has not been started.");
		
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
