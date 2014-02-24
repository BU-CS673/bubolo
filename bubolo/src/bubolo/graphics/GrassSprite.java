package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.world.entity.concrete.Grass;

/**
 * The graphical representation of a Tank.
 * @author BU673 - Clone Industries
 */
class GrassSprite extends Sprite<Grass>
{
	private Texture image;
	
	/**
	 * Constructor for the GrassSprite. This is Package-private because sprites
	 * should not be directly created outside of the graphics system
	 * (instead, call the Sprite.create(entity) static method).
	 * @param grass Reference to the Grass that this GrassSprite represents.
	 */
	GrassSprite(Grass grass)
	{
		super(DrawLayer.TERRAIN, grass);
		
		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "default.png");
		//TODO: Create a Tree texture!
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		drawTexture(batch, camera, layer, image);
	}
}
