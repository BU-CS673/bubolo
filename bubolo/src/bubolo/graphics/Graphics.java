package bubolo.graphics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

/**
 * The top-level class for the Graphics system.
 * @author BU673 - Clone Industries
 */
public class Graphics
{
	public static final String TEXTURE_PATH = "res/textures/";
	
	// Stores the textures, ensuring that only one is needed for all instances
	// of a given sprite. 
	private static Map<String, Texture> textures = 
			new HashMap<String, Texture>();
	
	/**
	 * Returns a texture from a path. Ensures that the same texture isn't
	 * store multiple times. Will load the file if it has not yet been loaded.
	 * @param path the path to the texture file.
	 * @return the requested texture.
	 * @throws FileNotFoundException if the file is not found on the file system
	 * (can only occur if the file has not yet been loaded).
	 */
	public static Texture getTexture(String path)
	{
		Texture texture = textures.get(path);
		if (texture == null)
		{
			texture = new Texture(new FileHandle(new File(path)));
			textures.put(path, texture);
		}
		
		return texture;
	}
	
	public static void dispose()
	{
		for (Texture texture : textures.values())
		{
			texture.dispose();
		}
	}
}
