package bubolo.audio.sfx;

/**
 * Stores instances of the sound effects.
 * 
 * @author BU673 - Clone Industries
 */
public class Sfx
{
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
