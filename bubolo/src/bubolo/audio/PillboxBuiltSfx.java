package bubolo.audio;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 * A pillbox built sound effect.
 * 
 * @author BU673 - Clone Industries
 */
public class PillboxBuiltSfx extends SoundEffect
{
	/**
	 * Constructs a pillbox built sound effect. External systems should not 
	 * construct <code>SoundEffect</code>s directly.
	 */
	PillboxBuiltSfx()
	{
		FileHandle soundFile = new FileHandle(new File(Audio.SFX_PATH + "pillbox_built.ogg"));
		Sound sound = Gdx.audio.newSound(soundFile);
		setSound(sound);
	}
}
