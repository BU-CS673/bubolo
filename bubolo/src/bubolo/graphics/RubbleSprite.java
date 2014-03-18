package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.world.entity.Entity;

/**
 * The graphical representation of a rubble entity.
 * @author BU673 - Clone Industries
 */
class RubbleSprite extends Sprite<Entity>
{
	private Texture image;
	
	/**
	 * Constructor for the RubbleSprite. This is Package-private because sprites
	 * should not be directly created outside of the graphics system.
	 * @param base reference to the base that this RubbleSprite represents.
	 */
	RubbleSprite(Entity rubble)
	{
		super(DrawLayer.OBJECTS, rubble);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "rubble.png");
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