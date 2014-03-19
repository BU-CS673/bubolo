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
		network.connect(mock(InetAddress.class));
	}

	@Test
	public void startServer()
	{
		network.startServer();
	}
	
	@Test
	public void startServerInvalid()
	{
		network.startServer();
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
		NetworkCommand command = new NetworkCommand() {
			@Override
			public void execute(World world)
			{	
			}
		};
		network.startServer();
		network.send(command);
	}

	@Test
	public void update()
	{
		network.startServer();
		network.update(new MockWorld());
	}
	
	@Test
	public void postToGameThread()
	{
		network.postToGameThread(mock(NetworkCommand.class));
	}
}
