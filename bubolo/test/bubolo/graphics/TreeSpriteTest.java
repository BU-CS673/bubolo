package bubolo.graphics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Road;
import bubolo.world.entity.concrete.Tank;
import bubolo.world.entity.concrete.Tree;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TreeSpriteTest
{
	private SpriteBatch batch;
	private Camera camera;
	
	private boolean isComplete;
	private boolean passed;
	
	@Before
	public void setUp()
	{	
		synchronized(LibGdxAppTester.getLock())
		{
			LibGdxAppTester.createApp();
			
			batch = new SpriteBatch();
			camera = new OrthographicCamera(100, 100);
			Graphics g = new Graphics(50, 500);
		}
	}
	

	@Test
	public void drawTreeSprite()
	{
		synchronized(LibGdxAppTester.getLock())
		{
			isComplete = false;
			passed = false;
			
			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run()
				{
					Sprite<?> sprite = Sprites.getInstance().createSprite(new Tree());
					batch.begin();
					sprite.draw(batch, camera, DrawLayer.OBJECTS);
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
	}

}
