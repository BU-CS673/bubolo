package bubolo.world;

import java.util.UUID;

/**
 * Basic class for DrawableEntities that do not move (i.e., those that are fixed a single location on the map grid).
 * @author BU673 - Clone Industries
 */
public abstract class StationaryEntity extends Entity
{

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 7748173162492885868L;

	public StationaryEntity(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	public StationaryEntity(){
		super();
	}

}
