package bubolo.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameMathTest
{

	@Test
	public void angleInRadians()
	{
		double value = GameMath.angleInRadians(3, 0, 3, 4);
		assertTrue(value < 1.58);
		assertTrue(value > 1.57);
	}

}
