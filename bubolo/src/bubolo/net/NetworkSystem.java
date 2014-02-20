package bubolo.net;

import java.net.InetSocketAddress;

import bubolo.world.World;

/**
 * Concrete implementation of the network system.
 * @author BU CS673 - Clone Productions
 */
public class NetworkSystem implements Network
{
	@Override
	public void connect(InetSocketAddress serverIpAddress) throws IllegalStateException,
			NetworkException
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void startServer() throws IllegalStateException
	{
		throw new UnsupportedOperationException("Not yet implemented");
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
}
