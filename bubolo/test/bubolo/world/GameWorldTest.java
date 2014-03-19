package bubolo.world;

import static org.junit.Assert.*;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;

import bubolo.graphics.LibGdxAppTester;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Base;
import bubolo.world.entity.concrete.Bullet;
import bubolo.world.entity.concrete.Crater;
import bubolo.world.entity.concrete.DeepWater;
import bubolo.world.entity.concrete.Engineer;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Mine;
import bubolo.world.entity.concrete.Pillbox;
import bubolo.world.entity.concrete.Road;
import bubolo.world.entity.concrete.Rubble;
import bubolo.world.entity.concrete.Swamp;
import bubolo.world.entity.concrete.Tank;
import bubolo.world.entity.concrete.Tree;
import bubolo.world.entity.concrete.Wall;
import bubolo.world.entity.concrete.Water;

public class GameWorldTest
{
	boolean isComplete = false;
	boolean passed = false;
	
	@BeforeClass
	public static void setupClass()
	{
		LibGdxAppTester.createApp();
	}

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
	public void testAddEntityBase()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new AddEntityRunnable(Base.class));
		
		while (!isComplete) { Thread.yield(); }		
		assertTrue(passed);
	}
	
	@Test
	public void testAddEntityBullet()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new AddEntityRunnable(Bullet.class));
		
		while (!isComplete) { Thread.yield(); }		
		assertTrue(passed);
	}
	
	@Test
	public void testAddEntityCrater()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new AddEntityRunnable(Crater.class));
		
		while (!isComplete) { Thread.yield(); }		
		assertTrue(passed);
	}
	
	@Test
	public void testAddEntityDeepWater()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new AddEntityRunnable(DeepWater.class));
		
		while (!isComplete) { Thread.yield(); }		
		assertTrue(passed);
	}
	
	@Test
	public void testAddEntityGrass()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new AddEntityRunnable(Grass.class));
		
		while (!isComplete) { Thread.yield(); }		
		assertTrue(passed);
	}
	
	@Test
	public void testAddEntityEngineer()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new AddEntityRunnable(Engineer.class));
		
		while (!isComplete) { Thread.yield(); }		
		assertTrue(passed);
	}
	
	@Test
	public void testAddEntityMine()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new AddEntityRunnable(Mine.class));
		
		while (!isComplete) { Thread.yield(); }		
		assertTrue(passed);
	}
	
	@Test
	public void testAddEntityPillbox()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new AddEntityRunnable(Pillbox.class));
		
		while (!isComplete) { Thread.yield(); }		
		assertTrue(passed);
	}
	
	@Test
	public void testAddEntityRoad()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new AddEntityRunnable(Road.class));
		
		while (!isComplete) { Thread.yield(); }		
		assertTrue(passed);
	}
	
	@Test
	public void testAddEntityRubble()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new AddEntityRunnable(Rubble.class));
		
		while (!isComplete) { Thread.yield(); }		
		assertTrue(passed);
	}
	
	@Test
	public void testAddEntitySwamp()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new AddEntityRunnable(Swamp.class));
		
		while (!isComplete) { Thread.yield(); }		
		assertTrue(passed);
	}
	
	@Test
	public void testAddEntityTank()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new AddEntityRunnable(Tank.class));
		
		while (!isComplete) { Thread.yield(); }		
		assertTrue(passed);
	}
	
	@Test
	public void testAddEntityTree()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new AddEntityRunnable(Tree.class));
		
		while (!isComplete) { Thread.yield(); }		
		assertTrue(passed);
	}
	
//	@Test
//	public void testAddEntityWall()
//	{
//		boolean isComplete = false;
//		passed = false;
//		
//		Gdx.app.postRunnable(new AddEntityRunnable(Wall.class));
//		
//		while (!isComplete) { Thread.yield(); }		
//		assertTrue(passed);
//	}
	
	@Test
	public void testAddEntityWater()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new AddEntityRunnable(Water.class));
		
		while (!isComplete) { Thread.yield(); }		
		assertTrue(passed);
	}

	@Test
	public void testGetEntity()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				World w = new GameWorld(1, 1);
				Entity t = w.addEntity(Tank.class);
				assertEquals(t, w.getEntity(t.getId()));
				passed = true;
				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
		
		assertTrue(passed);
	}

	@Test
	public void testGetEntities()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				World w = new GameWorld(1, 1);
				List<Entity> list = w.getEntities();
				assertNotNull(list);
				passed = true;
				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
		
		assertTrue(passed);	
	}

	@Test
	public void testRemoveEntity_Entity()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				World w = new GameWorld(1, 1);
				UUID id = null;
				try {
					w.addEntity(Road.class);
					w.update();
					Entity e = w.getEntities().get(0);
					id = e.getId();
	
					w.removeEntity(e);
				} catch (Exception e) {
					e.printStackTrace();
					isComplete = true;
					return;
				}
				
				try {					
					w.getEntity(id);
					isComplete = true;
					passed = false;
				} catch (Exception exception) { 
					passed = true;
					isComplete = true;
				}
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
		
		assertTrue(passed);
	}

	@Test
	public void testRemoveEntity_UUID()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				passed = false;
				isComplete = false;
				
				World w = new GameWorld(1, 1);
				UUID id = null;
				try {
					Tank t = w.addEntity(Tank.class);
					w.update();
					id = t.getId();
					w.removeEntity(id);
				} catch (Exception e) {
					isComplete = true;
					return;
				}
				
				try
				{
					w.getEntity(id);
					isComplete = true;
				}
				catch (Exception exception)
				{
					passed = true;
					isComplete = true;
				}
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
		
		assertTrue(passed);
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

	
	
	private class AddEntityRunnable implements Runnable
	{
		private Class<? extends Entity> c;
		
		AddEntityRunnable(Class<? extends Entity> c)
		{
			this.c = c;
		}
		
		@Override 
		public void run() 
		{
			try 
			{
				isComplete = false;
				passed = false;
				World world = new GameWorld(1, 2);
				world.addEntity(c);
				passed = true;
				isComplete = true;
			} 
			catch (Exception e) 
			{ 
				e.printStackTrace();
				isComplete = true; 
			}
		}
	};
}
