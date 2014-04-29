package bubolo.mock;

import bubolo.world.Damageable;
import bubolo.world.entity.concrete.Tank;

/**
 * A mock Tank class used for testing the Graphics system.
 * 
 * @author BU CS673 - Clone Productions
 */
public class MockTank extends Tank implements Damageable
{
	private static final long serialVersionUID = 4076597185322719449L;

	/**
	 * Constructs a mock Tank object.
	 */
	public MockTank()
	{
	}
}
