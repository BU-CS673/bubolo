package bubolo.net;

import static org.junit.Assert.*;

import java.net.Socket;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class ConnectionReaderTest
{
	private ConnectionReader reader;
	
	@Before
	public void setup()
	{
		reader = new ConnectionReader(mock(Network.class), mock(Socket.class));
	}

	@Test
	public void testIsActive()
	{
		assertTrue(reader.isActive());
	}

	@Test
	public void testGetSocket()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testDestroy()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testRun()
	{
		fail("Not yet implemented");
	}

}
