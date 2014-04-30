package bubolo.util;

import bubolo.world.Tile;

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

    // A-star cost functions. Note that although the algorithm will be based
    // on Tile co-ordinates (which are integer), we'll do the calculations
    // in float because the H function needs to be perturbed by a slight
    // fraction in order to revolve ties.
    private float f;
    private float g;
    private float h;
    
    // Reference to "parent" node for back-tracking along
    // shortest path
    private AStarNode parent;

	/**
	 * Constructs the AStarNode object.
	 * @param t
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
        parent = null;
    }

	/**
	 * Compare this node against another node.
     * @param node
     *          the other node to compare this node against.
	 * @return an integer with the comparison result.
	 */
    @Override
    public int compareTo(AStarNode node)
    {
        return Float.compare(this.f, node.f);
    }

	/**
	 * @return the x
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * @return the tile
	 */
	public Tile getTile()
	{
		return tile;
	}

	/**
	 * @param tile the tile to set
	 */
	public void setTile(Tile tile)
	{
		this.tile = tile;
	}

	/**
	 * @return the f
	 */
	public float getF()
	{
		return f;
	}

	/**
	 * @param f the f to set
	 */
	public void setF(float f)
	{
		this.f = f;
	}

	/**
	 * @return the g
	 */
	public float getG()
	{
		return g;
	}

	/**
	 * @param g the g to set
	 */
	public void setG(float g)
	{
		this.g = g;
	}

	/**
	 * @return the h
	 */
	public float getH()
	{
		return h;
	}

	/**
	 * @param h the h to set
	 */
	public void setH(float h)
	{
		this.h = h;
	}

	/**
	 * @return the parent
	 */
	public AStarNode getParent()
	{
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(AStarNode parent)
	{
		this.parent = parent;
	}
}
