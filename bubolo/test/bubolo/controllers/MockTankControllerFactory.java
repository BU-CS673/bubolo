package bubolo.controllers;

import bubolo.controllers.ControllerFactory;
import bubolo.controllers.Controllers;
import bubolo.test.MockTankController;
import bubolo.world.World;
import bubolo.world.entity.Entity;

public class MockTankControllerFactory implements ControllerFactory
{
	@Override
	public void create(Entity entity, Controllers controllers)
	{
		controllers.addController(new MockTankController());
	}
}
