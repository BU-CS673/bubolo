package bubolo.audio;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 * A pillbox hit sound effect.
 * 
 * @author BU673 - Clone Industries
 */
public class PillboxHitSfx extends SoundEffect
{
	/**
	 * Constructs a pillbox hit sound effect. External systems should not 
	 * construct <code>SoundEffect</code>s directly.
	 */
	PillboxHitSfx()
	{
		try
		{
			FileHandle soundFile = new FileHandle(new File(Audio.SFX_PATH + "pillbox_hit.wav"));
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
