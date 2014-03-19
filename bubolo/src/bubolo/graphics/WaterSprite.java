package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.TextureUtil;
import bubolo.world.entity.concrete.Water;

/**
 * The graphical representation of a Water.
 * 
 * @author BU673 - Clone Industries
 */
class WaterSprite extends Sprite<Water>
{
	private TextureRegion[] frames;

	/**
	 * Constructor for the WaterSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system.
	 * 
	 * @param water
	 *            Reference to the Water that this WaterSprite represents.
	 */
	WaterSprite(Water water)
	{
		super(DrawLayer.TERRAIN, water);

		frames = TextureUtil.adaptiveSplit_16_9_9(Graphics.getTexture(Graphics.TEXTURE_PATH
				+ "water.png"));
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		
		// TODO: Should pull adaptive tiling state from Entity, currently uses 'center'
		// texture exclusively
		drawTexture(batch, camera, layer, frames[this.getEntity().getState()]);
	}
}
