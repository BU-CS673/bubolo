package bubolo.util;

import org.junit.Test;
import static org.junit.Assert.*;

import bubolo.world.Tile;
import bubolo.world.entity.concrete.Grass;
import bubolo.util.AStarNode;

/**
 * @author BU CS673 - Clone Productions
 */
public class AStarNodeTest
{
	/**
	 * Tests all getters and setters.
	 */
	@Test
	public void testAccessors()
	{
		Tile tile = new Tile(0, 0, new Grass());
		AStarNode node = new AStarNode(tile);
		
		float f = 42;
		float g = 43;
		float h = 44;
	
		node.setF(f);
		node.setG(g);
		node.setH(h);
		node.setTile(tile);
		node.setParent(null);
		
		assertTrue(f == node.getF());
		assertTrue(g == node.getG());
		assertTrue(h == node.getH());
		assertTrue(tile == node.getTile());
		assertTrue(null == node.getParent());
	}

	/**
	 * Tests the compareTo() function.
	 */
	@Test
	public void testCompareFunction()
	{
		Tile tileA = new Tile(0, 0, new Grass());
		Tile tileB = new Tile(0, 1, new Grass());
		AStarNode nodeA = new AStarNode(tileA);
		AStarNode nodeB = new AStarNode(tileB);	

		float a = 42;
		float b = 84;
		
		nodeA.setF(a);
		nodeB.setF(b);
		
		assertTrue(Float.compare(a, b) == nodeA.compareTo(nodeB));
	}
}
