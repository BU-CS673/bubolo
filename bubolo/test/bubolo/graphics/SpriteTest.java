package bubolo.graphics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteTest
{
	private SpriteBatch batch;
	private Camera camera;

	private boolean isComplete;
	private boolean passed;

	@Before
	public void setUp()
	{
		LibGdxAppTester.createApp();

		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				batch = new SpriteBatch();
				camera = new OrthographicCamera(100, 100);
				Graphics g = new Graphics(50, 500);
			}
		});
	}

	@Test
	public void testDrawTextureRegion()
	{
		isComplete = false;
		passed = false;

		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				Sprite sprite = new MockSpriteTextureRegion();
				batch.begin();
				sprite.draw(batch, camera, sprite.getDrawLayer());
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
	public void testColor()
	{
		isComplete = false;
		passed = false;

		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				Sprite sprite = new BackgroundSprite(1, 1);

				assertEquals(Color.WHITE, sprite.getColor());

				sprite.setColor(Color.BLACK);
				assertEquals(Color.BLACK, sprite.getColor());

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
	public void testDrawTextureRegionWrongLayer()
	{
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				Camera cam = new OrthographicCamera();
				SpriteBatch spriteBatch = new SpriteBatch();
				Sprite sprite = new MockSpriteTextureRegion();
				DrawLayer wrongLayer = (sprite.getDrawLayer() != DrawLayer.BOTTOM) 
						? DrawLayer.BOTTOM : DrawLayer.SECOND;
				sprite.draw(spriteBatch, cam, wrongLayer);
			}
		});
	}
}
