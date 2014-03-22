package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.TextureUtil;
import bubolo.world.entity.concrete.Grass;

/**
 * The graphical representation of grass entity.
 * 
 * @author BU673 - Clone Industries
 */
class GrassSprite extends Sprite<Grass>
{
	private Texture image;
	
	private TextureRegion[][] frames;

	/**
	 * Constructor for the GrassSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system.
	 * 
	 * @param grass
	 *            Reference to the Grass that this GrassSprite represents.
	 */
	GrassSprite(Grass grass)
	{
		super(DrawLayer.BASE_TERRAIN, grass);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "grass.png");
		frames = TextureUtil.splitFrames(image, 48, 48);
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
//			drawTexture(batch, camera, layer, frames[0][0]);
//			drawTexture(batch, camera, layer, new TextureRegion(image, 48, 48));
			drawTexture(batch, camera, layer, image);
		}
	}
}
