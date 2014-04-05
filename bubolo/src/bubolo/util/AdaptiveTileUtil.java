/**
 *
 */

package bubolo.util;

import bubolo.world.Tile;
import bubolo.world.World;
import bubolo.world.entity.StationaryElement;
import bubolo.world.entity.Terrain;

/**
 * This utility provides various functions for determining the tiling state of
 * StationaryEntities that have multiple display states based on their surroundings.
 * 
 * @author BU CS673 - Clone Productions
 */
public class AdaptiveTileUtil
{
	private static boolean isValidTile(int gridX, int gridY, World w)
	{
		Tile[][] mapTiles = w.getMapTiles();
		if (gridX >= mapTiles.length || gridX < 0 || gridY >= mapTiles[gridX].length || gridY < 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	private static boolean containsTargetElement(Tile targetTile, Class<?>[] targetClasses)
	{
		if (targetTile.hasElement())
		{
			Class<? extends StationaryElement> tileClass = targetTile.getElement().getClass();
			for (int ii = 0; ii < targetClasses.length; ii++)
			{
				if (targetClasses[ii] == tileClass)
				{
					return true;
				}
			}
		}
		return false;
	}

	private static boolean containsTargetTerrain(Tile targetTile, Class<?>[] targetClasses)
	{
		Class<? extends Terrain> tileClass = targetTile.getTerrain().getClass();
		for (int ii = 0; ii < targetClasses.length; ii++)
		{
			if (targetClasses[ii] == tileClass)
			{
				return true;
			}
		}

		return false;
	}

	private static boolean matchesType(int gridX, int gridY, World w, Class<?>[] targetClasses)
	{
		if (!isValidTile(gridX, gridY, w))
		{
			return false;
		}

		Tile targetTile = w.getMapTiles()[gridX][gridY];

		if (containsTargetTerrain(targetTile, targetClasses)
				|| containsTargetElement(targetTile, targetClasses))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Returns the adaptive tiling state of an object located at the specified tile, given
	 * the list of Classes that the algorithm should consider 'matches'.
	 * 
	 * @param t
	 *            is the Tile where the object to be checked is contained.
	 * @param w
	 *            is the World object where the object, and any objects to be checked
	 *            against, reside.
	 * @param targetClasses
	 *            is an Array of any class which should be considered a 'match' -- that
	 *            is, tiles that contain any of the Class types listed in this array will
	 *            be considered a match for the purposes of determining the adaptive
	 *            tiling state of the specified Tile.
	 * @return an integer representing the correct adaptive tiling state for the specified
	 *         tile, according to the adaptive tiling mechanism outlined on the project
	 *         wiki.
	 */
	public static int getTilingState(Tile t, World w, Class<?>[] targetClasses)
	{
		int stateSum = 0;

		boolean[] edges = getEdgeMatches(t, w, targetClasses);

		// Match above
		if (edges[0])
		{
			stateSum += 1;
		}

		// Match below
		if (edges[1])
		{
			stateSum += 2;
		}

		// Match left
		if (edges[2])
		{
			stateSum += 4;
		}

		// Match right
		if (edges[3])
		{
			stateSum += 8;
		}

		return stateSum;
	}

	/**
	 * Returns an array of Boolean objects, representing whether the Tiles immediately
	 * above, below, to the left, and to the right of the target Tile contain objects of a
	 * Class matching those specified in the targetClasses array.
	 * 
	 * @param t
	 *            is the Tile where the object to be checked is contained.
	 * @param w
	 *            is the World object where the object, and any objects to be checked
	 *            against, reside.
	 * @param targetClasses
	 *            is an Array of any class which should be considered a 'match' -- that
	 *            is, tiles that contain any of the Class types listed in this array will
	 *            be considered a match for the purposes of determining the adaptive
	 *            tiling state of the specified Tile.
	 * @return an array of booleans, representing whether or not the tiles above, below,
	 *         to the left, and to the right of the specified tile match.
	 */
	public static boolean[] getEdgeMatches(Tile t, World w, Class<?>[] targetClasses)
	{
		int x = t.getGridX();
		int y = t.getGridY();
		boolean[] edges = new boolean[4];

		if (matchesType(x, y + 1, w, targetClasses))
		{
			edges[0] = true;
		}
		else
		{
			edges[0] = false;
		}

		if (matchesType(x, y - 1, w, targetClasses))
		{
			edges[1] = true;
		}
		else
		{
			edges[1] = false;
		}

		if (matchesType(x - 1, y, w, targetClasses))
		{
			edges[2] = true;
		}
		else
		{
			edges[2] = false;
		}

		if (matchesType(x + 1, y, w, targetClasses))
		{
			edges[3] = true;
		}
		else
		{
			edges[3] = false;
		}

		return edges;

	}

	/**
	 * Returns an array of Boolean objects, representing whether the Tiles to the top
	 * left, top right, bottom left, and bottom right of the specified tile contain
	 * objects of a Class matching those specified in the targetClasses array.
	 * 
	 * @param t
	 *            is the Tile where the object to be checked is contained.
	 * @param w
	 *            is the World object where the object, and any objects to be checked
	 *            against, reside.
	 * @param targetClasses
	 *            is an Array of any class which should be considered a 'match' -- that
	 *            is, tiles that contain any of the Class types listed in this array will
	 *            be considered a match for the purposes of determining the adaptive
	 *            tiling state of the specified Tile.
	 * @return an array of booleans, representing whether or not the tiles to the top
	 *         left, top right, bottom left, and bottom right of the specified tile match.
	 */
	public static boolean[] getCornerMatches(Tile t, World w, Class<?>[] targetClasses)
	{
		int x = t.getGridX();
		int y = t.getGridY();
		boolean[] corners = new boolean[4];

		if (matchesType(x - 1, y + 1, w, targetClasses))
		{
			corners[0] = true;
		}
		else
		{
			corners[0] = false;
		}

		if (matchesType(x + 1, y + 1, w, targetClasses))
		{
			corners[1] = true;
		}
		else
		{
			corners[1] = false;
		}

		if (matchesType(x - 1, y - 1, w, targetClasses))
		{
			corners[2] = true;
		}
		else
		{
			corners[2] = false;
		}

		if (matchesType(x + 1, y - 1, w, targetClasses))
		{
			corners[3] = true;
		}
		else
		{
			corners[3] = false;
		}

		return corners;

	}
}
