package bubolo.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
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
		this.isActive = new AtomicBoolean(true);
	}
	
	/**
	 * Returns true if the ClientProcessor is active, or false if not.
	 * @return true if the ClientProcessor is active.
	 */
	boolean isActive()
	{
		return isActive.get();
	}
	
	/**
	 * Returns a reference to the underlying <code>Socket</code>.
	 * @return a reference to the underlying <code>Socket</code>.
	 */
	Socket getSocket()
	{
		return socket;
	}
	
	/**
	 * Shuts down the ConnectionReader object.
	 */
	void destroy()
	{
		isActive.set(false);
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
					throw new NetworkException(e);
				}
			}
		}
		finally
		{
			try
			{
				isActive.set(false);
				if (socket != null)
				{
					socket.close();
				}
			}
			catch (IOException e)
			{
				// TODO: does this need to be handled?
				throw new NetworkException(e);
			}
		}
	}
}