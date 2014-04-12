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
class CraterSprite extends AbstractEntitySprite<Crater>
{
	private TextureRegion[] frames;
	
	/** The file name of the texture. */
	private static final String TEXTURE_FILE = "crater.png";

	/**
	 * Constructor for the CraterSprite. This is Package-private because sprites should
	 * not be directly created outside of the graphics system.
	 * 
	 ** @param crater
	 *            Reference to the crater that this CraterSprite represents.
	 */
	CraterSprite(Crater crater)
	{
		super(DrawLayer.SECOND, crater);

		Texture tex = Graphics.getTexture(Graphics.TEXTURE_PATH + TEXTURE_FILE);
		frames = TextureUtil.adaptiveSplit_16(tex);

	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		if (isDisposed())
		{
			Sprites.getInstance().removeSprite(this);
		}
		else
		{
			drawTexture(batch, camera, layer, frames[this.getEntity().getTilingState()]);

		}

	}
}
