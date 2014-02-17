package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.graphics.MockSprite;
import bubolo.graphics.Sprite;
import bubolo.world.entity.Actor;

/**
 * The tank, which may be controlled by a local player, a networked player, or
 * an AI bot.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Tank extends Actor {
	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 457933513574468829L;

	/**
	 * Construct a new Tank with a random UUID.
	 */
	public Tank() {
		super();
		sprite = Sprite.create(this);
	}

	/**
	 * Construct a new Tank with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tank.
	 */
	public Tank(UUID id) {
		super(id);
		sprite = Sprite.create(this);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construct a new Tank with a MockSprite. Used for unit testing with
	 * separation from the Graphics system.
	 * 
	 * @param m
	 *            is the MockSprite that should be used to represent this Tank.
	 */
	public Tank(MockSprite<Tank> m) {
		super();
		sprite = m;
	}

	// TODO: Add Tank functionality!
}
