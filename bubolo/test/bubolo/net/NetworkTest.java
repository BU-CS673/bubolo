/**
 *
 */

package bubolo.net;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bubolo.test.MockWorld;

/**
 * @author BU CS673 - Clone Productions
 */
public class NetworkTest
{
	private Network net;
	
	@Before
	public void setup()
	{
		net = NetworkSystem.getInstance(); 
		// Non-debug tests are performed in the integration tests, since these can only
		// be fully tested by connecting to the network.
		net.startDebug();
	}
	
	@After
	public void teardown()
	{
		net.dispose();
	}
	
	/**
	 * Test method for {@link bubolo.net.NetworkSystem#getInstance()}.
	 */
	@Test
	public void testGetInstance()
	{
		assertNotNull(NetworkSystem.getInstance());
	}

	/**
	 * Test method for {@link bubolo.net.NetworkSystem#startServer()}.
	 * Note that this does not perform a full server test, since a connection to the internet
	 * and other external resources would be needed. Instead, it tests that the method's invariant
	 * is true, and then returns. The full test should be performed in integration.
	 */
	@Test
	public void testStartServer()
	{
		net.startServer();
	}

	/**
	 * Test method for {@link bubolo.net.NetworkSystem#connect(java.net.InetAddress)}.
	 * Note that this does not perform a full server test, since a connection to the internet
	 * and other external resources would be needed. Instead, it tests that the method's invariant
	 * is true, and then returns. The full test should be performed in integration.
	 */
	@Test
	public void testConnect()
	{
		try
		{
			net.connect(InetAddress.getByName("127.0.0.1"));
		}
		catch (NetworkException | IllegalStateException | UnknownHostException e)
		{
			fail("Exception thrown in Network.connect");
		}
	}

	/**
	 * Test method for {@link bubolo.net.NetworkSystem#send(bubolo.net.NetworkCommand)}.
	 */
	@Test
	public void testSend()
	{
		net.send(mock(NetworkCommand.class));
	}

	/**
	 * Test method for {@link bubolo.net.NetworkSystem#update(bubolo.world.World)}.
	 */
	@Test
	public void testUpdate()
	{
		net.update(new MockWorld());
	}

	/**
	 * Test method for {@link bubolo.net.NetworkSystem#postToGameThread(bubolo.net.NetworkCommand)}.
	 */
	@Test
	public void testPostToGameThread()
	{
		net.postToGameThread(mock(NetworkCommand.class));
	}
}
