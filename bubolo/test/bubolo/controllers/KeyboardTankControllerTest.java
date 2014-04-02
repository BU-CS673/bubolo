package bubolo.controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import bubolo.controllers.input.KeyboardTankController;
import bubolo.graphics.LibGdxAppTester;
import bubolo.world.World;
import bubolo.world.entity.concrete.Tank;
import static org.mockito.Mockito.mock;


public class KeyboardTankControllerTest
{
	@Test
	public void test()
	{
		LibGdxAppTester.createApp();
		Controller c = new KeyboardTankController(mock(Tank.class));
		c.update(mock(World.class));
	}
}
