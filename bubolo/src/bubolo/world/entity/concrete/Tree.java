package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.world.entity.StationaryElement;

/**
 * Trees are StationaryElements that can spread over time, and hide Tanks that drive over
 * them.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Tree extends StationaryElement
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 4072369464678115753L;

	/**
	 * Construct a new Tree with a random UUID.
	 */
	public Tree()
	{
		super();
	}

	/**
	 * Construct a new Tree with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tree.
	 */
	public Tree(UUID id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}

	// TODO: Add Tree functionality!
}
