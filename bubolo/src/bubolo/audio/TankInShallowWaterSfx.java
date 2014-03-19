package bubolo.audio;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 * A tank in shallow water sound effect.
 * 
 * @author BU673 - Clone Industries
 */
public class TankInShallowWaterSfx extends SoundEffect
{
	/**
	 * Constructs a tank in shallow water sound effect. External systems should not 
	 * construct <code>SoundEffect</code>s directly.
	 */
	TankInShallowWaterSfx()
	{
		try
		{
			FileHandle soundFile = new FileHandle(new File(Audio.SFX_PATH + "tank_in_shallow_water.ogg"));
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
