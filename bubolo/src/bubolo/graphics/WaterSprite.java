package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import bubolo.world.entity.concrete.Water;

/**
 * The graphical representation of a Water.
 * 
 * @author BU673 - Clone Industries
 */
class WaterSprite extends Sprite<Water>
{
	private Texture image;

	/**
	 * Constructor for the WaterSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system (instead, call the
	 * Sprite.create(entity) static method).
	 * 
	 * @param water
	 *            Reference to the Water that this WaterSprite represents.
	 */
	WaterSprite(Water water)
	{
		super(DrawLayer.TERRAIN, water);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "water.png");
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		drawTexture(batch, camera, layer, image);
	}
}

