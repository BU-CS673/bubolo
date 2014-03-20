package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.Effect;
import bubolo.audio.Audio;
import bubolo.audio.Sfx;
import bubolo.world.World;

/**
 * Bullets are shot by Tanks, and can cause damage to StationaryElements and other Actors.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Bullet extends Effect
{
	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = -9153862417398330898L;

	/**
	 * Construct a new Bullet with a random UUID.
	 */
	public Bullet()
	{
		this(false);
		setWidth(4);
		setHeight(8);
		setXOffset(0);
		setYOffset(0);

	}

	/**
	 * Package-private constructor for testing.
	 */
	Bullet(boolean noSound)
	{
		super();

		// Play cannon fired sound effect.
		if (!noSound)
		{
			Audio.play(Sfx.CANNON_FIRED);
		}
	}

	/**
	 * Construct a new Bullet with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tank.
	 */
	public Bullet(UUID id)
	{
		super(id);
		setWidth(4);
		setHeight(8);
		setXOffset(0);
		setYOffset(0);
		// Play cannon fired sound effect.
		Audio.play(Sfx.CANNON_FIRED);
	}

	// TODO: Add Bullet functionality!
}
