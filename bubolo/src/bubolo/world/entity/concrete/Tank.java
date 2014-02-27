package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.Actor;

/**
 * The tank, which may be controlled by a local player, a networked player, or an AI bot.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Tank extends Actor
{
	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 457933513574468829L;
	
	/**
	 * Used to flag as the local tank object.
	 */
	private boolean isLocal;

	/**
	 * Construct a new Tank with a random UUID.
	 */
	public Tank()
	{
		super();
	}

	/**
	 * Construct a new Tank with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tank.
	 */
	public Tank(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Getter for the isLocal member. Returns if the object is flagged as the local tank.
	 * @return
	 */
	public boolean isLocal()
	{
		return isLocal;
	}
	

	// TODO: Add Tank functionality!
}
