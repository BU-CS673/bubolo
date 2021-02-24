package bubolo.map;

import bubolo.util.Coordinates;
import bubolo.world.GameWorld;
import bubolo.world.World;
import bubolo.world.entity.Entity;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Function;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

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
		boolean isInThisTileset(int tileGid) {
			return tileGid >= firstGid && tileGid <= lastGid();
		}
	}
	
	/**
	 * Map import results, including the fully instantiated World and diagnostic information. 
	 *
	 * @author Christopher D. Canfield
	 */
	public static class Results {
		World world;
		
		Set<String> typesImported = new HashSet<>();
		
		int tileHeight;
		int tileWidth;
		
		int layerCount;
		int tilesetCount;
		
		public World world() {
			return world;
		}
		
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
		stationaryElements.tiles.put(5, world -> world.addEntity(Spawn.class));
		tilesets.put(stationaryElements.name, stationaryElements);
		
		Tileset terrain = new Tileset("bubolo_tilset_terrain");
		terrain.tiles.put(0, world -> world.addEntity(Grass.class));
		terrain.tiles.put(1, world -> world.addEntity(Swamp.class));
		terrain.tiles.put(2, world -> world.addEntity(Water.class));
		terrain.tiles.put(3, world -> world.addEntity(DeepWater.class));
		terrain.tiles.put(4, world -> world.addEntity(Road.class));
		terrain.tiles.put(5, world -> world.addEntity(Crater.class));
		terrain.tiles.put(6, world -> world.addEntity(Rubble.class));
		tilesets.put(terrain.name, terrain);
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
	public Results importJsonMap(Path mapPath) throws IOException {
		try (BufferedReader reader = Files.newBufferedReader(mapPath)) {
			return importJsonMap(reader);
		}
	}
	
	/**
	 * Imports the json Tiled map, and constructs a world from the data.
	 * 
	 * @param mapReader a reader that reads from a json Tiled map.
	 * @return a results object that contains the world and diagnostic information.
	 * @throws InvalidMapException if the json Tiled map is malformed. 
	 */
	public Results importJsonMap(Reader mapReader) {
		try {
			JsonObject jsonTiledMap = (JsonObject) Jsoner.deserialize(mapReader);
			jsonTiledMap.requireKeys(Key.MapHeight, Key.MapWidth, Key.Tilesets, Key.Layers);
			
			Results importResults = new Results();
			
			setTilesetFirstGids(jsonTiledMap, importResults);
			
			// Get the map height and width, in tiles.
			int mapHeightTiles = importResults.tileWidth = jsonTiledMap.getInteger(Key.MapHeight);
			int mapWidthTiles = importResults.tileHeight = jsonTiledMap.getInteger(Key.MapWidth);
			
			World world = new GameWorld(Coordinates.TILE_TO_WORLD_SCALE * mapWidthTiles, 
					Coordinates.TILE_TO_WORLD_SCALE * mapHeightTiles);
			importResults.world = world;
			
			// TODO (cdc - 2021-02-23): Iterate through the layers, and add each entity to the world.
			JsonArray layers = (JsonArray) jsonTiledMap.get(Key.Layers.getKey());
			importResults.layerCount = layers.size();
			for (int i = 0; i < layers.size(); i++) {
				JsonObject layer = (JsonObject) layers.get(i);
				JsonArray layerTiles = (JsonArray) layer.get(Key.Data.getKey());
				
			}
			
			return importResults;
		} catch (JsonException e) {
			throw new InvalidMapException(DefaultExceptionMessage, e);
		} catch (NoSuchElementException e) {
			throw new InvalidMapException(DefaultExceptionMessage + e.toString(), e);
		}
	}
	
	void setTilesetFirstGids(JsonObject jsonTiledMap, Results importResults) {
		JsonArray jsonTilesets = (JsonArray) jsonTiledMap.get(Key.Layers.getKey());
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
				importResults.tilesetCount++;
				
			// Log a warning for unknown tileset, and then skip it.
			} else {
				System.out.println("[WARNING] Unknown tileset found in map file: " + tilesetName);
			}
		}
	}
}
