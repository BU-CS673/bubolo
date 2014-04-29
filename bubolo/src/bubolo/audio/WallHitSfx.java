package bubolo.audio;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 * A wall hit sound effect.
 * 
 * @author BU673 - Clone Industries
 */
public class WallHitSfx extends SoundEffect
{
	/**
	 * Constructs a wall hit sound effect. External systems should not 
	 * construct <code>SoundEffect</code>s directly.
	 */
	WallHitSfx()
	{
		try
		{
			FileHandle soundFile = new FileHandle(new File(Audio.SFX_PATH + "wall_hit.wav"));
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
