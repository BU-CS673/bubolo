package bubolo.audio;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 * A tank drowned sound effect.
 * 
 * @author BU673 - Clone Industries
 */
public class TankDrownedSfx extends SoundEffect
{
	/**
	 * Constructs a tank drowned sound effect. External systems should not 
	 * construct <code>SoundEffect</code>s directly.
	 */
	TankDrownedSfx()
	{
		try
		{
			FileHandle soundFile = new FileHandle(new File(Audio.SFX_PATH + "tank_drowned.ogg"));
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
