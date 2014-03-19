package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.world.entity.Entity;

/**
 * The graphical representation of a Tank.
 * @author BU673 - Clone Industries
 * @deprecated DO NOT USE - MAY BE DELETED IN THE FUTURE.
 */
class DefaultSprite extends Sprite<Entity>
{
	private Texture image;
	
	/**
	 * Constructor for the TankSprite. This is Package-private because sprites
	 * should not be directly created outside of the graphics system
	 * (instead, call the Sprite.create(entity) static method).
	 * @param tank Reference to the tank that this TankSprite represents.
	 */
	DefaultSprite(Entity e)
	{
		super(DrawLayer.STATIONARY_ELEMENTS, e);
		
		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "default.png");
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		drawTexture(batch, camera, layer, image);
	}
}
