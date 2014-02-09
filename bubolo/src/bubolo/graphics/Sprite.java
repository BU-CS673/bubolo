package bubolo.graphics;

import java.util.UUID;

import bubolo.world.Entity;
import bubolo.world.Tank;

/**
 * Interface for sprites, which draw textures to a quad at a specific
 * x,y location.
 * @author BU673 - Clone Industries
 */
public abstract class Sprite<T extends Entity>
{
	// Each concrete sprite class has a unique ID. These IDs are shared across
	// each instance of the concrete sprite.
	private UUID id;
	
	/**
	 * Constructor for the base Sprite class.
	 * @param spriteId An id that identifies the sprite's type.
	 */
	Sprite(UUID spriteId)
	{
		this.id = spriteId;
	}
	
	/**
	 * Returns an id that identifies the sprite's type. This exists
	 * so that the world system can delegate the entity.getSpriteId() method
	 * to this method.
	 * @return A UUID that identifies the sprite's type
	 */
	public UUID getId()
	{
		return id;
	}
	
	/**
	 * Draws the sprite to the screen.
	 * @param layer Specifies the draw order.
	 * @param entity The entity that this sprite represents. At a minimum,
	 * this is used to get location information.
	 */
	public abstract void draw(DrawLayer layer, T entity);
	
	/**
	 * Static factory method for creating concrete sprites. This ensures that
	 * the world system does not need to know about any of the concrete sprites.
	 * @param entity Reference to the entity that is creating the sprite.
	 * @return A new concrete Sprite of type T.
	 * @throws IllegalArgumentException if the entity is of a type that
	 * is unknown to this method. This signifies a programming error.
	 */
	public static <T extends Entity> Sprite<?> create(T entity)
	{
		if (entity.getClass() == Tank.class)
		{
			return new TankSprite();
		}
		
		// Programming error if this line is reached.
		throw new IllegalArgumentException(
				"Unknown entity type passed to Sprite.create(): " + entity.getClass().toString());
	}
}
