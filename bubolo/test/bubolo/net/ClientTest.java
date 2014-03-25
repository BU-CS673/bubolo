/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net;

import static org.junit.Assert.*;

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
		
	}
	
	@After
	public void teardown()
	{
		client.dispose();
	}
	
	
	/**
	 * Test method for {@link bubolo.net.Client#connect(java.net.InetAddress)}.
	 */
	@Test
	public void testConnect()
	{
//		client.connect(serverIpAddress);
	}

	/**
	 * Test method for {@link bubolo.net.Client#send(bubolo.net.NetworkCommand)}.
	 */
	@Test
	public void testSend()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link bubolo.net.Client#run()}.
	 */
	@Test
	public void testRun()
	{
		client.run();
	}

}
