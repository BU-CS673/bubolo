package bubolo.net;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class ConnectionsTest
{
	private Connections connections;
	
	@Before
	public void setup()
	{
		connections = new Connections(mock(Network.class), new MockSocket());
	}

	@Test
	public void testConnections()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testAddCommand()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testAddConnection()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testDestroy()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testRun()
	{
		fail("Not yet implemented");
	}

}
