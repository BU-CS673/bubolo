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
public class ConnectedToServerTest
{
	/**
	 * Test method for {@link bubolo.net.command.ConnectedToServer#execute(bubolo.world.World)}.
	 */
	@Test
	public void testExecute()
	{
		NetworkCommand command = new ConnectedToServer("Client", "Server");
		command.execute(mock(World.class));
	}

	@Test
	public void testValues()
	{
		final String CLIENT_NAME = "CLIENT";
		final String SERVER_NAME = "SERVER";
		ConnectedToServer command = new ConnectedToServer(CLIENT_NAME, SERVER_NAME);
		assertEquals(CLIENT_NAME, command.getClientName());
		assertEquals(SERVER_NAME, command.getServerName());
	}
}
