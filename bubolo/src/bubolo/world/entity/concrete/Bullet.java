package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.Effect;

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
		super();
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
		// TODO Auto-generated constructor stub
	}

	// TODO: Add Bullet functionality!
}
