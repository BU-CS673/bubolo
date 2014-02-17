package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.world.entity.concrete.Road;

/**
 * The graphical representation of a Tank.
 * 
 * @author BU673 - Clone Industries
 */
class RoadSprite extends Sprite<Road>
{
	private Texture image;

	/**
	 * Constructor for the RoadSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system (instead, call the
	 * Sprite.create(entity) static method).
	 * 
	 * @param road
	 *            Reference to the road that this RoadSprite represents.
	 */
	RoadSprite(Road road)
	{
		super(DrawLayer.OBJECTS, road);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "default.png");
		// TODO: Create a Tree texture!
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		drawTexture(batch, camera, layer, image);
	}
}
