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

	

}
