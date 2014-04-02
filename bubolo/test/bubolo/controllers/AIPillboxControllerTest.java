package bubolo.controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import bubolo.controllers.ai.AIPillboxController;
import bubolo.graphics.LibGdxAppTester;
import bubolo.world.World;
import bubolo.world.entity.concrete.Pillbox;
import static org.mockito.Mockito.mock;


public class AIPillboxControllerTest
{
	@Test
	public void test()
	{
		LibGdxAppTester.createApp();
		Controller c = new AIPillboxController(mock(Pillbox.class));
		c.update(mock(World.class));
	}
}
