package bubolo.graphics;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class Graphics
{
	private static Map<String, Texture> textures = 
			new HashMap<String, Texture>();
	
	public static Texture getTexture(String path)
	{
		Texture texture = textures.get(path);
		if (texture == null)
		{
			texture = new Texture(new FileHandle(new File(path)));
		}
		
		return texture;
	}
}
