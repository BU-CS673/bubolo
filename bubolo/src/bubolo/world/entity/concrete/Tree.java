package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.graphics.MockSprite;
import bubolo.world.entity.base.StationaryElement;

/**
 * Trees are StationaryElements that can spread over time, and hide Tanks that
 * drive over them.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Tree extends StationaryElement {
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 4072369464678115753L;

	/**
	 * Construct a new Tree with a random UUID.
	 */
	public Tree() {
		super();
		// sprite = Sprite.create(this);
	}

	/**
	 * Construct a new Tree with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tree.
	 */
	public Tree(UUID id) {
		super(id);
		// sprite = Sprite.create(this);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construct a new Tree with a MockSprite. Used for unit testing with
	 * separation from the Graphics system.
	 * 
	 * @param m
	 *            is the MockSprite that should be used to represent this Tree.
	 */
	public Tree(MockSprite<Tree> m) {
		sprite = m;
	}

	// TODO: Add Tree functionality!
}
