package bubolo.audio;

import java.util.HashMap;
import java.util.Map;

import bubolo.audio.sfx.SoundEffect;

import com.badlogic.gdx.audio.Sound;

/**
 * The top-level class for the Sound system.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Audio
{
	// Stores the sounds, ensuring that only one is needed for all instances. 
	private static Map<String, Sound> sounds = new HashMap<String, Sound>();
	
	public static void play(SoundEffect soundEffect)
	{
		
	}
	
	public static void startMusic()
	{
		
	}
	
	public static void stopMusic()
	{
		
	}
	
	public static void setSoundEffectVolume(int volume)
	{
		
	}
	
	public static int getSoundEffectVolume()
	{
		
	}
	
	public static void setMusicVolume(int volume)
	{
		
	}
	
	public static int getMusicVolume()
	{
		
	}
}
