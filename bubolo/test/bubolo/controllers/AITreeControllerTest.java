package bubolo.controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import bubolo.controllers.ai.AITreeController;
import bubolo.graphics.LibGdxAppTester;
import bubolo.world.World;
import bubolo.world.entity.concrete.Tree;
import static org.mockito.Mockito.mock;


public class AITreeControllerTest
{
	@Test
	public void test()
	{
		LibGdxAppTester.createApp();
		Controller c = new AITreeController();
		c.update(mock(World.class));
	}
}
