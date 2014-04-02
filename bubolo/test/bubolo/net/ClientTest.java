/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author BU CS673 - Clone Productions
 */
public class ClientTest
{
	private Client client;

	@Before
	public void setup()
	{
		this.client = new Client(new MockNetwork());
	}

	@After
	public void teardown()
	{
		client.dispose();
	}

	/**
	 * Test method for {@link bubolo.net.Client#send(bubolo.net.NetworkCommand)}.
	 */
	@Test
	public void testSend()
	{
		client.send(mock(NetworkCommand.class));
	}
	
	@Test
	public void connect()
	{
		try
		{
			client.connect(InetAddress.getByName("127.0.0.1"));
		}
		catch (NetworkException | UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link bubolo.net.Client#run()}. Ensures that an
	 * IllegalStateException is thrown when run is called before starting the network
	 * system.
	 */
	@Test
	public void testRun()
	{
		try
		{
			client.run();
			fail("Expected exception, but none encountered");
		}
		catch (IllegalStateException e)
		{

		}
	}

}
