package bubolo.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Receives <code>NetworkCommand</code>s from a connected Socket.
 * @author BU CS673 - Clone Productions
 */
class ConnectionReader implements Runnable
{
	private Network network;
	private Socket socket;
	
	// AtomicBoolean since this may be read by different threads.
	private AtomicBoolean isActive;
	
	// Queue of commands that will be sent across to other players.
	private Queue<NetworkCommand> commands = new ConcurrentLinkedQueue<NetworkCommand>();
	
	/**
	 * Constructs a ConnectionReader object.
	 * @param network reference to the Network.
	 * @param socket the connected Socket.
	 * @throws NetworkException if disabling Nagle's algorithm fails.
	 */
	ConnectionReader(Network network, Socket socket) throws NetworkException
	{
		this.network = network;
		this.socket = socket;
		try
		{
			socket.setTcpNoDelay(true);
		}
		catch (SocketException e)
		{
			throw new NetworkException(e);
		}
		this.isActive = new AtomicBoolean();
	}
	
	/**
	 * Enqueues a command to be sent across the network.
	 * @param command the command that will be sent.
	 */
	void send(NetworkCommand command)
	{
		commands.add(command);
	}
	
	/**
	 * Returns true if the ClientProcessor is active, or false if not.
	 * @return true if the ClientProcessor is active.
	 */
	boolean isActive()
	{
		return isActive.get();
	}
	
	@Override
	public void run()
	{
		try
		{
			ObjectInputStream inputStream;
			try
			{
				inputStream = new ObjectInputStream(socket.getInputStream());
			}
			catch (IOException e)
			{
				throw new NetworkException(e);
			}
			
			while (network.isActive())
			{
				// TODO: accept data from the client, and send data to it.
				// 1. Check for data in the queue, and send it if there is.
				// 2. Check for data coming in, and process it if there is.
				
				try
				{
					NetworkCommand command = (NetworkCommand)inputStream.readObject();
					network.postToGameThread(command);
				}
				catch (ClassNotFoundException | IOException e)
				{
					// TODO This may need to be handled.
					e.printStackTrace();
				}
			}
		}
		finally
		{
			try
			{
				isActive.set(false);
				socket.close();
			}
			catch (IOException e)
			{
				// TODO: does this need to be handled?
			}
		}
	}
}