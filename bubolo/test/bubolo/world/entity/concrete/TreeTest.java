package bubolo.world.entity.concrete;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class TreeTest
{
	static Tree tree;

	/**
	 * Constructs a Tree object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		tree = new Tree();
		EntityTestCase.setTestParams(tree);
	}

	@Test
	public void Tree()
	{
		assertTrue(true);
	}
	@Test
	public void  getHitPoints()
	{
		assertEquals(1, tree.getHitPoints(), 0);
	}
	
	@Test
	public void getMaxHitPoints()
	{
		assertEquals(1, tree.getMaxHitPoints(), 0);
	}
	
	@Test
	public void healDamageTest()
	{
		tree.takeHit(1);
		assertEquals(0, tree.getHitPoints(), 0);
		tree.heal(1);
		assertEquals(1, tree.getHitPoints(), 0);
	}
}
