package bubolo.audio;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;


/**
 * A cannon fired sound effect.
 * 
 * @author BU673 - Clone Industries
 */
public class CannonFiredSfx extends SoundEffect
{
	/**
	 * Constructs a cannon fired sound effect. External systems should not 
	 * construct <code>SoundEffect</code>s directly.
	 */
	CannonFiredSfx()
	{
		try
		{
			FileHandle soundFile = new FileHandle(new File(Audio.SFX_PATH + "explosion.ogg"));
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
