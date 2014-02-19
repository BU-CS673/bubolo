package bubolo.net;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bubolo.world.GameWorld;

public class NetworkSystemTest
{
	private Network network;
	
	@Before
	public void setup()
	{
		network = new NetworkSystem();
	}

	@Test
	public void testConnect()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testStartServer()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSend()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate()
	{
		network.update(new GameWorld(100, 50));
	}

}
