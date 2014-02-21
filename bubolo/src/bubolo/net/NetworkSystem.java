package bubolo.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
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
	
	private Server server;
	private Client client;
	
	private AtomicBoolean isActive = new AtomicBoolean(true);
	
	// Queue of commands that should be run in the game logic thread.
	private Queue<NetworkCommand> postedCommands = new ConcurrentLinkedQueue<NetworkCommand>();
	
	private static Network instance;
	
	/**
	 * Returns the Network instance.
	 * @return the Network instance.
	 */
	public static Network getInstance()
	{
		if (instance == null)
		{
			instance = new NetworkSystem();
		}
		return instance;
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
	public void connect(InetSocketAddress serverIpAddress) throws IllegalStateException,
			NetworkException
	{
		// TODO: implement connection to server.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void startServer(boolean isGameServer) throws IllegalStateException, NetworkException
	{
		if (server != null)
		{
			throw new IllegalStateException("The server was already started.");
		}
		isServer = isGameServer;
		
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
		server.addCommand(command);
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
		
		// Send queued commands.
		server.update();
	}
	
	@Override
	public void postToGameThread(NetworkCommand command)
	{
		postedCommands.add(command);
	}
}
