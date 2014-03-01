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
	private Texture image;
	
	private TextureRegion specificTankImg;
	
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
		
		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "tank.png");
		
		specificTankImg = new TextureRegion();
		
		specificTankImg.setTexture(image);
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		
		// Yellow Tank
		//specificTankImg.setRegion(32,0,32,32);
		 
		//Blue Tank
		specificTankImg.setRegion(0,0,32,32);
		drawTexture(batch, camera, layer, specificTankImg);
		//drawTexture(batch, camera, layer, image);
		
		if (!addedCameraController)
		{
			// TODO: the sprite needs to ask the Tank if it is the local player.
			//	Something like tank.isLocalPlayer() would work.
			CameraController controller = new TankCameraController(getEntity());
			Graphics.getInstance().addCameraController(controller);
			addedCameraController = true;
		}
	}
}
