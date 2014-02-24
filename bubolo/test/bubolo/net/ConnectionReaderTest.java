package bubolo.net;

import static org.junit.Assert.*;

import java.net.Socket;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class ConnectionReaderTest
{
	private ConnectionReader cr;
	
	@Before
	public void setup()
	{
		cr = new ConnectionReader(mock(Network.class), new MockSocket());
	}

	@Test
	public void testIsActive()
	{
		assertTrue(cr.isActive());
	}

	@Test
	public void testGetSocket()
	{
		assertNotNull(cr.getSocket());
	}

	@Test
	public void testDestroy()
	{
		cr.destroy();
		assertFalse(cr.isActive());
	}

	@Test
	public void testRun()
	{
		Thread t = new Thread(cr);
		t.start();
		
		try
		{
			// Kill the thread.
			t.interrupt();
		}
		catch (Exception e) {}
	}

}
