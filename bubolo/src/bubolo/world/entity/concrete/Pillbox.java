package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.StationaryElement;

/**
 * Pillboxes are stationary defensive structures that can be placed by a Tank. They shoot at 
 * an enemy Tank until destroyed, at which point they can be retrieved and used again.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Pillbox extends StationaryElement
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 278726024001386941L;

	/**
	 * Construct a new Pillbox with a random UUID.
	 */
	public Pillbox()
	{
		super();
	}

	/**
	 * Construct a new Pillbox with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tree.
	 */
	public Pillbox(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	// TODO: Add Pillbox functionality!
}
