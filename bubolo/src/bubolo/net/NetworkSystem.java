package bubolo.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

import bubolo.world.World;

/**
 * Concrete implementation of the network system.
 * @author BU CS673 - Clone Productions
 */
public class NetworkSystem implements Network
{	
	private boolean isServer;
	private boolean isClient;
	
	private ServerSocket server;
	
	private Thread serverThread;
	
	private boolean isActive;
	
	
	@Override
	public boolean isActive()
	{
		return isActive;
	}

	@Override
	public void shutdown()
	{
		isActive = false;
		try
		{
			server.close();
		}
		catch (IOException e)
		{
			// TODO: does this need to be handled?
		}
	}
	
	@Override
	public void connect(InetSocketAddress serverIpAddress) throws IllegalStateException,
			NetworkException
	{
		if (isServer)
		{
			throw new IllegalStateException(
					"connect was called after calling startServer. A player can be a client or a server, but not both.");
		}
		isClient = true;
		
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void startServer() throws IllegalStateException, NetworkException
	{
		if (isClient)
		{
			throw new IllegalStateException(
					"startServer was called after calling connect. A player can be a client or a server, but not both.");
		}
		isServer = true;
		
		try
		{
			server = new ServerSocket(NetworkInformation.GAME_PORT);
			serverThread = new Thread(new ServerRunnable(this, server));
			serverThread.start();
		}
		catch (IOException e)
		{
			throw new NetworkException(e);
		}
	}

	@Override
	public void send(NetworkCommand command)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void update(World world)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	
	/**
	 * Runnable that accepts client connection.
	 * @author BU CS673 - Clone Productions
	 */
	private class ServerRunnable implements Runnable
	{
		private ServerSocket server;
		private Network network;
		
		private 
		
		/**
		 * Constructs a new ServerRunnable object.
		 * @param networkSystem reference to the network system.
		 * @param server reference to an instantiated server socket.
		 */
		ServerRunnable(Network networkSystem, ServerSocket server)
		{
			this.server = server;
			this.network = networkSystem;
		}
		
		@Override
		public void run()
		{
			while (network.isActive())
			{
				try
				{
					server.accept();
				}
				catch (IOException e)
				{
					// TODO: does this need to be handled?
				}
			}
		}
	}
}
