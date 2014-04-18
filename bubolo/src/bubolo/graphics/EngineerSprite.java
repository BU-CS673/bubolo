package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.GameLogicException;
import bubolo.util.TextureUtil;
import bubolo.world.entity.concrete.Engineer;

/**
 * The graphical representation of an Engineer.
 * 
 * @author BU CS673 - Clone Productions
 */
class EngineerSprite extends AbstractEntitySprite<Engineer>
{
	// The index representing which animation frame will be drawn.
	private int frameIndex;

	// An index representing which row of the sprite sheet to use, based on color set.
	private int colorId = ColorSets.BLUE;

	// An array of all frames held in the texture sheet, by ROW and then COLUMN.
	// Why the y value first? Because the y value represents the color set to be used.
	private TextureRegion[][] allFrames;

	// The list of texture regions to be used for the running animation.
	private TextureRegion[][] runningFrames;

	// The list of texture regions to be used for the building animation.
	private TextureRegion[][] buildingFrames;

	// The texture region to be used for the engineer's idle animation (standing still).
	private TextureRegion[] standingFrames;

	// The number of milliseconds per frame.
	private static final long millisPerFrame = 150;

	// The amount of time remaining for the current frame.
	private long frameTimeRemaining;

	// The time of the last frame, in milliseconds.
	private long lastFrameTime;

	// The current animation state of the Engineer, determines which animation to play.
	private int animationState = 2;

	// The last animation state that the Engineer was in, used to determine when to reset
	// the starting frame.
	private int lastAnimationState = 0;
	
	/** The file name of the texture. */
	private static final String TEXTURE_FILE = "engineer.png";

	/**
	 * Constructor for the EngineerSprite. This is Package-private because sprites should
	 * not be directly created outside of the graphics system.
	 * 
	 * @param engi
	 *            Reference to the Engineer that this EngineerSprite represents.
	 */
	EngineerSprite(Engineer engi)
	{
		super(DrawLayer.FOURTH, engi);

		Texture texture = Graphics.getTexture(Graphics.TEXTURE_PATH + TEXTURE_FILE);
		allFrames = TextureUtil.splitFrames(texture, 21, 21);

		runningFrames = new TextureRegion[][] { allFrames[1], allFrames[2], allFrames[3],
				allFrames[2] };
		buildingFrames = new TextureRegion[][] { allFrames[4], allFrames[1], allFrames[5],
				allFrames[1] };
		standingFrames = allFrames[0];

		frameIndex = 0;
		frameTimeRemaining = millisPerFrame;
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
		if (isDisposed())
		{
			Sprites.getInstance().removeSprite(this);
		}
		else
		{
			updateColorSet();

			if (this.getEntity().isRunning())
			{
				animationState = 2;
			}
			else if (this.getEntity().isBuilding())
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
				drawTexture(batch, camera, layer, buildingFrames[frameIndex][colorId]);

				// Progress the Engineer building animation.
				// TODO: only change frames when the Engineer is actually building.
				frameTimeRemaining -= (System.currentTimeMillis() - lastFrameTime);
				lastFrameTime = System.currentTimeMillis();
				if (frameTimeRemaining < 0)
				{
					frameTimeRemaining = millisPerFrame;
					frameIndex = (frameIndex == buildingFrames.length - 1) ? 0 : frameIndex + 1;
				}
				break;

			case 2:
				if (lastAnimationState != 2)
				{
					frameIndex = 0;
					lastAnimationState = 2;
				}
				drawTexture(batch, camera, layer, runningFrames[frameIndex][colorId]);

				// Progress the Engineer running animation.
				// TODO: only change frames when the Engineer is actually running.
				frameTimeRemaining -= (System.currentTimeMillis() - lastFrameTime);
				lastFrameTime = System.currentTimeMillis();
				if (frameTimeRemaining < 0)
				{
					frameTimeRemaining = millisPerFrame;
					frameIndex = (frameIndex == runningFrames.length - 1) ? 0 : frameIndex + 1;
				}
				break;

			default:
				throw new GameLogicException(
						"Programming error in EngineerSprite: default case reached.");
			}

		}
	}
}
