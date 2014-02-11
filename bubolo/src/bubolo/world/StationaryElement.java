package bubolo.world;

import java.util.UUID;

public abstract class StationaryElement extends StationaryEntity implements Damageable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1849311149500334067L;
	
	public StationaryElement(UUID id){
		super(id);
	}
	
	public StationaryElement(){
		super();
	}
	
	public StationaryElement(UUID id, float x, float y, int w, int h, float rot)
	{
		super(id,x,y,w,h,rot);
	}
	
	public StationaryElement(float x, float y, int w, int h, float rot)
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
