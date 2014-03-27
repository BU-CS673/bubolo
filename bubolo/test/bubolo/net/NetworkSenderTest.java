/**
 *
 */

package bubolo.net;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.commons.io.output.NullOutputStream;
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
		try (ObjectOutputStream oos = new ObjectOutputStream(new NullOutputStream()))
		{
			NetworkSender sender = new NetworkSender(oos,
					mock(NetworkCommand.class));
			
			sender.run();
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}
