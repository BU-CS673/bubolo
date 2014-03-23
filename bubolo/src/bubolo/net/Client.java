/**
 *
 */

package bubolo.net;

import java.net.InetAddress;

import bubolo.world.World;

/**
 * The game client.
 * 
 * @author BU CS673 - Clone Productions
 */
class Client implements NetworkSubsystem
{
	/**
	 * Attempts to connect to the specified IP address.
	 * 
	 * @param serverIpAddress the IP address of a server. Note that this isn't necessarily
	 * the <i>game</i> server, since clients also connect directly to each other.
	 * @throws NetworkException if a network error occurs.
	 */
	void connect(InetAddress serverIpAddress) throws NetworkException
	{
		
	}

	@Override
	public void send(NetworkCommand command)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(World world)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		
	}
}
