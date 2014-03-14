package bubolo.audio;


import com.google.common.base.Preconditions;

/**
 * The top-level class for the Sound system.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Audio
{
	private static int soundEffectVolume;
	private static int musicVolume;
	
	public static void play(SoundEffect soundEffect)
	{
		soundEffect.play(soundEffectVolume);
	}
	
	public static void startMusic()
	{
		// TODO: Implement this.
	}
	
	public static void stopMusic()
	{
		// TODO: Implement this.
	}
	
	public static void setSoundEffectVolume(int volume)
	{
		Preconditions.checkArgument(volume >= 0, "Sound effect volume was less than zero: %s", volume);
		Preconditions.checkArgument(volume <= 100, "Sound effect volume was greater than 100: %s", volume);
		
		soundEffectVolume = volume;
	}
	
	public static int getSoundEffectVolume()
	{
		return soundEffectVolume;
	}
	
	public static void setMusicVolume(int volume)
	{
		Preconditions.checkArgument(volume >= 0, "Music volume was less than zero: %s", volume);
		Preconditions.checkArgument(volume <= 100, "Music volume was greater than 100: %s", volume);
		
		musicVolume = volume;
	}
	
	public static int getMusicVolume()
	{
		return musicVolume;
	}
}
