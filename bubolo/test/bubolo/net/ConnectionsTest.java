package bubolo.net;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import bubolo.world.World;
import static org.mockito.Mockito.*;

public class ConnectionsTest
{
	private Connections connections;
	
	@Before
	public void setup()
	{
		try
		{
			connections = new Connections(mock(Network.class), new MockServerSocket());
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testAddCommand()
	{
		connections.addCommand(new NetworkCommand() {
			@Override
			public void execute(World world)
			{
				// Do nothing. This is a mock object.
			}
		});
	}

	@Test
	public void testAddConnection()
	{
		connections.addConnection(new MockSocket());
	}

	@Test
	public void testDestroy()
	{
		connections.destroy();
		assertFalse(connections.isActive());
	}

	@Test
	public void testUpdate()
	{
		connections.update();
	}

	@Test
	public void testRun()
	{
		Thread t = new Thread(connections);
		t.start();
		t.interrupt();
	}

}
