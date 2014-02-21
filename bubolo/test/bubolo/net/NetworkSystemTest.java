package bubolo.net;

import static org.junit.Assert.*;

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
		network = new NetworkSystem();
	}
	
	
	@Test
	public void isActiveTrue()
	{
		assertTrue(network.isActive());
	}
	
	@Test
	public void isActiveFalse()
	{
		network.shutdown();
		assertFalse(network.isActive());
	}

	@Test
	public void shutdown()
	{
		network.shutdown();
		assertFalse(network.isActive());
	}

	@Test
	public void connect()
	{
		// TODO: call network.connect.
		network.connect(mock(InetSocketAddress.class));
	}
	
	@Test
	public void connectInvalid()
	{
		network.startServer();
		try
		{
			network.connect(mock(InetSocketAddress.class));
			fail("startServer should have failed, but did not.");
		}
		catch (IllegalStateException e)
		{
		}
	}

	@Test
	public void startServer()
	{
		network.startServer();
		network.shutdown();
	}
	
	@Test
	public void startServerInvalid()
	{
		network.connect(mock(InetSocketAddress.class));
		try
		{
			network.startServer();
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
}
