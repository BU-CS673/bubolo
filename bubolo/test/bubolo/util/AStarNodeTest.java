package bubolo.util;

import org.junit.Test;

import bubolo.world.Tile;
import bubolo.world.entity.concrete.Grass;
import bubolo.util.AStarNode;

/**
 * @author BU CS673 - Clone Productions
 */
public class AStarNodeTest
{
	@Test
	public void testAccessors()
	{
		Tile tile = new Tile(0, 0, new Grass());
		AStarNode node = new AStarNode(tile);
		
		float x = 42;
	
		node.setF(x);
		node.setG(x);
		node.setH(x);
		node.setTile(tile);
		node.setParent(null);
		
		assert(x == node.getF());
		assert(x == node.getG());
		assert(x == node.getH());
		assert(tile == node.getTile());
		assert(null == node.getParent());
	}

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
		
		assert(Float.compare(a, b) == nodeA.compareTo(nodeB));
	}
}
