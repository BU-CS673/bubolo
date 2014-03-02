package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.world.entity.Entity;

/**
 * 
 * @author BU673 - Clone Industries
 */
class BulletSprite extends Sprite<Entity>
{
	private Texture image;
	/**
	 * Constructor for the BulletSprite. This is Package-private because sprites
	 * should not be directly created outside of the graphics system
	 * (instead, call the Sprite.create(entity) static method).
	 * @param bullet Reference to the Bullet that this BulletSprite represents.
	 */
	BulletSprite(Entity bullet)
	{
		super(DrawLayer.OBJECTS, bullet);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "bullet.png");
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		drawTexture(batch, camera, layer, image);
	}
}