package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.world.entity.concrete.Tank;

/**
 * The graphical representation of a Tank.
 * @author BU CS673 - Clone Productions
 */
class TankSprite extends Sprite<Tank>
{
	private TextureRegion image;
	
	// true if the camera controller has been added.
	private boolean addedCameraController;
	
	/**
	 * Constructor for the TankSprite. This is Package-private because sprites
	 * should not be directly created outside of the graphics system
	 * (instead, call the Sprite.create(entity) static method).
	 * @param tank Reference to the tank that this TankSprite represents.
	 */
	TankSprite(Tank tank)
	{
		super(DrawLayer.TANKS, tank);
		
		Texture texture = Graphics.getTexture(Graphics.TEXTURE_PATH + "tank.png");
		
		// TODO: the sprite needs to ask the Tank if it is the local player.
		//	Something like tank.isLocalPlayer() would work.
		
		// TODO: use this once tank.isLocalPlayer() or equivalent exists. Please do not remove these commented out lines.
	//	if (tank.isLocalPlayer())
	//	{
			image = new TextureRegion(texture, 36, 1, 24, 29);
	//	}
		//else
		//{
			//enemyImage = new TextureRegion(image, 4, 1, 25, 29);
		//}
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{ 
		drawTexture(batch, camera, layer, image);

		// TODO: this should only be added for the local tank.
		if (!addedCameraController)
		{
			CameraController controller = new TankCameraController(getEntity());
			Graphics.getInstance().addCameraController(controller);
			addedCameraController = true;
		}
	}
}
