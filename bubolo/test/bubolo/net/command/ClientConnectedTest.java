/**
 *
 */

package bubolo.net.command;

import static org.junit.Assert.*;

import org.junit.Test;

import bubolo.net.NetworkCommand;
import bubolo.world.World;
import static org.mockito.Mockito.mock;

/**
 * @author BU CS673 - Clone Productions
 */
public class ClientConnectedTest
{
	/**
	 * Test method for {@link bubolo.net.command.ClientConnected#getClientName()}.
	 */
	@Test
	public void testGetClientName()
	{
		NetworkCommand command = new ClientConnected("Test");
		command.execute(mock(World.class));
	}

	/**
	 * Test method for {@link bubolo.net.command.ClientConnected#execute(bubolo.world.World)}.
	 */
	@Test
	public void testExecute()
	{
		final String NAME = "TEST";
		ClientConnected command = new ClientConnected(NAME);
		assertEquals(NAME, command.getClientName());
	}
}
