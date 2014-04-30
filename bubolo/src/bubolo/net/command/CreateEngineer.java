/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net.command;

import java.util.logging.Logger;

import bubolo.controllers.ControllerFactory;
import bubolo.controllers.net.NetworkEngineerController;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Engineer;

/**
 * Creates a network-controlled Engineer on connected computers.
 * 
 * @author BU CS673 - Clone Productions
 */
public class CreateEngineer extends CreateEntity
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a CreateEngineer object
	 * 
	 * @param engineer
	 *            reference to the engineer that should be created on network players' computers.
	 */
	public CreateEngineer(Engineer engineer)
	{
		super(Engineer.class, engineer.getId(), engineer.getX(), engineer.getY(), engineer.getRotation(),
				new ControllerFactory() {
					private static final long serialVersionUID = 1L;

					@Override
					public void create(Entity entity)
					{
						entity.addController(new NetworkEngineerController());
					}
				});
	}

	@Override
	public void execute(World world)
	{
		super.execute(world);

		try
		{
			Engineer engineer = (Engineer)world.getEntity(getId());
			engineer.setLocalPlayer(false);
		}
		catch (Exception e)
		{
			Logger.getGlobal().severe("CreateEngineer: The engineer was not created. ID: " + getId());
		}
	}
}
