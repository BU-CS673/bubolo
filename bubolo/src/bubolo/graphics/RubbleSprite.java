package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.world.entity.concrete.Rubble;
/**
 * The graphical representation of a Mine.
 * 
 * @author BU673 - Clone Industries
 */
class RubbleSprite extends Sprite<Rubble>
{
	private Texture image;

	/**
	 * Constructor for the MineSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system.
	 * 
	 * @param mine
	 *            Reference to the Mine that this MineSprite represents.
	 */
	RubbleSprite(Rubble rubble)
	{
		super(DrawLayer.STATIONARY_ELEMENTS, rubble);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "rubble.png");
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		drawTexture(batch, camera, layer, image);
	}
}
