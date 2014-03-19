package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.world.entity.concrete.Mine;
/**
 * The graphical representation of a Mine.
 * 
 * @author BU673 - Clone Industries
 */
class MineSprite extends Sprite<Mine>
{
	private Texture image;

	/**
	 * Constructor for the MineSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system.
	 * 
	 * @param mine
	 *            Reference to the Mine that this MineSprite represents.
	 */
	MineSprite(Mine mine)
	{
		super(DrawLayer.TERRAIN_MODIFIERS, mine);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "mine.png");
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		if (!isEntityDisposed())
		{
			drawTexture(batch, camera, layer, image);
		}
		else
		{
			Sprites.getInstance().removeSprite(this);
		}
	}
}
