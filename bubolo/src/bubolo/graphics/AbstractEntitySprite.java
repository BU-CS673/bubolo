package bubolo.graphics;

import bubolo.world.entity.Entity;

/**
 * Abstract base class for sprites, which draw textures to a quad at a specific x,y location.
 * 
 * @param <T>
 *            the least derived <code>Entity</code> type that this <code>Sprite</code> needs to draw
 *            itself. For example, a very simple <code>Sprite</code>, such as
 *            <code>GrassSprite</code>, can derive from <code>Sprite{@literal <Entity>}</code>,
 *            while a more complex <code>Sprite</code>, such as <code>PillboxSprite</code>, will
 *            likely need to derive from <code>Sprite{@literal <Pillbox>}</code>.
 * 
 * @author BU CS673 - Clone Productions
 */
abstract class AbstractEntitySprite<T extends Entity> extends Sprite
{
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
	protected AbstractEntitySprite(DrawLayer layer, T entity)
	{
		super(layer);
		this.entity = entity;
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
	 * 
	 * @return true if the underlying entity is destroyed, or false otherwise.
	 */
	@Override
	protected boolean isDisposed()
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
}
