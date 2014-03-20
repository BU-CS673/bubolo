package bubolo.world.entity;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.Effect;
import bubolo.world.entity.concrete.Bullet;
import bubolo.world.entity.concrete.Tank;

public class EffectTest
{
	static Effect bull;

	/**
	 * Creates a Bullet object and sets the starting parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		bull = new Bullet();
		EntityTestCase.setTestParams(bull);
	}

	@Test
	public void constructId()
	{
		Bullet bull2 = new Bullet(EntityTestCase.TARGET_UUID);
		assertEquals("Effect UUID set correctly.", EntityTestCase.TARGET_UUID, bull2.getId());
	}
	
	@Test 
	public void update(){
		//Implicitly checks that exceptions are not thrown.
	}

}
