package bubolo.graphics;

import java.util.UUID;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import bubolo.world.Pillbox;

/**
 * The graphical representation of a Pillbox
 * @author BU673 - Clone Industries
 */
class PillboxSprite extends Sprite<Pillbox>
{
	private Texture image;
	
	// true if the camera controller has been added.
	private boolean addedCameraController;
	
	/**
	 * Constructor for the PillboxSprite. This is Package-private because sprites
	 * should not be directly created outside of the graphics system
	 * (instead, call the Sprite.create(entity) static method).
	 * @param pillbox Reference to the pillbox that this PillboxSprite represents.
	 */
	PillboxSprite(Pillbox pillbox)
	{
		super(UUID.fromString("a297c142-716a-431f-8ea2-742a105fbbef"),
				DrawLayer.TERRAIN_MODIFIERS, pillbox);
		
		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "pillbox.png");
		
	}

	// EMS Commented out because I don't know to handle this--do we need a draw method for pillbox?
	// ...or is the layer itself drawn?
	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		drawTexture(batch, camera, layer, image);
//		
//	    Reminder Texture Region for Pillbox Sprite
//      Poll Pillbox entity poll for hit point status
//
//		if (!addedCameraController)
//		{
//			// TODO: the sprite needs to ask the Pillbox if it is the local player.
//			//	Something like pillbox.isLocalPlayer() would work.
//			CameraController controller = new PillboxCameraController(getEntity());
//			Graphics.getInstance().addCameraController(controller);
//			addedCameraController = true;
//		}
	}
}
