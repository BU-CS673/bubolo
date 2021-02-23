package bubolo.map;

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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	private List<Tileset> tilesets;
	
	public MapImporter() {
		// Add the known map tiles here.
		
		Tileset terrain = new Tileset("bubolo_tilset_terrain");
		terrain.tiles.put(0, world -> world.addEntity(Grass.class));
		terrain.tiles.put(1, world -> world.addEntity(Swamp.class));
		terrain.tiles.put(2, world -> world.addEntity(Water.class));
		terrain.tiles.put(3, world -> world.addEntity(DeepWater.class));
		terrain.tiles.put(4, world -> world.addEntity(Road.class));
		terrain.tiles.put(5, world -> world.addEntity(Crater.class));
		terrain.tiles.put(6, world -> world.addEntity(Rubble.class));
		tilesets.add(terrain);
		
		Tileset stationaryElements = new Tileset("bubolo_tilset_stationaryElements");
		stationaryElements.tiles.put(0, world -> world.addEntity(Pillbox.class));
		stationaryElements.tiles.put(1, world -> world.addEntity(Tree.class));
		stationaryElements.tiles.put(2, world -> world.addEntity(Mine.class));
		stationaryElements.tiles.put(3, world -> world.addEntity(Wall.class));
		stationaryElements.tiles.put(4, world -> world.addEntity(Base.class));
		stationaryElements.tiles.put(5, world -> world.addEntity(Spawn.class));
		tilesets.add(stationaryElements);
	}
	
	/**
	 * The top-level Tiled map keys that are relevant to us.
	 */
	enum Key implements JsonKey {
		MapHeight("height"),
		MapWidth("width"),
		Tilesets("tilesets"),
		Layers("layers"),
		Data("data");
		
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
	
	public World importJsonMap(Path mapPath) throws IOException {
		try (BufferedReader reader = Files.newBufferedReader(mapPath)) {
			JsonObject jsonTiledMap = (JsonObject) Jsoner.deserialize(reader);
			
			setTilesetFirstGids(jsonTiledMap);
			
			int mapHeightTiles = jsonTiledMap.getInteger(Key.MapHeight);
			int mapWidthTiles = jsonTiledMap.getInteger(Key.MapWidth);
			
			setTilesetFirstGids(jsonTiledMap);
			
			
		} catch (JsonException e) {
			throw new InvalidMapException("Error parsing the json map file.", e);
		}
	}
	
	void setTilesetFirstGids(JsonObject jsonTiledMap) {
		JsonArray jsonTilesets = (JsonArray) jsonTiledMap.get(Key.Layers.toString());
		
		// TODO: Import the tiled first gids from the tilesets json array.
	}
}
