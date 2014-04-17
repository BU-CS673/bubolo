package bubolo.controllers.ai;

import static org.junit.Assert.*;

import org.junit.Test;

import bubolo.controllers.Controller;
import bubolo.controllers.ai.AITreeController;
import bubolo.graphics.LibGdxAppTester;
import bubolo.world.GameWorld;
import bubolo.world.World;
import bubolo.world.entity.concrete.Mine;
import bubolo.world.entity.concrete.Pillbox;
import bubolo.world.entity.concrete.Tree;
import static org.mockito.Mockito.mock;


public class AIMineControllerTest
{
	@Test
	public void test()
	{
		LibGdxAppTester.createApp();
		Controller c = new AIMineController(mock(Mine.class));
		World world = new GameWorld(100, 100);
		c.update(world);
	}
}
