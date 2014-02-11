package bubolo.world;

import java.util.UUID;

/**
 * Basic class representing MobileEntities that exhibit some kind of behavior in the game world, such as tanks and humans.
 * @author BU673 - Clone Industries
 */
public abstract class Actor extends MobileEntity implements Damageable
{

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 6062132322107891442L;

	public Actor(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	public Actor(){
		super();
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
	public int modifyHP()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAlive()
	{
		// TODO Auto-generated method stub
		return false;
	}
	
}
