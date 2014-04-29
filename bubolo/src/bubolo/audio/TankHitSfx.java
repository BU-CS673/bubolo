package bubolo.audio;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 * A tank hit sound effect.
 * 
 * @author BU673 - Clone Industries
 */
public class TankHitSfx extends SoundEffect
{
	/**
	 * Constructs a tank hit sound effect. External systems should not 
	 * construct <code>SoundEffect</code>s directly.
	 */
	TankHitSfx()
	{
		try
		{
			FileHandle soundFile = new FileHandle(new File(Audio.SFX_PATH + "tank_hit.wav"));
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
