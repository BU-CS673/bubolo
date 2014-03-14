package bubolo.audio;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 * A road built sound effect.
 * 
 * @author BU673 - Clone Industries
 */
public class RoadBuiltSfx extends SoundEffect
{
	/**
	 * Constructs a road built sound effect. External systems should not 
	 * construct <code>SoundEffect</code>s directly.
	 */
	RoadBuiltSfx()
	{
		FileHandle soundFile = new FileHandle(new File(Audio.SFX_PATH + "road_built.ogg"));
		Sound sound = Gdx.audio.newSound(soundFile);
		setSound(sound);
	}
}
