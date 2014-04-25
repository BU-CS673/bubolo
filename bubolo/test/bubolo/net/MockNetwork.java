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
	public void connect(InetAddress serverIpAddress, String playerName) throws NetworkException, IllegalStateException
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
	public void startServer(String playerName) throws NetworkException, IllegalStateException
	{
	}

	@Override
	public void startGame(World world)
	{
	}

	@Override
	public void addObserver(NetworkObserver observer)
	{
	}

	@Override
	public void removeObserver(NetworkObserver observer)
	{
	}

	@Override
	public NetworkObserverNotifier getNotifier()
	{
		return null;
	}

	@Override
	public boolean isServer()
	{
		// TODO Auto-generated method stub
		return false;
	}
}
