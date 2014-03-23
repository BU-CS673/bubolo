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
	private Queue<NetworkCommand> postedCommands = new ConcurrentLinkedQueue<NetworkCommand>();
	
	// Specifies whether the network system is running in debug mode.
	private boolean debug = false;

	private static volatile Network instance;
	
	/**
	 * Returns the network system instance.
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
	}
	
	@Override
	public void startServer() throws NetworkException, IllegalStateException
	{
		checkState(subsystem == null, "The network system has already been started. " +
				"Do not call startServer or connect more than once.");

		Server server = new Server(this);
		server.startServer();
		subsystem = server;
	}

	@Override
	public void connect(InetAddress serverIpAddress) throws NetworkException
	{
		checkState(subsystem == null, "The network system has already been started. " +
				"Do not call startServer or connect more than once.");

		Client client = new Client(this);
		client.connect(serverIpAddress);
		subsystem = client;
	}
	
	@Override
	public void startDebug()
	{
		debug = true;
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
	public void postToGameThread(NetworkCommand command)
	{
		postedCommands.add(command);
	}

	@Override
	public void dispose()
	{
		subsystem.dispose();
		subsystem = null;
	}
}
