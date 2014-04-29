package bubolo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import bubolo.world.GameWorld;
import bubolo.world.Tile;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.StationaryElement;
import bubolo.world.entity.Terrain;
import bubolo.world.entity.concrete.*;

/**
 * The Parser object. The game utility to translate a give map file into a game world to be
 * executed.
 * 
 * @author BU673 - Clone Productions
 */

public class Parser
{

	private static Parser currentParser = null;

	/**
	 * Parser is a singleton object. No need for multiple parsers within one instance of the
	 * application.
	 */
	protected Parser()
	{

	}

	/**
	 * Generates new parser or returns existing object. Lazy instantiation is used.
	 * 
	 * @return either a new parser if one has not been created previously or a the previously
	 *         created instance
	 */
	public static Parser getInstance()
	{
		if (currentParser == null)
		{
			currentParser = new Parser();
		}

		return currentParser;
	}

	/**
	 * Define a Path to a valid map to parse and receive a World based on the given map.
	 * 
	 * @param mapPath
	 *            the Path of a map to be parsed into a world
	 * @return a World representation of the map file that was parsed
	 * @throws ParseException
	 *             for invalid JSON files
	 * @throws IOException when 
	 * @throws org.json.simple.parser.ParseException 
	 */
	public World parseMap(Path mapPath) throws IOException, ParseException
	{
		int mapHeight = 0;
		int mapWidth = 0;
		Object obj = null;
		JSONObject layerObject = null;
		JSONArray tileData = null;
		JSONArray layerArray = null;
		World world = null;

		Charset charset = Charset.forName("US-ASCII");

		try (BufferedReader reader = Files.newBufferedReader(mapPath, charset);)
		{
			JSONParser parser = new JSONParser();
			obj = parser.parse(reader);

			JSONObject jsonObject = (JSONObject)obj;

			mapHeight = (int)((long)jsonObject.get("height"));
			mapWidth = (int)((long)jsonObject.get("width"));

			Tile[][] mapTiles = new Tile[mapWidth][mapHeight];

			layerArray = (JSONArray)jsonObject.get("layers");

			layerObject = (JSONObject)layerArray.get(0);
			tileData = (JSONArray)layerObject.get("data");
			String dataString = null;
			world = new GameWorld(Coordinates.TILE_TO_WORLD_SCALE * mapWidth,
					Coordinates.TILE_TO_WORLD_SCALE * mapHeight);

			for (int i = 0; i < mapHeight; i++)
			{
				for (int j = 0; j < mapWidth; j++)
				{
					dataString = tileData.get(i * mapWidth + j).toString();
					int tileYIndex = mapHeight - i - 1;
					mapTiles[j][tileYIndex] = new Tile(j, tileYIndex, (Terrain)world.addEntity(
							layerOneSwitch(dataString)).setRotation((float)Math.PI / 2));
				}
			}

			if (layerArray.size() > 1)
			{
				layerObject = (JSONObject)layerArray.get(1);
				tileData = (JSONArray)layerObject.get("data");

				for (int i = 0; i < mapHeight; i++)
				{
					for (int j = 0; j < mapWidth; j++)
					{
						dataString = tileData.get(i * mapWidth + j).toString();
						if (layerTwoSwitch(dataString) != null)
						{
							int tileYIndex = mapHeight - i - 1;
							if (mapTiles[j][tileYIndex].getTerrain().getClass() == Road.class)
							{
								world.removeEntity(mapTiles[j][tileYIndex].getTerrain());
								mapTiles[j][tileYIndex].setTerrain(world.addEntity(Grass.class));
							}
							if (mapTiles[j][tileYIndex].getTerrain().getClass() == Water.class)
							{
								world.removeEntity(mapTiles[j][tileYIndex].getTerrain());
								mapTiles[j][tileYIndex].setTerrain(world.addEntity(Grass.class));
							}
							mapTiles[j][tileYIndex].setElement((StationaryElement)world.addEntity(
									layerTwoSwitch(dataString)).setRotation(((float)Math.PI / 2)));
						}
					}
				}
				
				if (layerArray.size() > 2)
				{
					layerObject = (JSONObject)layerArray.get(2);
					tileData = (JSONArray)layerObject.get("data");

					for (int i = 0; i < mapHeight; i++)
					{
						for (int j = 0; j < mapWidth; j++)
						{
							dataString = tileData.get(i * mapWidth + j).toString();
							if (layerThreeSwitch(dataString) != null)
							{
								Entity entity = world.addEntity(layerThreeSwitch(dataString));
								int tileYIndex = mapHeight - i - 1;
								entity.setParams(j * Coordinates.TILE_TO_WORLD_SCALE, 
										tileYIndex * Coordinates.TILE_TO_WORLD_SCALE, 
										((float)Math.PI / 2.f));
							}
						}
					}
				}
			}

			world.setMapTiles(mapTiles);
		}


		return world;
	}

	/**
	 * The following switches are used to more clearly show the logic for deciding what object the
	 * mapParser will create.
	 * 
	 * @param input
	 *            string to convert to a class object
	 * @return a class based upon the input string
	 * @throws InvalidMapException
	 *             if a string doesn't match a valid class for the first layer in the map
	 */
	private static Class<? extends Terrain> layerOneSwitch(String input) throws InvalidMapException
	{
		switch (input)
		{
		case "1":
			return Grass.class;

		case "2":
			return Swamp.class;

		case "3":
			return Water.class;

		case "4":
			return DeepWater.class;

		case "5":
			return Road.class;
			
		case "6":
			return Crater.class;

		case "7":
			return Rubble.class;

		default:
			throw new InvalidMapException("Invalid or missing terrain tile within map file");
		}
	}

	/**
	 * The following switches are used to more clearly show the logic for deciding what object the
	 * mapParser will create.
	 * 
	 * @param input
	 *            string to convert to a class object
	 * @return a class based upon the input string
	 * @throws InvalidMapException
	 *             if a string doesn't match a valid class for the first layer in the map
	 */
	private static Class<? extends Entity> layerTwoSwitch(String input) throws InvalidMapException
	{
		switch (input)
		{
		case "8":
			return Pillbox.class;

		case "9":
			return Tree.class;

		case "10":
			return Mine.class;

		case "11":
			return Wall.class;

		case "12":
			return Base.class;
			
		case "0":
			return null;

		default:
			throw new InvalidMapException("Invalid Stationary Element type within map file");
		}
	}
	
	/**
	 * Switch case for spawn layer of the map
	 * @param input a string to parse against
	 * @return either a Spawn class or null 
	 * @throws InvalidMapException thrown if the map contains an error in layer three
	 */
	private static Class<Spawn> layerThreeSwitch(String input) throws InvalidMapException
	{
		
		switch (input)
		{
		case "13":
			return Spawn.class;
					
		case "0":
			return null;
			
		default:
			System.out.println(input);
			throw new InvalidMapException("Invalid tile in Spawn Layer");
		}
				
	}
}
