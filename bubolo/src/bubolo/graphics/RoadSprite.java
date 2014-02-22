package bubolo.graphics;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import bubolo.world.entity.concrete.Road;

/**
 * The graphical representation of a Road
 * @author BU673 - Clone Industries
 */
class RoadSprite extends Sprite<Road>
{
	private Texture image;
	
	// true if the camera controller has been added.
	private boolean addedCameraController;
	
	/**
	 * Constructor for the RoadSprite. This is Package-private because sprites
	 * should not be directly created outside of the graphics system
	 * (instead, call the Sprite.create(entity) static method).
	 * @param road Reference to the road that this RoadSprite represents.
	 */
	RoadSprite(Road road)
	{
		super(DrawLayer.TERRAIN_MODIFIERS, road);
		
		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "road.png");
	}

	// EMS Commented out because I don't know to handle this--do we need a draw method for road?
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
//			// TODO: the sprite needs to ask the Road if it is the local player.
//			//	Something like road.isLocalPlayer() would work.
//			CameraController controller = new RoadCameraController(getEntity());
//			Graphics.getInstance().addCameraController(controller);
//			addedCameraController = true;
//		}
	}
}
