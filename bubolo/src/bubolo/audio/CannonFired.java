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
public class CannonFired extends SoundEffect
{
	/**
	 * Constructs a cannon fired sound effect. External systems should not 
	 * construct <code>SoundEffect</code>s directly.
	 */
	CannonFired()
	{
		FileHandle soundFile = new FileHandle(new File(Sfx.SFX_PATH + "cannonfired.ogg"));
		Sound sound = Gdx.audio.newSound(soundFile);
		setSound(sound);
	}
}
