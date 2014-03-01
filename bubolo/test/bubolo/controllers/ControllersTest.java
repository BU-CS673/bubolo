package bubolo.controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import bubolo.test.MockWorld;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Tank;
import bubolo.world.entity.concrete.Tree;

public class ControllersTest
{

	@Test
	public void testGetInstance()
	{
		assertNotNull(Controllers.getInstance());
	}

	@Test
	public void testUpdate()
	{
		Controllers.getInstance().update(new MockWorld());
	}

	@Test
	public void testCreateEntityControllerFactory()
	{
		Controllers controllerSystem = Controllers.getInstance();
		// Pass Grass entity, since it does not have any controllers normally.
		controllerSystem.createController(new Grass(), null);
		assertEquals(0, controllerSystem.getCount());
	}

	@Test
	public void testCreateTankControllerFactory()
	{
		Controllers controllerSystem = Controllers.getInstance();
		controllerSystem.createController(new Tank(), new MockControllerFactory());
		assertEquals(1, controllerSystem.getCount());
	}

	// TODO: this will not pass until at least one tree controller has been implemented.
	@Test
	public void testCreateTreeControllerFactory()
	{
		Controllers controllerSystem = Controllers.getInstance();
		controllerSystem.createController(new Tree(), null);
		assertEquals(1, controllerSystem.getCount());
	}

}
