package bubolo.world;

import bubolo.util.Coordinates;
import bubolo.util.GameLogicException;
import bubolo.world.entity.StationaryElement;
import bubolo.world.entity.Terrain;

/**
 * Tiles represent one 'unit square' on a game map. They must contain one Terrain, and can
 * have either 1 or 0 StationaryElements which sit on top of that Terrain.
 * 
 * 
 * @author BU CS673 - Clone Productions
 */
public class Tile
{
	/**
	 * The WORLD_SCALE represents the number of World units that each Tile represents. 32
	 * is selected here because it is current size of a Sprite in pixels.
	 * 
	 * TODO: Move this constant out to a global Bubolo Preferences class.
	 */
	private static final float TILE_SIZE = Coordinates.WORLD_SCALE;
	private int gridX;
	private int gridY;
	private Terrain myTerrain;
	private StationaryElement myElement = null;

	/**
	 * Create a new Tile with the specified Terrain at the given map unit coordinates.
	 * 
	 * NOTE: When creating Terrain or StationaryElement objects to be stored in Tiles, it
	 * is strongly advised that they be constructed using World.AddEntity(), which ensures
	 * proper Sprite handling and Entity indexing.
	 * 
	 * @param x
	 *            is the x coordinate of this Tile in map/grid units.
	 * @param y
	 *            is the y coordinate of this Tile in map/grid units.
	 * @param t
	 *            is the Terrain that should be applied to this Tile.
	 */
	public Tile(int x, int y, Terrain t)
	{
		myTerrain = t;
		t.setTile(this);
		gridX = x;
		gridY = y;
	}

	/**
	 * Uses Tile.WORLD_SCALE to return this Tile's x position in World coordinates.
	 * 
	 * @return a float representing this Tile's world x position..
	 */
	public float getX()
	{
		return gridX * TILE_SIZE + TILE_SIZE/2;
	}

	/**
	 * Uses Tile.WORLD_SCALE to return this Tile's y position in World coordinates.
	 * 
	 * @return a float representing this Tile's world y position..
	 */
	public float getY()
	{
		return gridY * TILE_SIZE + TILE_SIZE/2;
	}

	/**
	 * Get this Tile's horizontal position in the map grid.
	 * 
	 * @return an integer representing this Tile's x position in map/grid units.
	 */
	public int getGridX()
	{
		return gridX;
	}

	/**
	 * Get this Tile's vertical position in the map grid.
	 * 
	 * @return an integer representing this Tile's y position in map/grid units.
	 */
	public int getGridY()
	{
		return gridY;
	}

	/**
	 * Get the Terrain object held in this Tile.
	 * 
	 * @return this Tile's Terrain object.
	 */
	public Terrain getTerrain()
	{
		if (myTerrain == null)
		{
			throw new GameLogicException("Tile has no terrain to return!");
		}
		else
		{
			return myTerrain;
		}
	}

	/**
	 * Get the StationaryElement held in this Tile, if one exists.
	 * 
	 * @return this Tile's StationaryElement.
	 */
	public StationaryElement getElement()
	{
		if (myElement == null)
		{
			throw new GameLogicException("Tile has no element to return!");
		}
		else
			return myElement;
	}

	/**
	 * Check to see whether this Tile has a StationaryElement.
	 * 
	 * @return true if a StationaryElement exists in this tile, false otherwise.
	 */
	public boolean hasElement()
	{
		if (myElement != null)
		{
			return true;
		}
		else
			return false;
	}

	/**
	 * Removes this Tile's StationaryElement. Does nothing if a StationaryElement does ont
	 * exist in this Tile.
	 * 
	 * @return a reference to this Tile.
	 */
	public Tile clearElement()
	{
		myElement = null;
		return this;
	}

	/**
	 * Set the StationaryElement of this Tile.
	 * 
	 * NOTE: When creating Terrain or StationaryElement objects to be stored in Tiles, it
	 * is strongly advised that they be constructed using World.AddEntity(), which ensures
	 * proper Sprite handling and Entity indexing.
	 * 
	 * @param e
	 *            is the StationaryElement that should be added to this Tile.
	 * @return a reference to this Tile.
	 */
	public Tile setElement(StationaryElement e)
	{
		myElement = e;
		e.setTile(this);
		return this;
	}

	/**
	 * Set the Terrain of this Tile.
	 * 
	 * NOTE: When creating Terrain or StationaryElement objects to be stored in Tiles, it
	 * is strongly advised that they be constructed using World.AddEntity(), which ensures
	 * proper Sprite handling and Entity indexing.
	 * 
	 * @param t
	 *            is the Terrain that should be assigned to this Tile.
	 * @return a reference to this Tile.
	 */
	public Tile setTerrain(Terrain t)
	{
		if (t != null)
		{
			myTerrain = t;
			t.setTile(this);
			return this;
		}
		else
			throw new GameLogicException("Cannot set a Tile's Terrain object to null!");
	}

}
