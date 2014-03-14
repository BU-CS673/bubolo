package bubolo.audio;


/**
 * Stores instances of the sound effects.
 * 
 * @author BU673 - Clone Industries
 */
public abstract class Sfx
{
	/**
	 * An explosion sound effect.
	 */
	public static final SoundEffect EXPLOSION = new ExplosionSfx();
	
	/**
	 * A tank hit sound effect.
	 */
	public static final SoundEffect TANK_HIT = new TankHitSfx();
	
	/**
	 * A cannon fired sound effect.
	 */
	public static final SoundEffect CANNON_FIRED = new CannonFiredSfx();
}
