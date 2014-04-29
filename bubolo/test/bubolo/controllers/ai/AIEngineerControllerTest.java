package bubolo.controllers.ai;

import org.junit.Test;

import bubolo.controllers.Controller;
import bubolo.controllers.ai.AIEngineerController;
import bubolo.graphics.LibGdxAppTester;
import bubolo.world.World;
import bubolo.world.entity.concrete.Engineer;
import static org.mockito.Mockito.mock;

/**
 * @author BU CS673 - Clone Productions
 */
public class AIEngineerControllerTest
{
	@Test
	public void test()
	{
		LibGdxAppTester.createApp();
		Controller c = new AIEngineerController(mock(Engineer.class));
		c.update(mock(World.class));
	}
}
