package bubolo.audio;


/**
 * Stores instances of the sound effects. Sound effects should be played in the follow way:<br><br>
 * <code>
 * Audio.play(Sfx.CANNON_FIRED);
 * </code>
 * 
 * @author BU673 - Clone Industries
 */
public abstract class Sfx
{	
	/**
	 * A cannon fired sound effect.
	 */
	public static final SoundEffect CANNON_FIRED = new CannonFiredSfx();
	
	/**
	 * An engineer killed sound effect.
	 */
	public static final SoundEffect ENGINEER_KILLED = new EngineerKilledSfx();
	
	/**
	 * An explosion sound effect.
	 */
	public static final SoundEffect EXPLOSION = new ExplosionSfx();
	
	/**
	 * A mine explosion sound effect.
	 */
	public static final SoundEffect MINE_EXPLOSION = new MineExplosionSfx();
	
	/**
	 * A pillbox built sound effect.
	 */
	public static final SoundEffect PILLBOX_BUILT = new PillboxBuiltSfx();
	
	/**
	 * A pillbox hit sound effect.
	 */
	public static final SoundEffect PILLBOX_HIT = new PillboxHitSfx();
	
	/**
	 * A road built sound effect.
	 */
	public static final SoundEffect ROAD_BUILT = new RoadBuiltSfx();
	
	/**
	 * A tank drowned sound effect.
	 */
	public static final SoundEffect TANK_DROWNED = new TankDrownedSfx();
	
	/**
	 * A tank explosion sound effect.
	 */
	public static final SoundEffect TANK_EXPLOSION = new TankExplosionSfx();
	
	/**
	 * A tank hit sound effect.
	 */
	public static final SoundEffect TANK_HIT = new TankHitSfx();
	
	/**
	 * A tank in shallow water sound effect.
	 */
	public static final SoundEffect TANK_IN_SHALLOW_WATER = new TankInShallowWaterSfx();
	
	/**
	 * A tree gathered sound effect.
	 */
	public static final SoundEffect TREE_GATHERED = new TreeGatheredSfx();
	
	/**
	 * A tree hit sound effect.
	 */
	public static final SoundEffect TREE_HIT = new TreeHitSfx();
	
	/**
	 * A wall built sound effect.
	 */
	public static final SoundEffect WALL_BUILT = new WallBuiltSfx();
	
	/**
	 * A wall hit sound effect.
	 */
	public static final SoundEffect WALL_HIT = new WallHitSfx();
	
	/**
	 * Forces all sounds files to be loaded.
	 */
	static void initialize()
	{
	}
}
