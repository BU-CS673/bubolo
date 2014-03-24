/**
 *
 */

package bubolo.net;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.junit.Test;

/**
 * @author BU CS673 - Clone Productions
 */
public class NetworkSenderTest
{

	/**
	 * Test method for {@link bubolo.net.NetworkSender#run()}.
	 */
	@Test
	public void testRun()
	{
		ObjectOutputStream oos = null;
		try
		{
			oos = new ObjectOutputStream(System.out);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		NetworkSender sender = new NetworkSender(oos,
				mock(NetworkCommand.class));
		
		sender.run();
	}
}
