package bubolo.graphics;

import bubolo.world.Entity;

import java.util.UUID;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import bubolo.world.Tank;

/**
 * Interface for sprites, which draw textures to a quad at a specific x,y location.
 * 
 * @author BU673 - Clone Industries
 * @param <T>
 *            is any object that extends the Entity class.
 */
public abstract class Sprite<T extends Entity>
{
	// Each concrete sprite class has a unique ID. These IDs are shared across
	// each instance of the concrete sprite.
	private UUID id;

	// The layer that this sprite is drawn to.
	private DrawLayer drawLayer;

	// Reference to the entity that this sprite represents.
	private T entity;

	/**
	 * Constructor for the base Sprite class.
	 * 
	 * @param spriteId
	 *            an id that identifies the sprite's type.
	 * @param layer
	 *            the layer that the sprite is drawn to.
	 * @param entity
	 *            reference to the Entity that this sprite represents.
	 */
	Sprite(UUID spriteId, DrawLayer layer, T entity)
	{
		this.id = spriteId;
		this.drawLayer = layer;
		this.entity = entity;
	}

	/**
	 * Returns an id that identifies the sprite's type. This exists so that the world
	 * system can delegate the entity.getSpriteId() method to this method.
	 * 
	 * @return A UUID that identifies the sprite's type
	 */
	public UUID getId()
	{
		return id;
	}

	/**
	 * Returns the sprite's draw layer.
	 * 
	 * @return the sprite's draw layer.
	 */
	protected DrawLayer getDrawLayer()
	{
		return drawLayer;
	}

	/**
	 * Returns the entity that this sprite represents.
	 * 
	 * @return the entity that this sprite represents.
	 */
	protected T getEntity()
	{
		return entity;
	}

	/**
	 * Draws the sprite to the screen.
	 * 
	 * @param batch
	 *            The game's SpriteBatch object. batch.begin() must have been called
	 *            before the SpriteBatch is passed to this Sprite.
	 * @param camera
	 *            The game's libgdx camera.
	 * @param layer
	 *            The layer that is currently being drawn. Note that this is not
	 *            necessarily the DrawLayer that this entity belongs to.
	 */
	public abstract void draw(SpriteBatch batch, Camera camera, DrawLayer layer);

	/**
	 * Draws the texture to the screen.
	 * 
	 * @param batch
	 *            The game's SpriteBatch object. batch.begin() must have been called
	 *            before the SpriteBatch is passed to this Sprite.
	 * @param camera
	 *            The game's libgdx camera.
	 * @param layer
	 *            The layer that is currently being drawn. Note that this is not
	 *            necessarily the DrawLayer that this entity belongs to.
	 * @param texture
	 *            The texture to draw.
	 */
	protected void drawTexture(SpriteBatch batch, Camera camera, DrawLayer layer, Texture texture)
	{
		if (layer == drawLayer)
		{
			Vector2 cameraCoordinates = Coordinates.worldToCamera(camera, new Vector2(getEntity()
					.getX(), getEntity().getY()));
			batch.draw(texture, cameraCoordinates.x, cameraCoordinates.y);
		}
	}

	// Note (cdc - 2/10/2014): I initially tried to create a generic form of this
	// static method. However, I wasn't able to create a version that would compile
	// without warnings (unchecked class cast warning, despite performing a check).
	// Instead, this method will be overloaded.

	/**
	 * Static factory method for creating concrete sprites. This ensures that the world
	 * system does not need to know about any of the concrete sprites.
	 * 
	 * @param entity
	 *            Reference to the entity that is creating the sprite.
	 * @return A new concrete Sprite.
	 */
	public static Sprite<Tank> create(Tank entity)
	{
		return new TankSprite(entity);
	}

	/**
	 * Creates a DefaultSprite object for instances where subclasses of Entity are
	 * constructed without specifying their own specific sprite type.
	 * 
	 * @param entity Reference to the entity that is creating the sprite.
	 * @return A new concrete Sprite.
	 */
	public static Sprite<Entity> create(Entity entity)
	{
		return new DefaultSprite(entity);
	}
}
