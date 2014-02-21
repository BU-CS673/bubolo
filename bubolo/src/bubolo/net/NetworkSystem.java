package bubolo.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

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
	
	private AtomicBoolean isActive;
	
	
	@Override
	public boolean isActive()
	{
		return isActive.get();
	}

	@Override
	public void shutdown()
	{
		isActive.set(false);
		if (server != null)
		{
			try
			{
				server.close();
			}
			catch (IOException e)
			{
				// TODO: does this need to be handled?
			}
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
		private ServerSocket serverSocket;
		private Network network;
		
		private Executor executor = Executors.newCachedThreadPool();
		
		/**
		 * Constructs a new ServerRunnable object.
		 * @param networkSystem reference to the network system.
		 * @param server reference to an instantiated server socket.
		 */
		ServerRunnable(Network networkSystem, ServerSocket server)
		{
			this.serverSocket = server;
			this.network = networkSystem;
		}
		
		@Override
		public void run()
		{
			while (network.isActive())
			{
				try
				{
					executor.execute(new ClientProcessor(this.network, serverSocket.accept()));
				}
				catch (IOException e)
				{
					// TODO: does this need to be handled?
				}
			}
		}
	}
	
	
	/**
	 * Processes client requests, and sends data to a client.
	 * @author BU CS673 - Clone Productions
	 */
	private static class ClientProcessor implements Runnable
	{
		private Network network;
		private Socket socket;
		
		private AtomicBoolean isActive;
		
		ClientProcessor(Network network, Socket socket) throws SocketException
		{
			this.network = network;
			this.socket = socket;
			socket.setTcpNoDelay(true);
			this.isActive = new AtomicBoolean();
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
			while (network.isActive())
			{
				// TODO: accept data from the client, and send data to it.

			}
		
			try
			{
				socket.close();
			}
			catch (IOException e)
			{
				// TODO: does this need to be handled?
			}
			
			isActive.set(false);
		}
	}
}
