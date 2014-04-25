/**
 *
 */

package bubolo.net.command;

import java.util.UUID;

import org.junit.Test;

import bubolo.mock.MockTank;
import bubolo.mock.MockWorld;
import bubolo.net.NetworkCommand;
import bubolo.world.entity.concrete.Tank;

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
		UUID id = UUID.randomUUID();
		Tank tank = new MockTank();
		tank.setId(id);
		
		NetworkCommand command = new MoveTank(tank);
		MockWorld world = new MockWorld();
		world.add(tank);
		command.execute(world);
	}
}
