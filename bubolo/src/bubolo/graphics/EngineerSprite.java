package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.world.entity.Entity;

/**
 * The graphical representation of an engineer entity.
 * @author BU673 - Clone Industries
 */
class EngineerSprite extends Sprite<Entity>
{
	private Texture image;
	/**
	 * Constructor for the EngineerSprite. This is Package-private because sprites
	 * should not be directly created outside of the graphics system.
	 * @param engineer reference to the Engineer that this EngineerSprite represents.
	 */
	EngineerSprite(Entity engineer)
	{
		super(DrawLayer.OBJECTS, engineer);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "man.png");
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