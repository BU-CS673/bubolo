/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net.command;

import org.junit.Test;

import bubolo.net.NetworkCommand;
import bubolo.test.MockWorld;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Pillbox;
import bubolo.world.entity.concrete.Tank;

/**
 * @author BU CS673 - Clone Productions
 */
public class UpdateOwnableTest
{
	/**
	 * Test method for {@link bubolo.net.command.CreateBullet#execute(bubolo.world.World)}.
	 */
	@Test
	public void testExecute()
	{
		MockWorld world = new MockWorld();
		
		Pillbox pillbox = new Pillbox();
		world.add(pillbox);
		
		Entity tank = new Tank();
		world.add(tank);
		
		NetworkCommand command = new UpdateOwnable(pillbox);
		command.execute(world);
	}
}
