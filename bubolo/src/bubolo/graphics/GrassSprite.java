package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.world.entity.Entity;

/**
 * The graphical representation of grass entity.
 * @author BU673 - Clone Industries
 */
class GrassSprite extends Sprite<Entity>
{
	private Texture image;
	
	/**
	 * Constructor for the GrassSprite. This is Package-private because sprites
	 * should not be directly created outside of the graphics system.
	 * @param grass Reference to the Grass that this GrassSprite represents.
	 */
	GrassSprite(Entity grass)
	{
		super(DrawLayer.TERRAIN, grass);
		
		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "grass.png");
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		drawTexture(batch, camera, layer, image);
	}
}
