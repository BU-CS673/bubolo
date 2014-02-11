package bubolo.world;

import java.util.UUID;

public abstract class Modifier extends StationaryEntity implements Damageable
{

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = -3848663925702678195L;
	
	public Modifier(UUID id)
	{
		super(id);
	}
	
	public Modifier(){
		super();
	}

	public Modifier(UUID id, float x, float y, int w, int h, float rot)
	{
		super(id,x,y,w,h,rot);
	}
	
	public Modifier(float x, float y, int w, int h, float rot)
	{
		super(x,y,w,h,rot);
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
