package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.TextureUtil;
import bubolo.world.entity.concrete.Crater;

/**
 * The graphical representation of a Crater
 * 
 * @author BU673 - Clone Industries
 */
class CraterSprite extends Sprite<Crater>
{
	private TextureRegion[] frames;

	/**
	 * Constructor for the CraterSprite. This is Package-private because sprites should
	 * not be directly created outside of the graphics system.
	 * 
	 ** @param crater
	 *            Reference to the crater that this CraterSprite represents.
	 */
	CraterSprite(Crater crater)
	{
		super(DrawLayer.STATIONARY_ELEMENTS, crater);

		Texture tex = Graphics.getTexture(Graphics.TEXTURE_PATH + "crater.png");
		frames = TextureUtil.adaptiveSplit_16(tex);

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
