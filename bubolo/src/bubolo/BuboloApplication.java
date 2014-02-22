package bubolo;

import bubolo.graphics.Graphics;
import bubolo.world.GameWorld;
import bubolo.world.World;

/**
 * The Game: this is where the subsystems are initialized, as well as where
 * the main game loop is. 
 * @author BU CS673 - Clone Productions
 */
public class BuboloApplication implements GameApplication
{
	private int windowWidth;
	private int windowHeight;
	
	private Graphics graphics;
	private World world;
	
	private long lastUpdate;
	
	private boolean ready;
	
	/**
	 * The number of game ticks (calls to <code>update</code>) per second.
	 */
	public static final int TICKS_PER_SECOND = 30;
	
	/**
	 * The number of milliseconds per game tick.
	 */
	public static final float MILLIS_PER_TICK = 1000 / TICKS_PER_SECOND;
	
	/**
	 * Constructs an instance of the game application. Only one instance should 
	 * ever exist.
	 * @param windowWidth the width of the window.
	 * @param windowHeight the height of the window.
	 */
	public BuboloApplication(int windowWidth, int windowHeight)
	{
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
	}
	
	/**
	 * Called to import a map from a tiled JSON file
	 */
	private void parser()
	{
		int mapHeight = 0;
		int mapWidth = 0;
		Object obj = null;
		JSONObject layerObject = null;
		JSONArray tileData = null;
		JSONArray layerArray = null;
		
		// TODO: we need a way to determine the size of the game map. Perhaps we can have a default constructor,
		// and then the map loader or creator could set the size.

		/*
		 *  TODO: Modify the path line to choose from an assortment of maps either randomly or user selected iff we add more maps. 
		 */
		
		Path path = FileSystems.getDefault().getPath("res/maps", "Map 1.json");
		Charset charset = Charset.forName("US-ASCII");
		BufferedReader reader = null;		
		
		try
		{
			reader = Files.newBufferedReader(path, charset);
			JSONParser parser = new JSONParser();
			obj = parser.parse(reader);
			reader.close();
			JSONObject jsonObject = (JSONObject) obj;		
			mapHeight = (int) ((long) jsonObject.get("height"));
			mapWidth = (int) ((long) jsonObject.get("width"));
			layerArray = (JSONArray) jsonObject.get("layers");
			layerObject = (JSONObject) layerArray.get(0);
			tileData = (JSONArray) layerObject.get("data");
			
			world = new GameWorld(mapHeight, mapWidth);
			
			for(int i = 0; i < mapHeight; i++)
			{
				for(int j = 0; j < mapWidth; j++)
				{
					/*
					 * TODO: Add cases to interpret all of the tileset ID's from tiled.
					 * Convert string outputs to generate "tiles" within the map
					 */
					
					switch (tileData.get(i * mapWidth + j).toString())
					{
						case "1":
							world.addEntity(new Grass());
							break;
							
						default:
							break;
					}
				}
			}
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	public boolean isReady()
	{
		return ready;
	}

	/**
	 * Create anything that relies on graphics, sound, windowing, or input devices here.
	 * @see <a href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a> 
	 */
	@Override
	public void create()
	{
		graphics = new Graphics(windowWidth, windowHeight);
		
		// TODO: we need a way to determine the size of the game map. Perhaps we can have a default constructor,
		// and then the map loader or creator could set the size.
		world = new GameWorld(500, 500);
		
		// TODO: add other systems here.
		
		ready = true;
	}
	
	/**
	 * Called automatically by the rendering library.
	 * @see <a href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a>
	 */
	@Override
	public void render()
	{
		graphics.draw(world);
		
		// Ensure that the world is only updated as frequently as MILLIS_PER_TICK. 
		long currentMillis = System.currentTimeMillis();
		if (currentMillis > (lastUpdate + MILLIS_PER_TICK))
		{
			world.update();
			lastUpdate = currentMillis;
		}
	}
	
	/**
	 * Called when the application is destroyed.
	 * @see <a href="http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/ApplicationListener.html">ApplicationListener</a>
	 */
	@Override
	public void dispose()
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void resume()
	{
	}
}
