package bubolo.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
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
	
	private ServerSocket serverSocket;
	
	private Server server;
	
	private AtomicBoolean isActive = new AtomicBoolean(true);
	
	// Queue of commands that should be run in the game logic thread.
	private Queue<NetworkCommand> postedCommands = new ConcurrentLinkedQueue<NetworkCommand>();
	
	
	@Override
	public boolean isActive()
	{
		return isActive.get();
	}

	@Override
	public void shutdown()
	{
		isActive.set(false);
		if (serverSocket != null)
		{
			try
			{
				serverSocket.close();
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
			serverSocket = new ServerSocket(NetworkInformation.GAME_PORT);
			server = new Server(this, serverSocket);
			new Thread(server).start();
		}
		catch (IOException e)
		{
			throw new NetworkException(e);
		}
	}

	@Override
	public void send(NetworkCommand command)
	{
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	@Override
	public void update(World world)
	{
		// Execute all posted commands in the game logic thread.
		NetworkCommand c = null;
		while ((c = postedCommands.poll()) != null)
		{
			c.execute(world);
		}
		
		// TODO: remove this once NetworkSystem.update is implemented.
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	@Override
	public void postToGameThread(NetworkCommand command)
	{
		postedCommands.add(command);
	}
	
	
	/**
	 * Runnable that accepts client connection.
	 * @author BU CS673 - Clone Productions
	 */
	private static class Server implements Runnable
	{
		private ServerSocket serverSocket;
		private Network network;
		
		private Queue<ClientProcessor> clients;
		private Executor executor = Executors.newCachedThreadPool();
		
		/**
		 * Constructs a new Server object.
		 * @param networkSystem reference to the network system.
		 * @param server reference to an instantiated server socket.
		 */
		Server(Network networkSystem, ServerSocket server)
		{
			this.serverSocket = server;
			this.network = networkSystem;
		}
		
		/**
		 * Sends a network command to the other players.
		 * @param command the network command to send.
		 */
		public void send(NetworkCommand command)
		{
			for (ClientProcessor c : clients)
			{
				if (c.isActive())
				{
					c.send(command);
				}
				// TODO: remove inactive ClientProcessors?
			}
		}
		
		@Override
		public void run()
		{
			while (network.isActive())
			{
				try
				{
					ClientProcessor client = new ClientProcessor(this.network, serverSocket.accept()); 
					clients.add(client);
					executor.execute(client);
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
		
		private Queue<NetworkCommand> commands = new ConcurrentLinkedQueue<NetworkCommand>();
		
		ClientProcessor(Network network, Socket socket) throws SocketException
		{
			this.network = network;
			this.socket = socket;
			socket.setTcpNoDelay(true);
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
			while (network.isActive())
			{
				long startMillis = System.currentTimeMillis();
				// TODO: accept data from the client, and send data to it.
				// 1. Check for data in the queue, and send it if there is.
				// 2. Check for data coming in, and process it if there is.

				long differenceMillis = System.currentTimeMillis() - startMillis;
				if (differenceMillis > 0)
				{
					try
					{
						Thread.sleep(NetworkInformation.MILLIS_PER_TICK - differenceMillis);
					}
					catch (InterruptedException e)
					{
						isActive.set(false);
					}
				}
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
