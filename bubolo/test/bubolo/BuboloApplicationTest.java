package bubolo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;

import bubolo.graphics.LibGdxAppTester;

public class BuboloApplicationTest
{
	private BuboloApplication ga;
	
	@Before
	public void setup()
	{
		LibGdxAppTester.createApp();
		ga = new BuboloApplication(500, 400);
	}
	
	@Test
	public void testIsReady()
	{
		assertFalse(ga.isReady());
		ga.setReady(true);
		assertTrue(ga.isReady());
	}

	@Test
	public void testCreate()
	{
		Gdx.app.postRunnable(new Runnable() {
			@Override public void run() {
				ga.create();
			}
		});
	}

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
	
	@Test
	public void isGameStarted()
	{
		assertFalse(ga.isGameStarted());
	}
	
	@Test
	public void disposeTest()
	{
		ga.dispose();
	}
}
