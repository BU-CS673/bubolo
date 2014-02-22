package bubolo;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameApplicationTest
{
	@Test
	public void testGameApplication()
	{
		GameApplication ga = new GameApplication(500, 400);
	}

	@Test
	public void testCreate()
	{
		GameApplication ga = new GameApplication(500, 400);
		ga.create();
	}

	@Test
	public void testRender()
	{
		GameApplication ga = new GameApplication(500, 400);
		ga.create();
		ga.render();
	}

	@Test
	public void testDispose()
	{
		GameApplication ga = new GameApplication(500, 400);
		ga.dispose();
	}
	
}
