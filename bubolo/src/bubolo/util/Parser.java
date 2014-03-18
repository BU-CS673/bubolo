package bubolo.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
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
import bubolo.world.entity.Terrain;
import bubolo.world.entity.concrete.*;

public class Parser
{
	private static Parser currentParser = null;
	
	protected Parser()
	{

	}
	
	public static Parser getInstance()
	{
		if(currentParser == null)
		{
			currentParser = new Parser();			
		}
		
		return currentParser;
	}
	
	public Tile[][] parseMap(World world, Path mapPath) throws ParseException
	{ 
		int mapHeight = 0;
		int mapWidth = 0;
		Object obj = null;
		JSONObject layerObject = null;
		JSONArray tileData = null;
		JSONArray layerArray = null;
		
		Charset charset = Charset.forName("US-ASCII");
				
		
		try (BufferedReader reader = Files.newBufferedReader(mapPath, charset);)
		{
			JSONParser parser = new JSONParser();
			obj = parser.parse(reader);
			JSONObject jsonObject = (JSONObject) obj;		
			mapHeight = (int) ((long) jsonObject.get("height"));
			mapWidth = (int) ((long) jsonObject.get("width"));
			Tile[][] mapTiles = new Tile[mapHeight][mapWidth];
			layerArray = (JSONArray) jsonObject.get("layers");
			layerObject = (JSONObject) layerArray.get(0);
			tileData = (JSONArray) layerObject.get("data");
			String dataString = null;
			Terrain newTerrain = null;
			Entity newEntity = null;
			
			world = new GameWorld(mapHeight, mapWidth);
			
			for(int i = 0; i < mapHeight; i++)
			{
				for(int j = 0; j < mapWidth; j++)
				{
					/*
					 * TODO: Add cases to interpret all of the tileset ID's from tiled.
					 * Convert string outputs to generate "tiles" within the map
					 */
							dataString = tileData.get(i * mapWidth + j).toString();
							newTerrain = world.addEntity(layerOneSwitch(dataString));
							newTerrain.setParams(i * 32, j * 32, 32, 32, 0);
							mapTiles[i][j] = new Tile(i, j, newTerrain);
							
							
				}
			}
			return mapTiles;
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		catch (org.json.simple.parser.ParseException e)
		{
			e.printStackTrace();
		}	
		
		return null;
	}
	
	private static Class<? extends Terrain> layerOneSwitch(String input)
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
			
		default:
			return Grass.class;
		}
	}
}
