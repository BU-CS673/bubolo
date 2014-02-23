package bubolo.net;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.InetSocketAddress;

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
	
	
	@Test
	public void isActiveTrue()
	{
		assertTrue(network.isActive());
	}
	
	@Test
	public void isActiveFalseAndDestroyTest()
	{
		network.destroy();
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
		network.destroy();
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
		NetworkCommand command = mock(NetworkCommand.class);
		network.send(command);
	}

	@Test
	public void update()
	{
		network.update(mock(World.class));
	}
	
	@Test
	public void postToGameThread()
	{
		network.postToGameThread(mock(NetworkCommand.class));
	}
}
