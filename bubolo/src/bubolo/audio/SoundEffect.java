package bubolo.audio;

import com.badlogic.gdx.audio.Sound;

/**
 * Interface for sound effects.
 * 
 * @author BU673 - Clone Industries
 */
public abstract class SoundEffect
{
	private Sound sound;
	
	/**
	 * Sets the sound instance.
	 * @param sound the sound instance.
	 */
	protected void setSound(Sound sound)
	{
		this.sound = sound;
	}
	
	/**
	 * Plays the sound.
	 * @param volume the sound's volume, from 0 (muted) to 100 (full volume).
	 */
	protected void play(int volume)
	{
		sound.play(volume / 100.f);
	}
	
	/**
	 * Disposes the loaded sound.
	 */
	protected void dispose()
	{
		sound.dispose();
	}
}
