package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.world.entity.concrete.DeepWater;

/**
 * The graphical representation of a DeepWater.
 * 
 * @author BU673 - Clone Industries
 */
class DeepWaterSprite extends Sprite<DeepWater>
{
	private Texture image;

	/**
	 * Constructor for the DeepWaterSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system.
	 * 
	 * @param DeepWater
	 *            Reference to the DeepWater that this DeepWaterSprite represents.
	 */
	DeepWaterSprite(DeepWater deepwater)
	{
		super(DrawLayer.TERRAIN, deepwater);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "deepwater.png");
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		if (!isEntityDisposed())
		{
			drawTexture(batch, camera, layer, image);
		}
		else
		{
			Sprites.getInstance().removeSprite(this);
		}
	}
}

