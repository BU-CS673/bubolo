package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.graphics.MockSprite;
import bubolo.world.entity.base.Terrain;

/**
 * Grass is the standard Terrain of B.U.B.O.L.O., and offers no special movement
 * effects.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Grass extends Terrain {
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 5319713357245800006L;

	/**
	 * Construct a new Grass with a random UUID.
	 */
	public Grass() {
		super();
		// sprite = Sprite.create(this);
		// TODO: Create GrassSprite class!
	}

	/**
	 * Construct a new Grass with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Grass.
	 */
	public Grass(UUID id) {
		super(id);
		// sprite = Sprite.create(this);
		// TODO: Create GrassSprite class!
	}

	/**
	 * Construct a new Grass with a MockSprite. Used for unit testing with
	 * separation from the Graphics system.
	 * 
	 * @param m
	 *            is the MockSprite that should be used to represent this Grass.
	 */
	public Grass(MockSprite<Grass> m) {
		super();
		sprite = m;
	}

	// TODO: Add Grass functionality!
}
