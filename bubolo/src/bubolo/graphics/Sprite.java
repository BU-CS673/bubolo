package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import bubolo.world.entity.Entity;

/**
 * Abstract base class for sprites, which draw textures to a quad at a specific x,y location.
 * @param <T> the least derived <code>Entity</code> type that this <code>Sprite</code> needs
 * to draw itself. For example, a very simple <code>Sprite</code>, such as <code>GrassSprite</code>,
 * can derive from <code>Sprite{@literal <Entity>}</code>, while a more complex <code>Sprite</code>,
 * such as <code>PillboxSprite</code>, will likely need to derive from <code>Sprite{@literal <Pillbox>}</code>.
 * 
 * @author BU CS673 - Clone Productions
 */
abstract class Sprite<T extends Entity> implements Drawable
{
	// The layer that this sprite is drawn to.
	private DrawLayer drawLayer;

	// Reference to the entity that this sprite represents.
	private T entity;
	
	// Ideally, these probably should not be placed into Sprite<T>.
	private static final float SCALE_X = 1.f;
	private static final float SCALE_Y = 1.f;

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
	 * Returns true if the underlying entity is destroyed, or false otherwise.
	 * @return true if the underlying entity is destroyed, or false otherwise.
	 */
	boolean isEntityDisposed()
	{
		return entity.isDisposed();
	}
	
	@Override
	public float getX()
	{
		return entity.getX();
	}

	@Override
	public float getY()
	{
		return entity.getY();
	}

	@Override
	public int getWidth()
	{
		return entity.getWidth();
	}

	@Override
	public int getHeight()
	{
		return entity.getHeight();
	}

	@Override
	public float getRotation()
	{
		return entity.getRotation();
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
		if (layer == getDrawLayer())
		{
			Vector2 cameraCoordinates = Coordinates.worldToCamera(camera, 
					new Vector2(getEntity().getX(), getEntity().getY()));
			
			Vector2 origin = getOrigin(getEntity());
			
			batch.draw(texture, cameraCoordinates.x, cameraCoordinates.y, origin.x, origin.y, 
					getEntity().getWidth(), getEntity().getHeight(), SCALE_X, SCALE_Y, getEntity().getRotation(),
					0, 0, texture.getWidth(), texture.getHeight(), false, false);
		}
	}
	
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
	 *            The texture region to draw.
	 */
	protected void drawTexture(SpriteBatch batch, Camera camera, DrawLayer layer, TextureRegion texture)
	{
		if (layer == getDrawLayer())
		{
			Vector2 cameraCoordinates = Coordinates.worldToCamera(camera, 
					new Vector2(getEntity().getX(), getEntity().getY()));
			
			Vector2 origin = getOrigin(getEntity());
			
			batch.draw(texture, cameraCoordinates.x, cameraCoordinates.y, origin.x, origin.y, 
					getEntity().getWidth(), getEntity().getHeight(), SCALE_X, SCALE_Y, getEntity().getRotation());
		}
	}
	
	/**
	 * Returns the center of an entity.
	 * @param ent reference to an entity.
	 * @return the center of an entity.
	 */
	private static <T extends Entity> Vector2 getOrigin(T ent)
	{
		return new Vector2(ent.getWidth() / 2.f, ent.getHeight() / 2.f);
	}
}
