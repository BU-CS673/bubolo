package bubolo.world;

import java.util.UUID;

public abstract class Terrain extends StationaryEntity
{

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 6373697443259757484L;
	
	public Terrain(UUID id)
	{
		super(id);
	}
	
	public Terrain(){
		super();
	}

	public Terrain(UUID id, float x, float y, int w, int h, float rot)
	{
		super(id,x,y,w,h,rot);
	}
	
	public Terrain(float x, float y, int w, int h, float rot)
	{
		super(x,y,w,h,rot);
	}
	

}
