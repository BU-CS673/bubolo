package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.TextureUtil;
import bubolo.world.entity.concrete.Road;

/**
 * The graphical representation of a Road
 * 
 * @author BU673 - Clone Industries
 */
class RoadSprite extends AbstractEntitySprite<Road>
{
	private TextureRegion[] frames;
	
	/** The file name of the texture. */
	private static final String TEXTURE_FILE = "road.png";

	/**
	 * Constructor for the RoadSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system.
	 * 
	 * @param road
	 *            Reference to the road that this RoadSprite represents.
	 */
	RoadSprite(Road road)
	{
		super(DrawLayer.SECOND, road);

		frames = TextureUtil.adaptiveSplit_16(Graphics.getTexture(Graphics.TEXTURE_PATH
				+ TEXTURE_FILE));
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
