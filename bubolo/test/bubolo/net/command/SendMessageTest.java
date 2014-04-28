/**
 *
 */

package bubolo.net.command;

import static org.junit.Assert.*;

import org.junit.Test;

import bubolo.net.Network;
import bubolo.net.NetworkCommand;
import bubolo.net.NetworkSystem;
import bubolo.world.World;
import static org.mockito.Mockito.mock;

/**
 * @author BU CS673 - Clone Productions
 */
public class SendMessageTest
{
	/**
	 * Test method for {@link bubolo.net.command.SendMessage#execute(bubolo.world.World)}.
	 */
	@Test
	public void testExecute()
	{
		NetworkCommand command = new SendMessage("Test");
		command.execute(mock(World.class));
	}

	/**
	 * Test method for {@link bubolo.net.command.SendMessage#getMessage()}.
	 */
	@Test
	public void testGetMessage()
	{
		final String MESSAGE = "TEST TEST";
		final String NAME = "HELLO";
		Network net = NetworkSystem.getInstance();
		net.startDebug();
		net.startServer(NAME);
		
		SendMessage command = new SendMessage(MESSAGE);
		assertEquals(NAME + ": " + MESSAGE, command.getMessage());
	}
}
