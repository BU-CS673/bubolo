package bubolo.graphics;

import bubolo.world.entity.concrete.Swamp;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The graphical representation of a Swamp.
 * 
 * @author BU673 - Clone Industries
 */
class SwampSprite extends Sprite<Swamp>
{
	private Texture image;

	/**
	 * Constructor for the SwampSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system.
	 * 
	 * @param swamp
	 *            Reference to the Swamp that this SwampSprite represents.
	 */
	SwampSprite(Swamp swamp)
	{
		super(DrawLayer.BASE_TERRAIN, swamp);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "swamp.png");
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
			drawTexture(batch, camera, layer, image);
		}
	}
}