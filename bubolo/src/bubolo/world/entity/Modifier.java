package bubolo.world.entity;

import java.util.UUID;

import bubolo.world.Damageable;

/**
 * Basic class for StationaryEntities that sit on top of Terrain tiles and interact with
 * Modifiers in some way.
 * 
 * @author BU CS673 - Clone Productions
 */
public abstract class Modifier extends StationaryEntity implements Damageable
{

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = -3848663925702678195L;

	/**
	 * Construct a new Modifier with a random UUID.
	 */
	public Modifier()
	{
		super();
	}

	/**
	 * Construct a new Modifier with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Modifier.
	 */
	public Modifier(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Modifier setHP(int i)
	{
		return this;
	}

	@Override
	public int getHP()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxHP()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Modifier modifyHP(int i)
	{
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public boolean isAlive()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	// TODO: Add Modifier functionality!
}
