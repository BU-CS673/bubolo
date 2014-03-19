package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.world.entity.concrete.Base;

/**
 * The graphical representation of a base entity.
 * @author BU673 - Clone Industries
 */
class BaseSprite extends Sprite<Base>
{
	private Texture image;
	
	/**
	 * Constructor for the BaseSprite. This is Package-private because sprites
	 * should not be directly created outside of the graphics system.
	 * @param base reference to the base that this BaseSprite represents.
	 */
	BaseSprite(Base base)
	{
		super(DrawLayer.OBJECTS, base);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "base.png");
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