package bubolo.audio;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bubolo.util.GameLogicException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.google.common.base.Preconditions;

/**
 * The top-level class for the Sound system.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Audio implements Music.OnCompletionListener
{
	/**
	 * Instances of this class should not be directly constructed. 
	 */
	private Audio() {}
	
	/**
	 * The path to the music files.
	 */
	public static final String MUSIC_PATH = "res/music/";
	
	/**
	 * The path to the sound effect files.
	 */
	public static final String SFX_PATH = "res/sfx/";
	
	// The sound effects volume. The default is 50%.
	private static int soundEffectVolume = 50;
	// The music volume. The default is 50%.
	private static int musicVolume = 50;
	
	// A list of all music files.
	private static List<Music> music;
	// The index of the currently playing music file, or -1 if no music is playing. 
	private static int currentMusicFile = -1;
	
	// The music on completion listener. This is used when a song has finished playing.
	private static Music.OnCompletionListener musicOnCompletionListener = new Audio();
	
	private static SoundEffect lastSoundPlayed1;
	private static SoundEffect lastSoundPlayed2;
	private static long nextPlayTime;
	private static final long soundDelay = 80L;
	
	/**
	 * Loads all sounds files. Calling this isn't necessary, but there will be a slight pause when
	 * the first sound is played if this isn't called.
	 */
	public static void initialize()
	{
		Sfx.initialize();
	}
	
	/**
	 * Plays a sound effect. This should be called in the following way:<br><br>
	 * <code>Audio.play(Sfx.EXPLOSION);<br>
	 * Audio.play(Sfx.TANK_HIT);</code>
	 * @param soundEffect the sound effect to play.
	 */
	public static void play(SoundEffect soundEffect)
	{
		// Prevent the same sound from playing once per tick. This occurred because the mine explosion
		// lasts for multiple ticks in the world.
		if ((lastSoundPlayed1 != soundEffect && lastSoundPlayed2 != soundEffect) ||
			nextPlayTime < System.currentTimeMillis())
		{
			nextPlayTime = System.currentTimeMillis() + soundDelay;
			lastSoundPlayed2 = lastSoundPlayed1;
			lastSoundPlayed1 = soundEffect;
			soundEffect.play(soundEffectVolume);
		}
	}
	
	/**
	 * Starts the music. The audio system will continuously loop through all songs
	 * until <code>Audio.stopMusic()</code> is called. 
	 */
	public static void startMusic()
	{
		if (music == null)
		{
			loadMusic();
			if (music.size() < 2)
			{
				throw new GameLogicException("At least two songs must be specified.");
			}
		}
		
		currentMusicFile = 0;
		music.get(currentMusicFile).setVolume(musicVolume / 100.f);
		music.get(currentMusicFile).play();
		music.get(currentMusicFile).setOnCompletionListener(musicOnCompletionListener);
	}
	
	/**
	 * Loads all music files. Note that music files aren't actually stored in memory, unlike 
	 * sound effect files. Instead, they are streamed from disk as needed. 
	 */
	private static void loadMusic()
	{
		// TODO: update this with the correct file names.
		music = new ArrayList<Music>();
		
		try
		{
			music.add(Gdx.audio.newMusic(new FileHandle(new File(MUSIC_PATH + "Lessons-8bit.mp3"))));
			music.add(Gdx.audio.newMusic(new FileHandle(new File(MUSIC_PATH + "bolo_menu.mp3"))));
			//music.add(Gdx.audio.newMusic(new FileHandle(new File(MUSIC_PATH + "song2.ogg"))));
		}
		catch (Exception e)
		{
			
			System.out.println(e);
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * ***Testing only*** 
	 * Provides a hook to the Music OnCompletionListener for testing.
	 */
	static void testMusicOnCompletionListener()
	{
		if (music == null)
		{
			loadMusic();
		}
		
		musicOnCompletionListener.onCompletion(null);
	}
	
	/**
	 * Callback that is invoked when a music stream has completed.
	 */
	@Override
	public void onCompletion(Music completedMusic)
	{
		// Plays a randomly selected next song. The completed song will not be played multiple
		// times in a row. Note that this will not work with only one song.
		int nextSong = -1;
		while (nextSong == -1 || nextSong == currentMusicFile)
		{
			nextSong = (new Random()).nextInt(music.size());
		}
		
		music.get(nextSong).setVolume(musicVolume / 100.f);
		music.get(nextSong).play();
		
		setMusicFileIndex(nextSong);
	}
	
	private static void setMusicFileIndex(int index)
	{
		Preconditions.checkArgument(index >= 0, "Song index must be greater than zero: %s", index);
		Preconditions.checkArgument(index < music.size(), "song index exceeds music file count: %s", index);
		
		currentMusicFile = index;
	}
	
	/**
	 * Stops the music. There is no effect is no music is currently being played.
	 */
	public static void stopMusic()
	{
		if (music != null && currentMusicFile != -1)
		{
			music.get(currentMusicFile).stop();
			currentMusicFile = -1;
		}
	}
	
	/**
	 * Sets the sound effect volume, from 0 (mute) to 100 (max volume).
	 * @param volume the new sound effect volume, ranging from 0 to 100.
	 * @throws IllegalArgumentException if volume is less than 0 or greater than 100.
	 */
	public static void setSoundEffectVolume(int volume)
	{
		Preconditions.checkArgument(volume >= 0, "Sound effect volume was less than zero: %s", volume);
		Preconditions.checkArgument(volume <= 100, "Sound effect volume was greater than 100: %s", volume);
		
		soundEffectVolume = volume;
	}
	
	/**
	 * Gets the sound effect volume, in the range [0, 100].
	 * @return the sound effect volume.
	 */
	public static int getSoundEffectVolume()
	{
		return soundEffectVolume;
	}
	
	/**
	 * Sets the music volume, from 0 (mute) to 100 (max volume).
	 * @param volume the new music volume, ranging from 0 to 100.
	 * @throws IllegalArgumentException if volume is less than 0 or greater than 100.
	 */
	public static void setMusicVolume(int volume)
	{
		Preconditions.checkArgument(volume >= 0, "Music volume was less than zero: %s", volume);
		Preconditions.checkArgument(volume <= 100, "Music volume was greater than 100: %s", volume);
		
		musicVolume = volume;
	}
	
	/**
	 * Gets the music volume, in the range [0, 100].
	 * @return the music volume.
	 */
	public static int getMusicVolume()
	{
		return musicVolume;
	}
	
	/**
	 * Diposes all sound effects and music files.
	 */
	public static void dispose()
	{
		Sfx.CANNON_FIRED.dispose();
		Sfx.EXPLOSION.dispose();
		Sfx.ENGINEER_KILLED.dispose();
		Sfx.MINE_EXPLOSION.dispose();
		Sfx.PILLBOX_BUILT.dispose();
		Sfx.PILLBOX_HIT.dispose();
		Sfx.ROAD_BUILT.dispose();
		Sfx.TANK_DROWNED.dispose();
		Sfx.TANK_EXPLOSION.dispose();
		Sfx.TANK_HIT.dispose();
		Sfx.TANK_IN_SHALLOW_WATER.dispose();
		Sfx.TREE_GATHERED.dispose();
		Sfx.TREE_HIT.dispose();
		Sfx.WALL_BUILT.dispose();
		Sfx.WALL_HIT.dispose();
		
		if (music != null)
		{
			for (Music m : music)
			{
				m.stop();
				m.dispose();
			}
		}
	}
}
