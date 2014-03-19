package bubolo.graphics;

import java.util.ArrayList;
import java.util.List;

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
	// The index of the texture region that will be drawn.
	private int imageIndex;
	
	// The list of texture regions, which is used for the tank animation.
	private List<TextureRegion> images;
	
	// The number of milliseconds per frame.
	private static final long millisPerFrame = 100;

	// The amount of time remaining for the current frame.
	private long frameTimeRemaining;
	
	// The time of the last frame, in milliseconds.
	private long lastFrameTime;
	
	/**
	 * Constructor for the TankSprite. This is Package-private because sprites
	 * should not be directly created outside of the graphics system.
	 * @param tank Reference to the tank that this TankSprite represents.
	 */
	TankSprite(Tank tank)
	{
		super(DrawLayer.TANKS, tank);
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{ 
		if (isEntityDisposed())
		{
			Sprites.getInstance().removeSprite(this);
			return;
		}
		else if (images == null)
		{
			initialize(camera);
		}
		
		drawTexture(batch, camera, layer, images.get(imageIndex));
		
		// Play the tank movement animation.
		// TODO: only change frames when the tank is actually moving.
		frameTimeRemaining -= (System.currentTimeMillis() - lastFrameTime);
		lastFrameTime = System.currentTimeMillis();
		if (frameTimeRemaining < 0)
		{
			frameTimeRemaining = millisPerFrame;
			imageIndex = (imageIndex == images.size() - 1) ? 0 : imageIndex + 1;
		}
	}
	
	/**
	 * Initializes the tank. This is needed because the Tank entity may not know
	 * whether it is local or not at construction time.
	 * @param camera reference to the camera.
	 */
	private void initialize(Camera camera)
	{
		Texture texture = Graphics.getTexture(Graphics.TEXTURE_PATH + "tank.png");
		images = new ArrayList<TextureRegion>(3);
		
		if (getEntity().isLocal())
		{
		
			images.add(new TextureRegion(texture, 4, 33, 25, 29));
			images.add(new TextureRegion(texture, 37, 33, 25, 29));
			images.add(new TextureRegion(texture, 70, 33, 25, 29));
		}
		else
		{
			images.add(new TextureRegion(texture, 4, 1, 24, 29));
			images.add(new TextureRegion(texture, 37, 1, 24, 29));
			images.add(new TextureRegion(texture, 70, 1, 24, 29));
		}
		imageIndex = 0;
		frameTimeRemaining = millisPerFrame;
		
		if (getEntity().isLocal())
		{
			CameraController controller = new TankCameraController(getEntity());
			Graphics.getInstance().addCameraController(controller);
			controller.setCamera(camera);
		}
	}
}
