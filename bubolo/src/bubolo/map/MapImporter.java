package bubolo.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Function;

import org.apache.commons.lang3.tuple.Pair;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import bubolo.util.Coordinates;
import bubolo.world.GameWorld;
import bubolo.world.Tile;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.StationaryElement;
import bubolo.world.entity.Terrain;
import bubolo.world.entity.concrete.Base;
import bubolo.world.entity.concrete.Crater;
import bubolo.world.entity.concrete.DeepWater;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Mine;
import bubolo.world.entity.concrete.Pillbox;
import bubolo.world.entity.concrete.Road;
import bubolo.world.entity.concrete.Rubble;
import bubolo.world.entity.concrete.Spawn;
import bubolo.world.entity.concrete.Swamp;
import bubolo.world.entity.concrete.Tree;
import bubolo.world.entity.concrete.Wall;
import bubolo.world.entity.concrete.Water;

/**
 * Imports the Tiled-generated map.
 *
 * @author Christopher D. Canfield
 */
public class MapImporter {
	/**
	 * A tileset in the Tiled-generated map. The name and tiles are set before importing the map. The
	 * firstGid is set by the importer.
	 */
	private static class Tileset {
		final String name;
		/**
		 * Map of tile IDs to Entity creation functions. The tile ID is the tile's gid minus the tileset's firstGid.
		 */
		final Map<Integer, Function<World, Entity>> tiles = new HashMap<>();

		int firstGid;

		Tileset(String name) {
			this.name = name;
		}

		int lastGid() {
			return firstGid + tiles.size() - 1;
		}

		/**
		 * Whether the specified tile global ID belongs to this tileset.
		 * @param tileGid the tile global ID to check.
		 */
		boolean isGidInThisTileset(int tileGid) {
			// If the firstGid wasn't set, always return false. This happens when the tileset wasn't found
			// in the json map file. That's fine, because there are multiple tileset layouts available in the map files.
			if (firstGid == 0) {
				return false;
			}
			return tileGid >= firstGid && tileGid <= lastGid();
		}
	}

	/**
	 * Map import results, including the fully instantiated World and diagnostic information.
	 *
	 * @author Christopher D. Canfield
	 */
	public static class Diagnostics {
		Set<String> typesImported = new HashSet<>();

		int tileHeight;
		int tileWidth;

		int layerCount;
		int tilesetCount;

		public Set<String> typesImported() {
			return typesImported;
		}

		public int tileHeight() {
			return tileHeight;
		}

		public int tileWidth() {
			return tileWidth;
		}

		public int layerCount() {
			return layerCount;
		}

		public int tilesetCount() {
			return tilesetCount;
		}
	}

	private String DefaultExceptionMessage = "Error parsing the json map file";

	private Map<String, Tileset> tilesets = new HashMap<>();

	public MapImporter() {
		// Add the known map tiles here.

		Tileset stationaryElements = new Tileset("bubolo_tilset_stationaryElements");
		stationaryElements.tiles.put(0, world -> world.addEntity(Pillbox.class));
		stationaryElements.tiles.put(1, world -> world.addEntity(Tree.class));
		stationaryElements.tiles.put(2, world -> world.addEntity(Mine.class));
		stationaryElements.tiles.put(3, world -> world.addEntity(Wall.class));
		stationaryElements.tiles.put(4, world -> world.addEntity(Base.class));
		stationaryElements.tiles.put(5, world -> world.addEntity(Crater.class));
		stationaryElements.tiles.put(6, world -> world.addEntity(Rubble.class));
		stationaryElements.tiles.put(7, world -> world.addEntity(Spawn.class));
		tilesets.put(stationaryElements.name, stationaryElements);

		Tileset terrain = new Tileset("bubolo_tilset_terrain");
		terrain.tiles.put(0, world -> world.addEntity(Grass.class));
		terrain.tiles.put(1, world -> world.addEntity(Swamp.class));
		terrain.tiles.put(2, world -> world.addEntity(Water.class));
		terrain.tiles.put(3, world -> world.addEntity(DeepWater.class));
		terrain.tiles.put(4, world -> world.addEntity(Road.class));
		tilesets.put(terrain.name, terrain);

		// Tilesets for the original layout, which put rubble and craters in the terrain category.
		// Needed so the Everard Island map will continue to work.
		Tileset stationaryElements_oldLayout = new Tileset("bubolo_tilset_stationaryElements_oldLayout");
		stationaryElements_oldLayout.tiles.put(0, world -> world.addEntity(Pillbox.class));
		stationaryElements_oldLayout.tiles.put(1, world -> world.addEntity(Tree.class));
		stationaryElements_oldLayout.tiles.put(2, world -> world.addEntity(Mine.class));
		stationaryElements_oldLayout.tiles.put(3, world -> world.addEntity(Wall.class));
		stationaryElements_oldLayout.tiles.put(4, world -> world.addEntity(Base.class));
		stationaryElements_oldLayout.tiles.put(5, world -> world.addEntity(Spawn.class));
		tilesets.put(stationaryElements_oldLayout.name, stationaryElements_oldLayout);

		Tileset terrain_oldLayout = new Tileset("bubolo_tilset_terrain_oldLayout");
		terrain_oldLayout.tiles.put(0, world -> world.addEntity(Grass.class));
		terrain_oldLayout.tiles.put(1, world -> world.addEntity(Swamp.class));
		terrain_oldLayout.tiles.put(2, world -> world.addEntity(Water.class));
		terrain_oldLayout.tiles.put(3, world -> world.addEntity(DeepWater.class));
		terrain_oldLayout.tiles.put(4, world -> world.addEntity(Road.class));
		terrain_oldLayout.tiles.put(5, world -> world.addEntity(Crater.class));
		terrain_oldLayout.tiles.put(6, world -> world.addEntity(Rubble.class));
		tilesets.put(terrain_oldLayout.name, terrain_oldLayout);
	}

	/**
	 * The top-level Tiled map keys that are relevant to us.
	 */
	enum Key implements JsonKey {
		MapHeight("height"),
		MapWidth("width"),
		Tilesets("tilesets"),
		Layers("layers"),

		Data("data"),

		Name("name"),
		FirstGid("firstgid");

		private String key;

		private Key(String key) {
			this.key = key;
		}

		@Override
		public String getKey(){
			return key;
		}

		@Override
		public Object getValue(){
			return null;
		}
	}

	/**
	 * Imports the json Tiled map, and constructs a world from the data.
	 *
	 * @param mapPath path to the json Tiled map file.
	 * @return a results object that contains the world and diagnostic information.
	 * @throws IOException if the provided path can't be opened.
	 * @throws InvalidMapException if the json Tiled map is malformed.
	 */
	public Pair<World, Diagnostics> importJsonMapWithDiagnostics(Path mapPath, boolean disableGraphics) throws IOException {
		try (BufferedReader reader = Files.newBufferedReader(mapPath)) {
			return importJsonMapWithDiagnostics(reader, disableGraphics);
		}
	}

	public World importJsonMap(Path mapPath) throws IOException {
		try (BufferedReader reader = Files.newBufferedReader(mapPath)) {
			return importJsonMap(reader);
		}
	}

	public Pair<World, Diagnostics> importJsonMapWithDiagnostics(Reader mapReader, boolean disableGraphics) {
		return importMap(mapReader, disableGraphics);
	}

	public World importJsonMap(Reader mapReader) {
		return importMap(mapReader, false).getLeft();
	}



	/**
	 * Imports the json Tiled map, and constructs a world from the data.
	 *
	 * @param mapReader a reader that reads from a json Tiled map.
	 * @return a results object that contains the world and diagnostic information.
	 * @throws InvalidMapException if the json Tiled map is malformed.
	 */
	private Pair<World, Diagnostics> importMap(Reader mapReader, boolean disableGraphics) {
		try {
			JsonObject jsonTiledMap = (JsonObject) Jsoner.deserialize(mapReader);
			jsonTiledMap.requireKeys(Key.MapHeight, Key.MapWidth, Key.Tilesets, Key.Layers);

			Diagnostics diagnostics = new Diagnostics();

			setTilesetFirstGids(jsonTiledMap, diagnostics);

			// Get the map height and width, in tiles.
			int mapHeightTiles = diagnostics.tileHeight = jsonTiledMap.getInteger(Key.MapHeight);
			int mapWidthTiles = diagnostics.tileWidth = jsonTiledMap.getInteger(Key.MapWidth);
			Tile[][] mapTiles = new Tile[mapWidthTiles][mapHeightTiles];

			GameWorld world = new GameWorld(Coordinates.TILE_TO_WORLD_SCALE * mapWidthTiles,
					Coordinates.TILE_TO_WORLD_SCALE * mapHeightTiles);
			world.setGraphicsEnabled(!disableGraphics);

			JsonArray layers = (JsonArray) jsonTiledMap.get(Key.Layers.getKey());
			diagnostics.layerCount = layers.size();
			// Iterate through each map layer.
			for (int layerIndex = 0; layerIndex < layers.size(); layerIndex++) {
				JsonObject layer = (JsonObject) layers.get(layerIndex);
				JsonArray layerTiles = (JsonArray) layer.get(Key.Data.getKey());

				// Iterate through each tile GID in the map layer.
				for (int row = 0; row < mapHeightTiles; row++) {
					for (int col = 0; col < mapWidthTiles; col++) {
						int tileGid = layerTiles.getInteger(row * mapWidthTiles + col);
						addEntityIfGidRecognized(tileGid, world, mapTiles, mapHeightTiles, row, col, diagnostics);
					}
				}
			}

			return Pair.of(world, diagnostics);
		} catch (JsonException e) {
			throw new InvalidMapException(DefaultExceptionMessage, e);
		} catch (NoSuchElementException e) {
			throw new InvalidMapException(DefaultExceptionMessage + e.toString(), e);
		}
	}

	void setTilesetFirstGids(JsonObject jsonTiledMap, Diagnostics diagnostics) {
		JsonArray jsonTilesets = (JsonArray) jsonTiledMap.get(Key.Tilesets.getKey());
		if (jsonTilesets.size() < 2) {
			throw new InvalidMapException(DefaultExceptionMessage + " There should be two tilesets, but " + jsonTilesets.size() + " was found.");
		}

		for (Object ts : jsonTilesets) {
			JsonObject jsonTileset = (JsonObject) ts;
			jsonTileset.requireKeys(Key.Name, Key.FirstGid);

			String tilesetName = jsonTileset.getString(Key.Name);
			Tileset tileset = tilesets.get(tilesetName);
			if (tileset != null) {
				tileset.firstGid = jsonTileset.getInteger(Key.FirstGid);
				diagnostics.tilesetCount++;

			// Log a warning for unknown tileset, and then skip it.
			} else {
				System.out.println("[WARNING] Unknown tileset found in map file: " + tilesetName);
			}
		}
	}

	void addEntityIfGidRecognized(int tileGid, World world, Tile[][] mapTiles, int mapHeightTiles, int row, int col, Diagnostics diagnostics) {
		// Zero represents an empty space in the layer, so skip it if encountered.
		if (tileGid > 0) {
			// Check the tile GID against the known GIDs in each tileset.
			for (Tileset ts : tilesets.values()) {
				// Add the entity if it is known to this tileset.
				if (ts.isGidInThisTileset(tileGid)) {
					Entity entity = ts.tiles.get(tileGid - ts.firstGid).apply(world);
					// The game world is flipped from json map indexes.
					int gridY = mapHeightTiles - row - 1;
					int gridX = col;
					int posY = gridY * Coordinates.TILE_TO_WORLD_SCALE;
					int posX = gridX * Coordinates.TILE_TO_WORLD_SCALE;
					double rotation = Math.PI / 2.0;

					entity.setParams(posX, posY, rotation);

					if (entity instanceof Terrain terrain) {
						if (mapTiles[gridX][gridY] != null) {
							mapTiles[gridX][gridY].setTerrain(terrain);
						} else {
							mapTiles[gridX][gridY] = new Tile(gridX, gridY, terrain);
						}
					} else if (entity instanceof StationaryElement stationaryElement) {
						// If the tile under the stationary element is empty, create a grass terrain there.
						if (mapTiles[gridX][gridY] == null) {
							Grass grass = world.addEntity(Grass.class);
							mapTiles[gridX][gridY] = new Tile(gridX, gridY, grass);
						}
						mapTiles[gridX][gridY].setElement(stationaryElement);
					}

					diagnostics.typesImported.add(entity.getClass().getSimpleName());
				}
			}
		}
	}
}
