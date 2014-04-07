/**
 *
 */

package bubolo.net;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;

import bubolo.world.World;

/**
 * @author BU CS673 - Clone Productions
 */
public class MockNetwork implements Network
{
	ObjectOutputStream oos = null;

	MockNetwork()
	{
		try
		{
			oos = new ObjectOutputStream(System.out);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public void connect(InetAddress serverIpAddress) throws NetworkException, IllegalStateException
	{
	}

	@Override
	public void startDebug()
	{
	}

	@Override
	public void send(NetworkCommand command)
	{
	}

	@Override
	public void update(World world)
	{
	}

	@Override
	public void postToGameThread(NetworkCommand command)
	{
	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void startServer() throws NetworkException, IllegalStateException
	{
	}

	@Override
	public void startGame(World world)
	{
	}
}
