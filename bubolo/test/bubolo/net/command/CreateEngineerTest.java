/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net.command;

import org.junit.Test;

import bubolo.mock.MockWorld;
import bubolo.net.NetworkCommand;
import bubolo.world.entity.concrete.Engineer;

/**
 * @author BU CS673 - Clone Productions
 */
public class CreateEngineerTest
{
	/**
	 * Test method for {@link bubolo.net.command.CreateEngineer#execute(bubolo.world.World)}.
	 */
	@Test
	public void testExecute()
	{
		MockWorld world = new MockWorld();
		
		Engineer engineer = new Engineer();
		world.add(engineer);
		
		NetworkCommand command = new CreateEngineer(engineer);
		command.execute(world);
	}
}
