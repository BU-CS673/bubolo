package bubolo.world.entity;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.StationaryElement;
import bubolo.world.entity.concrete.Tank;
import bubolo.world.entity.concrete.Tree;

public class StationaryElementTest
{
	static StationaryElement sta;

	/**
	 * Sets up a Tree object with the default parameters.
	 */
	@BeforeClass
	public static void setUpApp()
	{
		sta = new Tree();
		EntityTestCase.setTestParams(sta);

	}
	
	@Test
	public void constructId()
	{
		Tree tree2 = new Tree(EntityTestCase.TARGET_UUID);
		assertEquals("StationaryElement UUID set correctly.", EntityTestCase.TARGET_UUID, tree2.getId());
	}
}
