package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import bubolo.world.entity.Entity;

/**
 * Abstract base class for sprites, which draw textures to a quad at a specific x,y location.
 * 
 * @author BU CS673 - Clone Productions
 */
abstract class Sprite<T extends Entity>
{
	// The layer that this sprite is drawn to.
	private DrawLayer drawLayer;

	// Reference to the entity that this sprite represents.
	private T entity;

	/**
	 * Constructor for the base Sprite class.
	 * 
	 * @param layer
	 *            the layer that the sprite is drawn to.
	 * @param entity
	 *            reference to the Entity that this sprite represents.
	 */
	Sprite(DrawLayer layer, T entity)
	{
		this.drawLayer = layer;
		this.entity = entity;
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
	abstract void draw(SpriteBatch batch, Camera camera, DrawLayer layer);

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
}
