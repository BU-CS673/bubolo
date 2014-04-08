package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.world.entity.concrete.Rubble;

/**
 * The graphical representation of Rubble.
 * 
 * @author BU673 - Clone Industries
 */
class RubbleSprite extends AbstractEntitySprite<Rubble>
{
	private Texture image;
	
	/** The file name of the texture. */
	static final String TEXTURE_FILE = "rubble.png";

	/**
	 * Constructor for the RubbleSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system.
	 * 
	 * @param rubble
	 *            Reference to the Rubble that this RubbleSprite represents.
	 */
	RubbleSprite(Rubble rubble)
	{
		super(DrawLayer.STATIONARY_ELEMENTS, rubble);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + TEXTURE_FILE);
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
