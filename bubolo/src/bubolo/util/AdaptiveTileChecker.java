/**
 *
 */

package bubolo.util;

import bubolo.world.Tile;
import bubolo.world.World;

/**
 * @author BU CS673 - Clone Productions
 */
public class AdaptiveTileChecker
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

	private static boolean containsTargetElement(Tile targetTile, Class[] targetClasses)
	{
		if (targetTile.hasElement())
		{
			Class tileClass = targetTile.getElement().getClass();
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

	private static boolean containsTargetTerrain(Tile targetTile, Class[] targetClasses)
	{
		Class tileClass = targetTile.getTerrain().getClass();
		for (int ii = 0; ii < targetClasses.length; ii++)
		{
			if (targetClasses[ii] == tileClass)
			{
				return true;
			}
		}

		return false;
	}

	private static boolean matchesType(int gridX, int gridY, World w, Class[] targetClasses)
	{
		if (!isValidTile(gridX, gridY, w))
		{
			return false;
		}

		Tile targetTile = w.getMapTiles()[gridX][gridY];

		if (containsTargetTerrain(targetTile, targetClasses) || containsTargetElement(targetTile, targetClasses))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static int getTilingState(Tile t, World w, Class[] targetClasses)
	{
		int x = t.getGridX();
		int y = t.getGridY();
		int stateSum = 0;

		boolean[] edges = getEdgeMatches(t, w, targetClasses);
		boolean[] corners = getCornerMatches(t, w, targetClasses);

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

	public static boolean[] getEdgeMatches(Tile t, World w, Class[] targetClasses)
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

	public static boolean[] getCornerMatches(Tile t, World w, Class[] targetClasses)
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
