package bubolo.world.entity;

import java.util.UUID;

import bubolo.world.Damageable;

/**
 * Basic class representing MobileEntities that exhibit some kind of behavior in the game
 * world, such as tanks and humans.
 * 
 * @author BU CS673 - Clone Productions
 */
public abstract class Actor extends Entity implements Damageable
{
	private int hp, maxhp;
	private boolean alive = true;

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 6062132322107891442L;

	/**
	 * Construct a new Actor with a random UUID.
	 */
	public Actor()
	{
		super();
	}

	/**
	 * Construct a new Actor with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Actor.
	 */
	public Actor(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Actor setHP(int i)
	{
		hp = i;
		return this;
	}
	
	protected Actor setMaxHP(int i)
	{
		maxhp = i;
		return this;
	}

	@Override
	public int getHP()
	{
		return hp;
	}

	@Override
	public int getMaxHP()
	{
		//TODO setMaxHP
		return maxhp;
	}

	@Override
	public Actor modifyHP(int i)
	{
		hp += i;
		return this;
	}

	@Override
	public boolean isAlive()
	{
		return alive;
	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	// TODO: Add Actor functionality!

}
