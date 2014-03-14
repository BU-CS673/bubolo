package bubolo.audio;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 * A wall built sound effect.
 * 
 * @author BU673 - Clone Industries
 */
public class WallBuiltSfx extends SoundEffect
{
	/**
	 * Constructs a wall built sound effect. External systems should not 
	 * construct <code>SoundEffect</code>s directly.
	 */
	WallBuiltSfx()
	{
		try
		{
			FileHandle soundFile = new FileHandle(new File(Audio.SFX_PATH + "wall_built.ogg"));
			Sound sound = Gdx.audio.newSound(soundFile);
			setSound(sound);
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
			throw e;
		}
	}
}
