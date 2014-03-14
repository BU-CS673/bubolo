package bubolo.audio;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 * A tree hit sound effect.
 * 
 * @author BU673 - Clone Industries
 */
public class TreeHitSfx extends SoundEffect
{
	/**
	 * Constructs a tree hit sound effect. External systems should not 
	 * construct <code>SoundEffect</code>s directly.
	 */
	TreeHitSfx()
	{
		FileHandle soundFile = new FileHandle(new File(Audio.SFX_PATH + "tree_hit.ogg"));
		Sound sound = Gdx.audio.newSound(soundFile);
		setSound(sound);
	}
}
