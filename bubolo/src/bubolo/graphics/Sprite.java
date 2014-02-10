package bubolo.graphics;

import java.util.UUID;

import bubolo.world.DrawableEntity;

/**
 * Interface for sprites, which draw textures to represent DrawableEntities a quad at a
 * specific x,y location.
 * 
 * @author BU673 - Clone Industries
 */
public interface Sprite<T extends DrawableEntity>
{
	/**
	 * Draws the sprite to the screen.
	 * 
	 * @param layer
	 *            Specifies the draw order.
	 * @param entity
	 *            The entity that this sprite represents. At a minimum, this is used to
	 *            get location information.
	 */
	public void draw(DrawLayer layer, T entity);

}
