package bubolo.world;

import static org.junit.Assert.*;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import bubolo.test.MockTank;
import bubolo.world.entity.Entity;

public class GameWorldTest
{

	@Test
	public void testGameWorld()
	{
		World w = new GameWorld(100, 1000);
	}

	@Test
	public void gameWorldBadWidth()
	{
		try
		{
			World w = new GameWorld(-100, 1000);
			fail("GameWorld did not fail on invalid input");
		}
		catch (Exception e)
		{
		}
	}

	@Test
	public void gameWorldBadHeight()
	{
		try
		{
			World w = new GameWorld(100, -1000);
			fail("GameWorld did not fail on invalid input");
		}
		catch (Exception e)
		{
		}
	}

	@Test
	public void testAddEntity()
	{
		World w = new GameWorld(1, 1);
		Entity t = new MockTank();
		w.addEntity(t);
	}

	@Test
	public void testAddEntityTwice()
	{
		World w = new GameWorld(1, 1);
		Entity t = new MockTank();
		w.addEntity(t);
		try
		{
			w.addEntity(t);
			fail("Entity was added twice, but this is not allowed.");
		}
		catch (Exception e)
		{
		}
	}

	@Test
	public void testGetEntity()
	{
		World w = new GameWorld(1, 1);
		Entity t = new MockTank();
		w.addEntity(t);
		assertEquals(t, w.getEntity(t.getId()));
	}

	@Test
	public void testGetEntities()
	{
		World w = new GameWorld(1, 1);
		List<Entity> list = w.getEntities();
		assertNotNull(list);
	}

	@Test
	public void testRemoveEntityEntity()
	{
		World w = new GameWorld(1, 1);
		w.addEntity(new MockTank());
		WeakReference<Entity> e = new WeakReference<Entity>(w.getEntities().get(0));
		UUID id = e.get().getId();

		w.removeEntity(e.get());
		try
		{
			w.getEntity(id);
			fail("The entity remained in the world after calling world.removeEntity");
		}
		catch (Exception exception)
		{
		}
	}

	@Test
	public void testRemoveEntityUUID()
	{
		World w = new GameWorld(1, 1);
		w.addEntity(new MockTank());
		UUID id = w.getEntities().get(0).getId();

		w.removeEntity(id);
		try
		{
			w.getEntity(id);
			fail("The entity remained in the world after calling world.removeEntity");
		}
		catch (Exception exception)
		{
		}
	}

	@Test
	public void testGetMapWidth()
	{
		World w = new GameWorld(10, 50);
		assertEquals(10, w.getMapWidth());
	}

	@Test
	public void testGetMapHeight()
	{
		World w = new GameWorld(10, 50);
		assertEquals(50, w.getMapHeight());
	}

}
