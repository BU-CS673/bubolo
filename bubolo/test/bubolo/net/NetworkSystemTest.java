package bubolo.net;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.test.MockWorld;
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
	public void isActive()
	{
		assertFalse(network.isActive());
	}

	@Test
	public void shutdown()
	{
		network.shutdown();
		assertFalse(network.isActive());
	}

	@Test
	public void testConnect()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testStartServer()
	{
		network.startServer();
	}

	@Test
	public void testSend()
	{
		NetworkCommand command = mock(NetworkCommand.class);
		network.send(command);
	}

	@Test
	public void testUpdate()
	{
		network.update(new MockWorld());
	}
}
