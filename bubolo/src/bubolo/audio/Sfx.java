package bubolo.audio;

import bubolo.audio.sfx.CannonFired;
import bubolo.audio.sfx.Explosion;
import bubolo.audio.sfx.TankHit;

/**
 * Stores instances of the sound effects.
 * 
 * @author BU673 - Clone Industries
 */
public class Sfx
{
	public static final String SFX_PATH = "res/sfx/";
	
	/**
	 * An explosion sound effect.
	 */
	public static final SoundEffect EXPLOSION = new Explosion();
	
	/**
	 * A tank hit sound effect.
	 */
	public static final SoundEffect TANK_HIT = new TankHit();
	
	/**
	 * A cannon fired sound effect.
	 */
	public static final SoundEffect CANNON_FIRED = new CannonFired();
}
