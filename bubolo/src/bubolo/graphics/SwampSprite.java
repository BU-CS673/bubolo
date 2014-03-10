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
	 * be directly created outside of the graphics system (instead, call the
	 * Sprite.create(entity) static method).
	 * 
	 * @param swamp
	 *            Reference to the Swamp that this SwampSprite represents.
	 */
	SwampSprite(Swamp swamp)
	{
		super(DrawLayer.TERRAIN, swamp);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "Swamp.png");
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		drawTexture(batch, camera, layer, image);
	}
}