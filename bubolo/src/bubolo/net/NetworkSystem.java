/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net;

import java.net.InetAddress;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import bubolo.world.World;
import static com.google.common.base.Preconditions.*;

/**
 * The network system implementation.
 * 
 * @author BU CS673 - Clone Productions
 */
public class NetworkSystem implements Network
{
	private NetworkSubsystem subsystem;

	// Queue of commands that should be run in the game logic thread.
	private final Queue<NetworkCommand> postedCommands;
	
	private final NetworkObserverNotifier observerNotifier;

	// Specifies whether the network system is running in debug mode.
	private boolean debug = false;
	
	// The name of the player, which is used when sending messages.
	private String name;
	
	// Specifies whether this is a server player.
	private boolean isServer;

	private static volatile Network instance;

	/**
	 * Returns the network system instance.
	 * 
	 * @return the network system instance.
	 */
	public static Network getInstance()
	{
		if (instance == null)
		{
			instance = new NetworkSystem();
		}
		return instance;
	}

	private NetworkSystem()
	{
		this.postedCommands = new ConcurrentLinkedQueue<NetworkCommand>();
		this.observerNotifier = new NetworkObserverNotifier();
	}
	
	@Override
	public boolean isServer()
	{
		return isServer;
	}

	@Override
	public String getPlayerName()
	{
		return name;
	}
	
	@Override
	public void startServer(String serverName) throws NetworkException, IllegalStateException
	{
		checkState(subsystem == null, "The network system has already been started. " +
				"Do not call startServer or connect more than once.");

		name = serverName;
		isServer = true;
		
		// Don't allow the server to run in debug mode, since it requires external resources.
		// Instead, test this properly in an integration test.
		if (debug)
		{
			return;
		}

		Server server = new Server(this);
		subsystem = server;
		server.startServer(serverName);
	}

	@Override
	public void connect(InetAddress serverIpAddress, String clientName) 
			throws NetworkException, IllegalStateException
	{
		checkState(subsystem == null, "The network system has already been started. " +
				"Do not call startServer or connect more than once.");

		name = clientName;
		isServer = false;
		
		// Don't allow the client to run in debug mode, since it requires external resources.
		// Instead, test this properly in an integration test.
		if (debug)
		{
			return;
		}

		Client client = new Client(this);
		subsystem = client;
		client.connect(serverIpAddress, clientName);
	}

	@Override
	public void startDebug()
	{
		debug = true;
	}
	
	@Override
	public void startGame(World world)
	{
		if (subsystem instanceof Server)
		{
			((Server)subsystem).startGame(world);
		}
	}

	@Override
	public void send(NetworkCommand command)
	{
		// Returns without sending the command if the system is running in debug mode.
		if (debug)
		{
			return;
		}

		// Explicit check rather than a call to checkState, because FindBugs
		// was unable to identify checkState as a valid defense against null pointer dereferencing.
		if (subsystem == null)
		{
			throw new NetworkException("Unable to send command: the network is not initialized.");
		}

		subsystem.send(command);
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
	}

	@Override
	public void addObserver(NetworkObserver o)
	{
		observerNotifier.addObserver(o);
	}

	@Override
	public void removeObserver(NetworkObserver o)
	{
		observerNotifier.removeObserver(o);
	}
	

	@Override
	public NetworkObserverNotifier getNotifier()
	{
		return observerNotifier;
	}
	
	@Override
	public void postToGameThread(NetworkCommand command)
	{
		postedCommands.add(command);
	}

	@Override
	public void dispose()
	{
		if (subsystem != null)
		{
			subsystem.dispose();
		}
		subsystem = null;
		debug = false;
		isServer = false;
		name = null;
		postedCommands.clear();
	}
}
