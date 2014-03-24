package bubolo.world.entity;

import java.util.UUID;

import bubolo.world.Damageable;

/**
 * A StationaryElement is a StationaryEntity that can take Damage and take actions in the
 * game world.
 * 
 * @author BU CS673 - Clone Productions
 */
public abstract class StationaryElement extends StationaryEntity implements Damageable
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = -1849311149500334067L;
	private int hp, maxhp;
	private boolean alive = true;

	/**
	 * Construct a new StationaryElement with a random UUID.
	 */
	public StationaryElement()
	{
		super();
	}

	/**
	 * Construct a new StationaryElement with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new StationaryElement.
	 */
	public StationaryElement(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	protected StationaryElement setMaxHP(int i)
	{
		maxhp = i;
		return this;
		
	}

	@Override
	public int getMaxHP()
	{
		// TODO setMaxHP
		return maxhp;
	}

	@Override
	public int getHP()
	{
		return hp;
	}
	
	@Override
	public StationaryElement modifyHP(int i)
	{
		hp += i;
		return this;
	}

	@Override
	public StationaryElement setHP(int i)
	{
		hp = i;
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

	// TODO: Add StationaryElement functionality!
}
