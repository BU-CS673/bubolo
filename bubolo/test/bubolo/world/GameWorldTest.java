package bubolo.world;

import static org.junit.Assert.*;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Road;
import bubolo.world.entity.concrete.Tank;

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
		World world = new GameWorld(1, 2);
		world.addEntity(Grass.class);
	}

	@Test
	public void testGetEntity()
	{
		World w = new GameWorld(1, 1);
		Entity t = w.addEntity(Tank.class);
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
		w.addEntity(Road.class);
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
		w.addEntity(Tank.class);
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
