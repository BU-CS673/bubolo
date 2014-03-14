package bubolo.audio.sfx;

import java.io.File;

import bubolo.audio.SoundEffect;

import com.badlogic.gdx.files.FileHandle;

/**
 * A tank hit sound effect.
 * 
 * @author BU673 - Clone Industries
 */
public class TankHit extends SoundEffect
{
	/**
	 * Constructs a tank hit sound effect. External systems should not 
	 * construct <code>SoundEffect</code>s directly.
	 */
	TankHit()
	{
		FileHandle soundFile = new FileHandle(new File(path)));
	}
}
