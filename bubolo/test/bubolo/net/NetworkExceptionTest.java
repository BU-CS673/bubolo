/**
 *
 */

package bubolo.net;

import static org.junit.Assert.*;

import org.junit.Test;

import bubolo.util.GameException;

/**
 * @author BU CS673 - Clone Productions
 */
public class NetworkExceptionTest
{

	/**
	 * Test method for
	 * {@link bubolo.net.NetworkException#NetworkException(java.lang.String)}.
	 */
	@Test
	public void testNetworkExceptionString()
	{
		try
		{
			throw new NetworkException("Network exception");
		}
		catch (NetworkException e)
		{
			assertEquals("Network exception", e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link bubolo.net.NetworkException#NetworkException(java.lang.Throwable)}.
	 */
	@Test
	public void testNetworkExceptionThrowable()
	{
		RuntimeException exception = new RuntimeException("Network exception");
		try
		{
			throw new NetworkException(exception);
		}
		catch (NetworkException e)
		{
			assertEquals(exception, e.getCause());
		}
	}

}
