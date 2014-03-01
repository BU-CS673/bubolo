package bubolo.graphics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import bubolo.test.MockTank;
import bubolo.test.MockWorld;
import bubolo.world.World;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import static org.mockito.Mockito.*;

public class GraphicsTest extends ApplicationAdapter
{
	private boolean isComplete;
	private boolean passed;
	
	@Before
	public void setUp()
	{
		LibGdxAppTester.createApp();
	}
	
	@Test
	public void getTexture() throws InterruptedException
	{
		isComplete = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				Texture texture = Graphics.getTexture(Graphics.TEXTURE_PATH + "tank.png");
				assertNotNull(texture);
				
				isComplete = true;
			}
		});

		while (!isComplete)
		{
			Thread.yield();
		}
	}

	@Test
	public void disposeTest()
	{
		isComplete = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run()
			{
				Texture texture = Graphics.getTexture(Graphics.TEXTURE_PATH + "tank.png");
				Graphics.dispose();
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
	public void constructGraphics()
	{
		Graphics g = new Graphics(50, 500);
	}
	
	@Test
	public void getGraphicsInstance()
	{
		Graphics g = new Graphics(50, 500);
		assertNotNull(Graphics.getInstance());
	}
	
	@Test
	public void getGraphicsInstanceBad()
	{
		
		try {
			assertNotNull(Graphics.getInstance());
			fail("Graphics.getInstance() should throw an exception when it has not been explicitly instantiated, but it has not.");
		} catch (Exception e) {}	
	}

	@Test
	public void draw()
	{
		Graphics g = new Graphics(50, 500);
		g.draw(new MockWorld());
	}
	
	@Test
	public void addCameraController()
	{
		Camera camera = new OrthographicCamera();
		CameraController controller = new TankCameraController(new MockTank());
		Graphics g = new Graphics(50, 500);
		g.addCameraController(controller);
	}
	
	@Test
	public void addCameraControllerAndUpdate()
	{
		Camera camera = new OrthographicCamera();
		CameraController controller = new TankCameraController(new MockTank());
		controller.setCamera(camera);
		Graphics g = new Graphics(50, 500);
		g.addCameraController(controller);
		g.draw(new MockWorld());
	}
}
