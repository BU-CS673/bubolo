/**
 *
 */

package bubolo.net.command;

import static org.junit.Assert.*;

import org.junit.Test;

import bubolo.net.NetworkCommand;
import bubolo.world.World;
import bubolo.world.entity.concrete.Tank;
import static org.mockito.Mockito.mock;

/**
 * @author BU CS673 - Clone Productions
 */
public class MoveTankTest
{
	/**
	 * Test method for {@link bubolo.net.command.MoveTank#execute(bubolo.world.World)}.
	 */
	@Test
	public void testExecute()
	{
		NetworkCommand command = new MoveTank(mock(Tank.class));
		command.execute(mock(World.class));
	}
}
