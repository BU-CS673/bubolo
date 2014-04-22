/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net.command;

import static org.junit.Assert.*;

import org.junit.Test;

import bubolo.net.NetworkCommand;
import bubolo.test.MockWorld;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Bullet;
import bubolo.world.entity.concrete.Tank;

/**
 * @author BU CS673 - Clone Productions
 */
public class CreateBulletTest
{
	/**
	 * Test method for {@link bubolo.net.command.CreateBullet#execute(bubolo.world.World)}.
	 */
	@Test
	public void testExecute()
	{
		MockWorld world = new MockWorld();
		
		Bullet bullet = new Bullet();
		world.add(bullet);
		
		Entity tank = new Tank();
		world.add(tank);
		
		NetworkCommand command = new CreateBullet(bullet.getClass(), bullet.getId(), bullet.getX(), 
				bullet.getY(), bullet.getRotation(), tank.getId());
		command.execute(world);
	}
}
