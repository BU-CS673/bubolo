package bubolo.graphics;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GraphicsTest.class, GrassSpriteTest.class,
		RoadSpriteTest.class, SpritesTest.class, TankCameraControllerTest.class,
		TankSpriteTest.class, TreeSpriteTest.class })

public class AllGraphicsTests
{
}
