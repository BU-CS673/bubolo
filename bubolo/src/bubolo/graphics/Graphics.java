package bubolo.graphics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bubolo.world.World;
import bubolo.world.entity.Entity;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The top-level class for the Graphics system.
 * @author BU CS673 - Clone Productions
 */
public class Graphics
{
	/**
	 * File path where textures are stored.
	 */
	public static final String TEXTURE_PATH = "res/textures/";
	
	/**
	 * The target number of draw ticks per second.
	 */
	public static final int TICKS_PER_SECOND = 60;
	
	/**
	 * The number of milliseconds per draw tick.
	 */
	public static final long MILLIS_PER_TICK = 1000 / TICKS_PER_SECOND;
	
	// Stores the textures, ensuring that only one is needed for all instances
	// of a given sprite. 
	private static Map<String, Texture> textures = new HashMap<String, Texture>();
	
	private SpriteBatch batch;
	private Camera camera;
	
	private Sprites spriteSystem;
	
	// The list of camera controllers.
	private List<CameraController> cameraControllers = new ArrayList<CameraController>();
	
	// Static reference to this object for the getInstance() method.
	private static Graphics instance = null; 
	
	/**
	 * Gets a reference to the Graphics system. The Graphics system must be 
	 * explicitly constructed using the <code>Graphics(width, height)</code> constructor
	 * before this is called, or an <code>IllegalStateException</code> will
	 * be thrown. This method is package-private, because only objects within
	 * the Graphics system should have access to it.
	 * @return a reference to the Graphics system.
	 * @throws IllegalStateException when the Graphics system has not been 
	 * explicitly constructed using the <code>Graphics(width, height)</code> constructor.
	 */
	static Graphics getInstance()
	{
		if (instance == null)
		{
			throw new IllegalStateException(
					"Graphics.getInstance cannot be called until the Graphics system has been explicitly constructed.");
		}
		return instance;
	}
	
	/**
	 * Returns a texture from a path. Ensures that the same texture isn't
	 * store multiple times. Will load the file if it has not yet been loaded.
	 * @param path the path to the texture file.
	 * @return the requested texture.
	 * @throws FileNotFoundException if the file is not found on the file system
	 * (can only occur if the file has not yet been loaded).
	 */
	static Texture getTexture(String path)
	{
		//TODO: Throw FileNotFoundException for this method or remove the @throws from the javadoc.
		Texture texture = textures.get(path);
		if (texture == null)
		{
			texture = new Texture(new FileHandle(new File(path)));
			textures.put(path, texture);
		}
		
		return texture;
	}
	
	/**
	 * Destroys all textures.
	 */
	public static void dispose()
	{
		for (Texture texture : textures.values())
		{
			texture.dispose();
		}
	}
	
	/**
	 * Creates the graphics system.
	 * @param windowWidth the width of the window, in pixels.
	 * @param windowHeight the height of the window, in pixels.
	 */
	public Graphics(int windowWidth, int windowHeight)
	{
		camera = new OrthographicCamera(windowWidth, windowHeight);
		batch = new SpriteBatch();
		spriteSystem = Sprites.getInstance();
		
		synchronized(Graphics.class)
		{
			Graphics.instance = this;
		}
	}
	
	/**
	 * Draws the entities that are within the camera's clipping boundary.
	 * @param world reference to the World Model object.
	 */
	public void draw(World world)
	{
		// 1. Get list of entities from the World/Model.
		List<Sprite<? extends Entity>> sprites = spriteSystem.getSprites();
		
		// 2. Perform clipping: remove entities that are outside of the bounding window.
		// TODO - Implement this
		 
		// 3. Sort list by entity.getSpriteId()
		// TODO - Implement this.
		
		// 4. Render sprites by layer.
		drawEntities(sprites, DrawLayer.TERRAIN);
		drawEntities(sprites, DrawLayer.TERRAIN_MODIFIERS);
		drawEntities(sprites, DrawLayer.OBJECTS);
		drawEntities(sprites, DrawLayer.TANKS);
		
		// Update the camera controller(s).
		for (CameraController c : cameraControllers)
		{
			c.update(world);
		}
	}
	
	/**
	 * Adds the specified camera controller.
	 * @param controller a camera controller. The update method will be called
	 * once per draw call.
	 */
	public void addCameraController(CameraController controller)
	{
		cameraControllers.add(controller);
	}
	
	/**
	 * Draw all entities in the specified layer.
	 * @param entities the list of entities.
	 * @param currentLayer the current layer to draw.
	 */
	private void drawEntities(List<Sprite<? extends Entity>> sprites, DrawLayer currentLayer)
	{
		if (sprites.size() == 0)
		{
			return;
		}
		
		Class<?> lastTexture = null;
		for (Sprite<? extends Entity> sprite : sprites)
		{
			if (sprite.getClass() != lastTexture)
			{
				if (lastTexture != null)
				{
					batch.end();
				}
				batch.begin();
			}
			sprite.draw(batch, camera, currentLayer);
			lastTexture = sprite.getClass();
		}
		batch.end();
	}
}
