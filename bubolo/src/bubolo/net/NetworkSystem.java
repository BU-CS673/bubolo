package bubolo.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import bubolo.world.World;

/**
 * Concrete implementation of the network system.
 * @author BU CS673 - Clone Productions
 */
public class NetworkSystem implements Network
{	
	private boolean isServer;
	
	private ServerSocket serverSocket;
	
	private Connections connections;
	
	private AtomicBoolean isActive = new AtomicBoolean(true);
	
	// Queue of commands that should be run in the game logic thread.
	private Queue<NetworkCommand> postedCommands = new ConcurrentLinkedQueue<NetworkCommand>();
	
	// The last time the network system was updated, in milliseconds.
	private long lastUpdateMillis = 0;
	
	// Volatile to eliminate FindBugs warning: "This method contains an unsynchronized lazy initialization of a non-volatile static field. 
	// Because the compiler or processor may reorder instructions, threads are not guaranteed to see a completely initialized object"
	private static volatile NetworkSystem instance = null;
	
	/**
	 * Returns the Network instance.
	 * @return the Network instance.
	 */
	public static NetworkSystem getInstance()
	{
		if (instance == null)
		{
			instance = new NetworkSystem();
		}
		return instance;
	}
	
	/**
	 * Resets the instance variable.
	 */
	private static void resetInstance()
	{
		instance = null;
	}
	
	/**
	 * Private constructor since only one network instance can be created.
	 */
	private NetworkSystem()
	{
	}
	
	@Override
	public boolean isActive()
	{
		return isActive.get();
	}
	
	@Override
	public boolean isGameServer()
	{
		return isServer;
	}

	@Override
	public void destroy()
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
	public void connect(InetAddress serverIpAddress) throws IllegalStateException,
			NetworkException
	{
		if (connections == null)
		{
			throw new IllegalStateException("startServer must be called before calling connect.");
		}
		
		class Connector implements Runnable
		{
			private InetAddress ipAddress;
			private Connections networkConnections;
			
			Connector(InetAddress ipAddress, Connections connections)
			{
				this.ipAddress = ipAddress;
				this.networkConnections = connections;
			}

			@Override
			public void run()
			{
				try
				{
					networkConnections.addConnection(new Socket(ipAddress, NetworkInformation.GAME_PORT));
				}
				catch (IOException e)
				{
					// TODO: Need a way to return this error.
					throw new NetworkException(e);
				}
			}
		}
		
		new Thread(new Connector(serverIpAddress, connections)).start();;
	}

	@Override
	public void startServer(boolean isGameServer) throws IllegalStateException, NetworkException
	{
		if (connections != null)
		{
			throw new IllegalStateException("The server was already started.");
		}
		isServer = isGameServer;
		
		try
		{
			serverSocket = new ServerSocket(NetworkInformation.GAME_PORT);
			connections = new Connections(this, serverSocket);
			new Thread(connections).start();
		}
		catch (IOException e)
		{
			throw new NetworkException(e);
		}
	}

	@Override
	public void send(NetworkCommand command)
	{
		connections.addCommand(command);
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
		
		// The network system runs less frequently than the game logic.
		long differenceMillis = System.currentTimeMillis() - lastUpdateMillis - NetworkInformation.MILLIS_PER_TICK;
		if (differenceMillis >= 0)
		{
			// Send queued commands to servers.
			connections.update();
		}
		lastUpdateMillis = System.currentTimeMillis();
	}
	
	@Override
	public void postToGameThread(NetworkCommand command)
	{
		postedCommands.add(command);
	}

	@Override
	public void reset()
	{
		destroy();
		resetInstance();
	}
}
