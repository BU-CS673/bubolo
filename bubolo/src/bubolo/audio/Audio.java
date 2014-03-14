package bubolo.audio;


import java.io.File;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.google.common.base.Preconditions;

/**
 * The top-level class for the Sound system.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Audio
{
	public static final String MUSIC_PATH = "/res/music/";
	
	public static final String SFX_PATH = "res/sfx/";
	
	private static int soundEffectVolume;
	private static int musicVolume;
	
	private static List<Music> music;
	private static int currentMusicFile;
	
	public static void play(SoundEffect soundEffect)
	{
		soundEffect.play(soundEffectVolume);
	}
	
	public static void startMusic()
	{
		if (music == null)
		{
			loadMusic();
		}
		
		// TODO: play the music; set callback handler for when song ends.
	}
	
	private static void loadMusic()
	{
		// TODO: update this with the correct file names.
		music.add(Gdx.audio.newMusic(new FileHandle(new File(MUSIC_PATH + "song1.ogg"))));
		music.add(Gdx.audio.newMusic(new FileHandle(new File(MUSIC_PATH + "song2.ogg"))));
	}
	
	public static void stopMusic()
	{
		music.get(currentMusicFile).stop();
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
	
	public static void dispose()
	{
		Sfx.CANNON_FIRED.dispose();
		Sfx.EXPLOSION.dispose();
		Sfx.TANK_HIT.dipose();
	}
}
