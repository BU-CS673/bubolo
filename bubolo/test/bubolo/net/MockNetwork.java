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
	public void startServer() throws NetworkException, IllegalStateException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connect(InetAddress serverIpAddress) throws NetworkException, IllegalStateException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDebug()
	{
		// TODO Auto-generated method stub
		
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
	public void postToGameThread(NetworkCommand command)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		
	}

}
