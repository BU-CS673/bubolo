/**
 *
 */

package bubolo.net.command;

import java.util.ArrayList;
import java.util.List;

import bubolo.net.NetworkCommand;
import bubolo.world.Tile;
import bubolo.world.World;
import bubolo.world.entity.StationaryElement;
import bubolo.world.entity.Terrain;

/**
 * Sends the game map to a client.
 * 
 * @author BU CS673 - Clone Productions
 */
public class SendMap implements NetworkCommand
{
	private final List<TerrainInfo> terrain;
	private final List<StationaryElementInfo> stationaryElements;

	/**
	 * Constructs a Send Map network command.
	 * 
	 * @param world
	 *            the game world.
	 */
	public SendMap(World world)
	{
		terrain = new ArrayList<TerrainInfo>();
		stationaryElements = new ArrayList<StationaryElementInfo>();
		
		Tile[][] map = world.getMapTiles();
		for (int row = 0; row < map.length; ++row)
		{
			for (int column = 0; column < map[0].length; ++column)
			{
				if (map[row][column])
			}
		}
	}

	@Override
	public void execute(World world)
	{
		for (int row = 0; row < )
	}

	private class TerrainInfo
	{
		private final int gridX;
		private final int gridY;
		private final Class<? extends Terrain> terrainClass;

		TerrainInfo(int gridX, int gridY, Class<? extends Terrain> c)
		{
			this.gridX = gridX;
			this.gridY = gridY;
			this.terrainClass = c;
		}
	}

	private class StationaryElementInfo
	{
		private final int gridX;
		private final int gridY;
		private final Class<? extends StationaryElement> stationaryElementClass;

		StationaryElementInfo(int gridX, int gridY, Class<? extends StationaryElement> c)
		{
			this.gridX = gridX;
			this.gridY = gridY;
			this.stationaryElementClass = c;
		}
	}
}
