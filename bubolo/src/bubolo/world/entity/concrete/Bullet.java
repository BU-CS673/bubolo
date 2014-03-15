package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.audio.Audio;
import bubolo.audio.Sfx;
import bubolo.world.World;
import bubolo.world.entity.Actor;

/**
 * Bullets are shot by Tanks, and can cause damage to StationaryElements and other Actors.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Bullet extends Actor
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
		super();
		
		// Play cannon fired sound effect.
		Audio.play(Sfx.CANNON_FIRED);
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

		// Play cannon fired sound effect.
		Audio.play(Sfx.CANNON_FIRED);
	}

	@Override
	public void update(World world)
	{
		// TODO Implement this.
	}

	// TODO: Add Bullet functionality!
}
