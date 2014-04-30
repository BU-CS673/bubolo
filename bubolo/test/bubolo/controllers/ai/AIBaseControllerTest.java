package bubolo.controllers.ai;

import org.junit.Test;

import bubolo.controllers.Controller;

import bubolo.graphics.LibGdxAppTester;
import bubolo.world.World;
import bubolo.world.entity.concrete.Base;
import static org.mockito.Mockito.mock;


public class AIBaseControllerTest
{
	@Test
	public void test()
	{
		LibGdxAppTester.createApp();
		Controller c = new AIBaseController(mock(Base.class));
		c.update(mock(World.class));
	}
}
