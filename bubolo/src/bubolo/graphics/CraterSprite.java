package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.GameLogicException;
import bubolo.world.entity.concrete.Crater;

/**
 * The graphical representation of a Crater
 * @author BU673 - Clone Industries
 */
class CraterSprite extends Sprite<Crater>
{
	private Texture image;

	/**
	 * Constructor for the CraterSprite. This is Package-private because sprites
	 * should not be directly created outside of the graphics system
	 * (instead, call the Sprite.create(entity) static method)
	 * 
	 ** @param crater
	 *            Reference to the crater that this CraterSprite represents.
	 */
	CraterSprite(Crater crater)
	{
		super(DrawLayer.TERRAIN, crater);
		
		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "crater.png");

	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		drawTexture(batch, camera, layer, image);

	}
}
