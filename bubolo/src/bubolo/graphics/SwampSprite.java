package bubolo.graphics;

import bubolo.world.entity.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The graphical representation of a Swamp.
 * 
 * @author BU673 - Clone Industries
 */
class SwampSprite extends AbstractEntitySprite<Entity>
{
	private Texture image;
	
	/** The file name of the texture. */
	private static final String TEXTURE_FILE = "swamp.png";

	/**
	 * Constructor for the SwampSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system.
	 * 
	 * @param swamp
	 *            Reference to the Swamp that this SwampSprite represents.
	 */
	SwampSprite(Entity swamp)
	{
		super(DrawLayer.BOTTOM, swamp);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + TEXTURE_FILE);
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
			drawTexture(batch, camera, layer, image);
		}
	}
}