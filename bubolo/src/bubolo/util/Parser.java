package bubolo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
	 * @returns either a new parser if one has not been created previously or a the previously
	 *          created instance
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
	 */
	public World parseMap(Path mapPath) throws ParseException
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
					Coordinates.TILE_TO_WORLD_SCALE
							* mapHeight);

			for (int i = 0; i < mapHeight; i++)
			{
				for (int j = 0; j < mapWidth; j++)
				{
					dataString = tileData.get(i * mapWidth + j).toString();
					int tileYIndex = mapHeight - i - 1;
					mapTiles[j][tileYIndex] = new Tile(
							j,
							tileYIndex,
							(Terrain)world.addEntity(layerOneSwitch(dataString)).setRotation((float)Math.PI / 2));
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
							mapTiles[j][tileYIndex].setElement((StationaryElement)world
									.addEntity(layerTwoSwitch(dataString)).setRotation(((float)Math.PI / 2)));
						}
					}
				}

			}

			world.setMapTiles(mapTiles);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		catch (org.json.simple.parser.ParseException e)
		{
			e.printStackTrace();
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
	 * @throws an
	 *             InvalidMapException if a string doesn't match a valid class for the first layer
	 *             in the map
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

		default:
			throw new InvalidMapException("Invalid terrain type within map file");
		}
	}

	/**
	 * The following switches are used to more clearly show the logic for deciding what object the
	 * mapParser will create.
	 * 
	 * @param input
	 *            string to convert to a class object
	 * @return a class based upon the input string
	 * @throws an
	 *             InvalidMapException if a string doesn't match a valid class for the first layer
	 *             in the map
	 */
	private static Class<? extends Entity> layerTwoSwitch(String input) throws InvalidMapException
	{
		switch (input)
		{
		case "6":
			return Pillbox.class;

		case "7":
			return Tree.class;

		case "8":
			return Mine.class;

		case "9":
			return Wall.class;

		case "10":
			return Base.class;

		case "11":
			return Crater.class;

		case "12":
			return Rubble.class;
			/*
			 * case "13": return PlayerSpawn.class;
			 */
		case "0":
			return null;

		default:
			throw new InvalidMapException("Invalid terrain type within map file");
		}
	}
}
