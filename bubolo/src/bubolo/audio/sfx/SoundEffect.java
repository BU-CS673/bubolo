package bubolo.audio.sfx;

/**
 * Interface for sound effects.
 * 
 * @author BU673 - Clone Industries
 */
public abstract class SoundEffect
{
	/**
	 * Plays the sound.
	 * @param volume the sound's volume, from 0 (muted) to 100 (full volume).
	 */
	protected abstract void play(int volume);
}
