package bubolo.controllers;

import bubolo.controllers.ControllerFactory;
import bubolo.controllers.Controllers;
import bubolo.test.MockController;
import bubolo.world.entity.Entity;

/**
 * This should only be used for testing. 
 * 
 * @author BU CS673 - Clone Productions
 */
public class MockControllerFactory implements ControllerFactory
{
	@Override
	public void create(Entity entity, Controllers controllers)
	{
		controllers.addController(new MockController());
	}
}
