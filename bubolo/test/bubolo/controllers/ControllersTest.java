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
	public void testCreateEntityControllerFactoryMockController()
	{
		Controllers controllerSystem = Controllers.getInstance();
		// Use Grass entity, since it does not have any controllers by default.
		controllerSystem.create(new Grass(), new MockControllerFactory());
		assertEquals(0, controllerSystem.getCount());
	}
	
	@Test
	public void testCreateEntityControllerFactory()
	{
		Controllers controllerSystem = Controllers.getInstance();
		// Use Grass entity, since it does not have any controllers by default.
		controllerSystem.create(new Grass(), null);
		assertEquals(0, controllerSystem.getCount());
	}

	@Test
	public void createTankControllerFactoryMockController()
	{
		Controllers controllerSystem = Controllers.getInstance();
		controllerSystem.create(new Tank(), new MockControllerFactory());
		assertEquals(1, controllerSystem.getCount());
	}
	
	// TODO: this will not pass until at least one tree controller has been implemented.
	@Test
	public void createTankControllerFactory()
	{
		Controllers controllerSystem = Controllers.getInstance();
		controllerSystem.create(new Tank(), null);
		assertEquals(1, controllerSystem.getCount());
	}

	@Test
	public void createTreeControllerFactoryMockController()
	{
		Controllers controllerSystem = Controllers.getInstance();
		controllerSystem.create(new Tree(), new MockControllerFactory());
		assertEquals(1, controllerSystem.getCount());
	}
	
	// TODO: this will not pass until at least one tree controller has been implemented.
	@Test
	public void createTreeControllerFactory()
	{
		Controllers controllerSystem = Controllers.getInstance();
		controllerSystem.create(new Tree(), null);
		assertEquals(1, controllerSystem.getCount());
	}

}
