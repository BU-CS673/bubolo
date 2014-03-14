package bubolo.audio;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 * An engineer killed sound effect.
 * 
 * @author BU673 - Clone Industries
 */
public class EngineerKilledSfx extends SoundEffect
{
	/**
	 * Constructs an engineer killed sound effect. External systems should not 
	 * construct <code>SoundEffect</code>s directly.
	 */
	EngineerKilledSfx()
	{
		FileHandle soundFile = new FileHandle(new File(Audio.SFX_PATH + "engineer_killed.ogg"));
		Sound sound = Gdx.audio.newSound(soundFile);
		setSound(sound);
	}
}
