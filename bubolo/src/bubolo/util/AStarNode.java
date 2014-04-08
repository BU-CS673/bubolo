package bubolo.util;

import java.util.List;
import java.util.PriorityQueue;
import java.util.HashSet;

import bubolo.util.Coordinates;
import bubolo.util.Tiles;

/**
 * A Node that holds all intermediate data required during the execution of
 * the A-star pathfinding algorithm.
 * @author BU CS673 - Clone Productions
 */
public class AStarNode implements Comparable<AStarNode>
{
    // Grid Coordinates
    private int x;
    private int y;

    // Reference to underlying Tile
    private Tile tile;

    // A-star cost functions
    private int f;
    private int g;
    private int h;

	/**
	 * Constructs the AStarNode object.
	 * @param tile
     *               is a reference to the underlying map tile.
	 */
    public AStarNode(Tile t)
    {
        tile = t;
        x = tile.getGridX();
        y = tile.getGridY();
        f = 0;
        g = 0;
        h = 0;
    }

	/**
	 * Get this Node's x position in map/grid units.
	 * @return an integer representing this Node's x position in map/grid units.
	 */
    public int getX()
    {
        return x;
	}

	/**
	 * Compare this node against another node.
     * @param node
     *          the other node to compare this node against.
	 * @return an integer with the comparison result.
	 */
    public int compareTo(AStarNode node)
    {
        return Integer.compare(this.f, node.f);
    }
}
