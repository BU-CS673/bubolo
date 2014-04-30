package bubolo.graphics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import bubolo.mock.MockTank;
import bubolo.mock.MockWorld;
import bubolo.world.World;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
			@Override public void run() {
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
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				try {
					Texture texture = Graphics.getTexture(Graphics.TEXTURE_PATH + "tank.png");
					Graphics.dispose();
					passed = true;
				} catch (Exception e) {
					passed = false;
				} finally {
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
	public void constructGraphics()
	{
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				Graphics g = new Graphics(50, 500);
			}
		});
	}
	
	@Test
	public void getGraphicsInstance()
	{
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				Graphics g = new Graphics(50, 500);
				assertNotNull(Graphics.getInstance());				
			}
		});
	}
	
	@Test
	public void getGraphicsInstanceBad()
	{
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				try {
					Graphics.dispose();
					assertNotNull(Graphics.getInstance());
					fail("Graphics.getInstance() should throw an exception when it has not been explicitly instantiated, but it has not.");
				} catch (Exception e) {}
			}
		});
	}

	@Test
	public void draw()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				try {
					Graphics g = new Graphics(50, 500);
					g.draw(new MockWorld());
					passed = true;
				} catch (Exception e) {
					passed = false;
				} finally {
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
	public void draw2()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				try {
					Graphics g = new Graphics(50, 500);
					g.draw(new MockWorld(), null);
					g.draw(new MockWorld(), mock(Stage.class));
					passed = true;
				} catch (Exception e) {
					passed = false;
				} finally {
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
	public void addCameraController()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				try {
					Camera camera = new OrthographicCamera();
					CameraController controller = new TankCameraController(new MockTank());
					Graphics g = new Graphics(50, 500);
					g.addCameraController(controller);
					passed = true;
				} catch (Exception e) {
					passed = false;
				} finally {
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
	public void addCameraControllerAndUpdate()
	{
		isComplete = false;
		passed = false;
		
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				try {
					Camera camera = new OrthographicCamera();
					CameraController controller = new TankCameraController(new MockTank());
					controller.setCamera(camera);
					Graphics g = new Graphics(50, 500);
					g.addCameraController(controller);
					g.draw(new MockWorld());
					passed = true;
				} catch (Exception e) {
					passed = false;
				} finally {
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
}
