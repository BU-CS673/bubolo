/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net.command;

import java.util.logging.Logger;

import bubolo.controllers.ControllerFactory;
import bubolo.controllers.net.NetworkTankController;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Tank;

/**
 * Creates a network-controlled tank on connected computers.
 * 
 * @author BU CS673 - Clone Productions
 */
public class CreateTank extends CreateEntity
{
	private static final long serialVersionUID = 1L;

	/**
	 * @param tank
	 *            reference to the tank that should be created on network players' computers.
	 */
	public CreateTank(Tank tank)
	{
		super(Tank.class, tank.getId(), tank.getX(), tank.getY(), tank.getRotation(),
				new ControllerFactory() {
					private static final long serialVersionUID = 1L;

					@Override
					public void create(Entity entity)
					{
						entity.addController(new NetworkTankController());
					}
				});
	}

	@Override
	public void execute(World world)
	{
		super.execute(world);

		try
		{
			Tank tank = (Tank)world.getEntity(getId());
			tank.setLocalPlayer(false);
		}
		catch (Exception e)
		{
			Logger.getGlobal().severe("CreateTank: The tank was not created. ID: " + getId());
		}
	}
}
