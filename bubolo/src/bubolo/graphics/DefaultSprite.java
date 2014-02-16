package bubolo.graphics;

import java.util.UUID;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.world.entity.base.Entity;

/**
 * The graphical representation of a Tank.
 * @author BU673 - Clone Industries
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
		super(UUID.fromString("5dbd05cb-9ae7-44e0-a84b-def4a8791641"),
				DrawLayer.OBJECTS, e);
		
		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "default.png");
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		drawTexture(batch, camera, layer, image);
	}
}
