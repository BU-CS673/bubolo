package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.GameLogicException;
import bubolo.util.TextureUtil;
import bubolo.world.entity.concrete.Tank;

/**
 * The graphical representation of a Tank.
 * 
 * @author BU CS673 - Clone Productions
 */
class TankSprite extends Sprite<Tank>
{
	// The index representing which animation frame will be drawn.
	private int frameIndex;

	// An index representing which row of the sprite sheet to use, based on color set.
	private int colorId = ColorSets.RED;

	// An array of all frames held in the texture sheet, by ROW and then COLUMN.
	// Why the y value first? Because the y value represents the color set to be used.
	private TextureRegion[][] frames;

	// An array of the frames to be used for the driving forwards animation.
	private TextureRegion[][] backwardFrames;

	// An array of the frames to be used for the driving backwards animation.
	private TextureRegion[][] forwardFrames;

	// Frame to be used for the standing (idle) animation
	private TextureRegion[] standingFrames;

	// The number of milliseconds per frame.
	private static final long millisPerFrame = 100;

	// The amount of time remaining for the current frame.
	private long frameTimeRemaining;

	// The time of the last frame, in milliseconds.
	private long lastFrameTime;

	// The current animation state of the tank, determines which animation to play.
	private int animationState = 1;

	// The last animation state that the tank was in, used to determine when to reset
	// the starting frame.
	private int lastAnimationState = 0;

	/**
	 * Constructor for the TankSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system.
	 * 
	 * @param tank
	 *            Reference to the tank that this TankSprite represents.
	 */
	TankSprite(Tank tank)
	{
		super(DrawLayer.ACTORS, tank);
	}

	private void updateColorSet()
	{
		if (this.getEntity().isLocalPlayer())
		{
			colorId = ColorSets.BLUE;
		}
		else
		{
			colorId = ColorSets.RED;
		}
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		if (isEntityDisposed())
		{
			Sprites.getInstance().removeSprite(this);
			return;
		}
		else if (frames == null)
		{
			initialize(camera);
		}

		updateColorSet();

		drawTexture(batch, camera, layer, forwardFrames[frameIndex][colorId]);
		if (this.getEntity().getSpeed() > 0.0f)
		{
			animationState = 1;
		}
		else
		{
			animationState = 0;
		}

		switch (animationState)
		{
		case 0:
			if (lastAnimationState != 0)
			{
				lastAnimationState = 0;
				frameIndex = 0;
			}
			drawTexture(batch, camera, layer, standingFrames[colorId]);
			break;

		case 1:
			if (lastAnimationState != 1)
			{
				frameIndex = 0;
				lastAnimationState = 1;
			}
			drawTexture(batch, camera, layer, forwardFrames[frameIndex][colorId]);

			// Progress the tank building animation.
			// TODO: only change frames when the tank is actually building.
			frameTimeRemaining -= (System.currentTimeMillis() - lastFrameTime);
			lastFrameTime = System.currentTimeMillis();
			if (frameTimeRemaining < 0)
			{
				frameTimeRemaining = millisPerFrame;
				frameIndex = (frameIndex == forwardFrames.length - 1) ? 0 : frameIndex + 1;
			}
			break;

		case 2:
			if (lastAnimationState != 2)
			{
				frameIndex = 0;
				lastAnimationState = 2;
			}
			drawTexture(batch, camera, layer, backwardFrames[frameIndex][colorId]);

			// Progress the tank building animation.
			// TODO: only change frames when the tank is actually building.
			frameTimeRemaining -= (System.currentTimeMillis() - lastFrameTime);
			lastFrameTime = System.currentTimeMillis();
			if (frameTimeRemaining < 0)
			{
				frameTimeRemaining = millisPerFrame;
				frameIndex = (frameIndex == backwardFrames.length - 1) ? 0 : frameIndex + 1;
			}
			break;

		default:
			throw new GameLogicException("Programming error in tankSprite: default case reached.");
		}
	}

	/**
	 * Initializes the tank. This is needed because the Tank entity may not know whether
	 * it is local or not at construction time.
	 * 
	 * @param camera
	 *            reference to the camera.
	 */
	private void initialize(Camera camera)
	{
		Texture texture = Graphics.getTexture(Graphics.TEXTURE_PATH + "tank.png");
		frames = TextureUtil.splitFrames(texture, 32, 32);

		forwardFrames = new TextureRegion[][] { frames[0], frames[1], frames[2] };
		backwardFrames = new TextureRegion[][] { frames[0], frames[2], frames[1] };
		standingFrames = frames[0];
		frameIndex = 0;
		frameTimeRemaining = millisPerFrame;

		if (getEntity().isLocal())
		{
			CameraController controller = new TankCameraController(getEntity());
			Graphics.getInstance().addCameraController(controller);
			controller.setCamera(camera);
		}
	}
}
