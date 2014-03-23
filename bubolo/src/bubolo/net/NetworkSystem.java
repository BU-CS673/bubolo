/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net;

import java.net.InetAddress;

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

		Client client = new Client();
		client.connect(serverIpAddress);
		subsystem = client;
	}

	@Override
	public void send(NetworkCommand command)
	{
		checkState(subsystem != null,
				"The network system has not been started. Call startServer or connect.");

		subsystem.send(command);
	}

	@Override
	public void update(World world)
	{
		
	}

	@Override
	public void postToGameThread(NetworkCommand command)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose()
	{
		subsystem.dispose();
		subsystem = null;
	}
}
