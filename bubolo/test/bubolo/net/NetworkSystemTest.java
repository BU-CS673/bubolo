package bubolo.net;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.test.MockWorld;
import bubolo.world.World;
import static org.mockito.Mockito.*;


public class NetworkSystemTest
{
	private Network network;
	
	@Before
	public void setup()
	{
		network = NetworkSystem.getInstance();
	}
	
	@After
	public void teardown()
	{
		network.reset();
	}
	
	
	@Test
	public void isActiveTrue()
	{
		assertTrue(network.isActive());
	}
	
	@Test
	public void isActiveFalseAndDestroyTest()
	{
		network.dispose();
		assertFalse(network.isActive());
	}

	@Test
	public void connect()
	{
		network.startServer(false);
		network.connect(mock(InetAddress.class));
	}
	
	@Test
	public void connectInvalid()
	{
		try
		{
			network.connect(mock(InetAddress.class));
			fail("connect should have failed, but did not.");
		}
		catch (IllegalStateException e)
		{
		}
	}

	@Test
	public void startServer()
	{
		network.startServer(true);
	}
	
	@Test
	public void startServerInvalid()
	{
		network.startServer(true);
		try
		{
			network.startServer(true);
			fail("startServer should have failed, but did not.");
		}
		catch (IllegalStateException e)
		{
		}
	}

	@Test
	public void send()
	{
		NetworkCommand command = new NetworkCommand() {
			@Override
			public void execute(World world)
			{	
			}
		};
		network.startServer(false);
		network.send(command);
	}

	@Test
	public void update()
	{
		network.startServer(false);
		network.update(new MockWorld());
	}
	
	@Test
	public void postToGameThread()
	{
		network.postToGameThread(mock(NetworkCommand.class));
	}
}
