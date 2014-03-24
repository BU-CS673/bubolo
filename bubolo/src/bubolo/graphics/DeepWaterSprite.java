package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.TextureUtil;
import bubolo.world.entity.concrete.DeepWater;

/**
 * The graphical representation of a DeepWater.
 * 
 * @author BU673 - Clone Industries
 */
class DeepWaterSprite extends Sprite<DeepWater>
{
	// list of texture regions, used for different tiling states
	private TextureRegion[] frames;

	/**
	 * Constructor for the DeepWaterSprite. This is Package-private because sprites should
	 * not be directly created outside of the graphics system.
	 * 
	 * @param deepWater
	 *            Reference to the DeepWater that this DeepWaterSprite represents.
	 */
	DeepWaterSprite(DeepWater deepWater)
	{
		super(DrawLayer.TERRAIN, deepWater);
		frames = TextureUtil.adaptiveSplit_9((Graphics.getTexture(Graphics.TEXTURE_PATH
				+ "deepWater.png")));
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		if (isEntityDisposed())
		{
			Sprites.getInstance().removeSprite(this);
		}
		else
		{
			drawTexture(batch, camera, layer, frames[this.getEntity().getState()]);
		}
	}
}
