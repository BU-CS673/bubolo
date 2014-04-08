/**
 *
 */

package bubolo.net.command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import bubolo.net.NetworkCommand;
import bubolo.world.Tile;
import bubolo.world.World;
import bubolo.world.entity.StationaryElement;
import bubolo.world.entity.Terrain;

/**
 * Command that is used to send the game map to a client.
 * 
 * @author BU CS673 - Clone Productions
 */
public class SendMap implements NetworkCommand
{
	private static final long serialVersionUID = 1L;

	private final List<TileInfo> tiles;
	private final int rows;
	private final int columns;
	private final int worldWidth;
	private final int worldHeight;

	/**
	 * Constructs a Send Map network command.
	 * 
	 * @param world
	 *            the game world.
	 */
	public SendMap(World world)
	{
		this.worldWidth = world.getMapWidth();
		this.worldHeight = world.getMapHeight();

		this.tiles = new ArrayList<TileInfo>();

		Tile[][] map = world.getMapTiles();
		this.rows = map.length;
		this.columns = map[0].length;

		for (int row = 0; row < map.length; ++row)
		{
			for (int column = 0; column < map[0].length; ++column)
			{
				Tile tile = map[row][column];

				TileInfo tileInfo = new TileInfo(tile.getGridX(),
						tile.getGridY(),
						tile.getTerrain().getClass(), tile.getTerrain().getId(),
						(tile.hasElement()) ? tile.getElement().getClass() : null,
						(tile.hasElement()) ? tile.getElement().getId() : null);
				tiles.add(tileInfo);
			}
		}
	}

	@Override
	public void execute(World world)
	{
		world.setMapWidth(worldWidth);
		world.setMapHeight(worldHeight);

		Tile[][] mapTiles = new Tile[rows][columns];

		for (final TileInfo t : tiles)
		{
			Terrain terrain = world.addEntity(t.getTerrainClass(), t.getTerrainId());
			terrain.setRotation((float)Math.PI / 2.f);
			mapTiles[t.getGridX()][t.getGridY()] = new Tile(
					t.getGridX(),
					t.getGridY(),
					terrain);

			if (t.getStationaryElementClass() != null)
			{
				StationaryElement element = world.addEntity(t.getStationaryElementClass(),
						t.getStationaryElementId());
				element.setRotation((float)Math.PI / 2);
				mapTiles[t.getGridX()][t.getGridY()].setElement(element);
			}
		}

		world.setMapTiles(mapTiles);
	}

	/**
	 * Information about a game tile.
	 * 
	 * @author BU CS673 - Clone Productions
	 */
	private static class TileInfo implements Serializable
	{
		private static final long serialVersionUID = 1L;

		private final int gridX;
		private final int gridY;

		private final Class<? extends Terrain> terrainClass;
		private final UUID terrainId;

		private final Class<? extends StationaryElement> stationaryElementClass;
		private final UUID stationaryElementId;

		private TileInfo(int gridX, int gridY,
				Class<? extends Terrain> terrainClass, UUID terrainId,
				Class<? extends StationaryElement> stationaryElementClass,
				UUID stationaryElementId)
		{
			this.gridX = gridX;
			this.gridY = gridY;
			this.terrainClass = terrainClass;
			this.terrainId = terrainId;
			this.stationaryElementClass = stationaryElementClass;
			this.stationaryElementId = stationaryElementId;
		}

		/**
		 * Returns the grid x coordinate.
		 * 
		 * @return the grid x coordinate.
		 */
		private int getGridX()
		{
			return gridX;
		}

		/**
		 * Returns the grid y coordinate.
		 * 
		 * @return the grid y coordinate.
		 */
		private int getGridY()
		{
			return gridY;
		}

		/**
		 * Returns the terrain's id.
		 * 
		 * @return the terrain's id.
		 */
		private UUID getTerrainId()
		{
			return terrainId;
		}

		/**
		 * Returns the terrain class.
		 * 
		 * @return the terrain class.
		 */
		private Class<? extends Terrain> getTerrainClass()
		{
			return terrainClass;
		}

		/**
		 * Returns the stationary element's id.
		 * 
		 * @return the stationary element's id.
		 */
		private UUID getStationaryElementId()
		{
			return stationaryElementId;
		}

		/**
		 * Returns the stationary element class, or null if it does not exist.
		 * 
		 * @return the stationary element class, or null if it does not exist.
		 */
		private Class<? extends StationaryElement> getStationaryElementClass()
		{
			return stationaryElementClass;
		}
	}
}
