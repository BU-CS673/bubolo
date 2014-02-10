package bubolo.graphics;

import java.util.UUID;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import bubolo.world.Tank;

/**
 * The graphical representation of a Tank.
 * @author BU673 - Clone Industries
 */
class TankSprite extends Sprite<Tank>
{
	private Texture image;
	
	/**
	 * Constructor for the TankSprite. This is Package-private because sprites
	 * should not be directly created outside of the graphics system
	 * (instead, call the Sprite.create(entity) static method).
	 * @param tank Reference to the tank that this TankSprite represents.
	 */
	TankSprite(Tank tank)
	{
		super(UUID.fromString("13eb9d6a-8965-43fc-a4aa-82fb70c9045f"),
				DrawLayer.TANKS, tank);
		
		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "tank.png");
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		drawTexture(batch, camera, layer, image);
	}
}
