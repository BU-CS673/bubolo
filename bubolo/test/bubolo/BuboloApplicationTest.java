package bubolo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bubolo.graphics.LibGdxAppTester;

import com.badlogic.gdx.Gdx;

public class BuboloApplicationTest
{
	private BuboloApplication ga;
	
	@Before
	public void setup()
	{
		ga = new BuboloApplication(500, 400);
	}
	
	@Test
	public void testIsReady()
	{
		assertFalse(ga.isReady());
	}


	@Test
	public void testCreate()
	{
		synchronized(LibGdxAppTester.getLock())
		{
			ga.create();
			assertTrue(ga.isReady());
		}
	}

	// NOTE: This test currently freezes. Continuing to research. However, this is the full
	//   game loop, so it may make sense to test it in integration.
	
//	@Test
//	public void testRender()
//	{
//		synchronized(LibGdxAppTester.getLock())
//		{
//			GameApplication ga = new BuboloApplication(500, 400);
//		//	LibGdxAppTester.createApp(ga);
//			ga.render();
//			class RenderTester implements Runnable
//			{
//				private GameApplication ga;
//				
//				RenderTester(GameApplication ga)
//				{
//					this.ga = ga;
//				}
//				
//				@Override
//				public void run()
//				{
//					try {
//						ga.render();
//					} catch (Exception e) {
//						fail("Exception was thrown when calling GameApplication.render()");
//					}
//				}
//			}
//			
//			RenderTester tester = new RenderTester(ga);
//			//Gdx.app.postRunnable(tester);
//		}
//	}

	@Test
	public void testDispose()
	{
		ga.dispose();
	}

	@Test
	public void pause()
	{
		ga.pause();
	}

	@Test
	public void resize()
	{
		ga.resize(600, 700);
	}

	@Test
	public void resume()
	{
		ga.resume();
	}
}
