package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.graphics.MockSprite;
import bubolo.world.entity.Modifier;

//import bubolo.graphics.Sprite;

/**
 * Roads are Modifiers for Terrain that allow Tanks to drive more quickly. They
 * can be created and destroyed by Tanks.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Road extends Modifier {
	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = -5302600252810938564L;

	/**
	 * Construct a new Road with a random UUID.
	 */
	public Road() {
		super();
		// sprite = Sprite.create(this);
	}

	/**
	 * Construct a new Road with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Road.
	 */
	public Road(UUID id) {
		super(id);
		// sprite = Sprite.create(this);
	}

	/**
	 * Construct a new Road with a MockSprite. Used for unit testing with
	 * separation from the Graphics system.
	 * 
	 * @param m
	 *            is the MockSprite that should be used to represent this Road.
	 */
	public Road(MockSprite<Road> m) {
		super();
		sprite = m;
	}

	// TODO: Add Road functionality!
}
