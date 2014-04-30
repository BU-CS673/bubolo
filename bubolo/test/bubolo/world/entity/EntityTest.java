package bubolo.world.entity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import bubolo.controllers.Controller;
import bubolo.mock.MockWorld;
import bubolo.world.GameWorld;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Tank;

public class EntityTest
{
	static Entity ent;
	static World world;

	/**
	 * Create an Entity and set its initial parameters.
	 */
	@BeforeClass
	public static void setUpApp()
	{

		ent = new MockEntity(EntityTestCase.TARGET_UUID);
		EntityTestCase.setTestParams(ent);
	}
	
	@Test
	public void isSolid(){
		ent.setSolid(true);
		assertEquals(true, ent.isSolid());
		ent.setSolid(false);
		assertEquals(false, ent.isSolid());
	}

	@Test
	public void setParams()
	{
		assertEquals("Entity parameters set correctly.", true,
				ent.getX() == EntityTestCase.TARGET_X && ent.getY() == EntityTestCase.TARGET_Y
						&& ent.getRotation() == EntityTestCase.TARGET_ROT);

	}
	
	@Test
	public void overlapsEntity(){
		Entity ent1 = new MockEntity();
		Entity ent2 = new MockEntity();
		Entity ent3 = new MockEntity();
		ent1.setParams(16, 16, 0);
		ent2.setParams(16, 32, 0);
		ent3.setParams(60, 60, 0);
		
		ent1.setWidth(32);
		ent1.setHeight(32);
		ent2.setWidth(32);
		ent2.setHeight(32);
		ent3.setWidth(32);
		ent3.setHeight(32);
		
		assertEquals(true, ent1.overlapsEntity(ent2));
		assertEquals(false, ent1.overlapsEntity(ent3));
	}

	@Test
	public void update()
	{
		ent.update(mock(World.class));
	}

	@Test
	public void getId()
	{
		assertEquals("UUID of new Entity consistent with input UUID.", EntityTestCase.TARGET_UUID,
				ent.getId());
	}

	@Test
	public void getX()
	{
		assertEquals("Entity x position matches target center.", EntityTestCase.TARGET_X, ent.getX(),
				.0001);
	}

	@Test
	public void getY()
	{
		assertEquals("Entity y position matches target center.", EntityTestCase.TARGET_Y, ent.getY(),
				.0001);
	}

	@Test
	public void getRotation()
	{
		assertEquals("Entity rotation matches target.", EntityTestCase.TARGET_ROT,
				ent.getRotation(), .0001);
	}

	@Test
	public void getWidth()
	{
		ent.setWidth(EntityTestCase.TARGET_WIDTH);
		assertEquals("Entity width matches target.", EntityTestCase.TARGET_WIDTH, ent.getWidth());
	}

	@Test
	public void getHeight()
	{
		ent.setHeight(EntityTestCase.TARGET_HEIGHT);
		assertEquals("Entity height matches target.", EntityTestCase.TARGET_HEIGHT, ent.getHeight());
	}

	@Test
	public void addController()
	{
		Entity tank = new Tank();
		tank.addController(mock(Controller.class));
		assertEquals(1, tank.getControllerCount());
	}

	@Test
	public void updateTest()
	{
		Entity tank = new Tank();
		tank.addController(new Controller() 
		{
			@Override
			public void update(World world)
			{
				// Do nothing.
			}
		});
		tank.update(new MockWorld());
	}

	@Test
	public void isDisposed()
	{
		Entity e = new MockEntity();
		assertFalse(e.isDisposed());
	}

	@Test
	public void disposeTest()
	{
		ent.dispose();
		assertTrue(ent.isDisposed());
	}
	
	@Test
	public void onDispose()
	{
		ent.onDispose();
	}
}
