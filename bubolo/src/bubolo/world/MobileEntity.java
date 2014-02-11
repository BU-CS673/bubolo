package bubolo.world;

import java.util.UUID;


/**
 * Basic class for DrawableEntities that move.
 * @author BU673 - Clone Industries
 */
public abstract class MobileEntity extends Entity
{

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = -7956746424636939370L;

	public MobileEntity(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	public MobileEntity()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public MobileEntity(UUID id, float x, float y, int w, int h, float rot)
	{
		super(id,x,y,w,h,rot);
	}
	
	public MobileEntity(float x, float y, int w, int h, float rot)
	{
		super(x,y,w,h,rot);
	}

}
