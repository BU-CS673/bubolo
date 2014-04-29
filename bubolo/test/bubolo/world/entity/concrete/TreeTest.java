package bubolo.world.entity.concrete;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.GameWorld;
import bubolo.world.Tile;
import bubolo.world.World;
import bubolo.world.entity.EntityTestCase;

public class TreeTest
{
	private static Tree tree;
	private static World world;
	private static Grass grass;
	/**
	 * Constructs a Tree object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		tree = new Tree();
		grass = new Grass();
		Tile[][] treeTile = new Tile[1][1];
		treeTile[0][0] = new Tile(0,0,grass);
		treeTile[0][0].setElement(tree);
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
	
	@Test
	public void onDispose()
	{
		tree.onDispose();
	}
}
