package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.GameLogicException;
import bubolo.util.TextureUtil;
import bubolo.world.entity.concrete.Mine;

/**
 * The graphical representation of a Mine.
 * 
 * @author BU673 - Clone Industries
 */
class MineSprite extends Sprite<Mine>
{
	// The index representing which animation frame will be drawn.
	private int frameIndex;

	// An index representing which row of the sprite sheet to use, Mined on color set.
	private int colorId = ColorSets.BLUE;

	// An array of all frames held in the texture sheet, by ROW and COLUMN.
	private TextureRegion[][] allFrames;

	// The list of texture regions to be used for the exploding animation.
	private TextureRegion[][] explodingFrames;

	// The texture regions to be used for the idle animation.
	private TextureRegion[][] idleFrames;

	// The number of milliseconds per frame.
	private static final long millisPerFrame = 150;

	// The amount of time remaining for the current frame.
	private long frameTimeRemaining;

	// The time of the last frame, in milliseconds.
	private long lastFrameTime;

	// The current animation state of the Engineer, determines which animation to play.
	private int animationState = 0;

	// The last animation state that the Engineer was in, used to determine when to reset
	// the starting frame.
	private int lastAnimationState = 0;

	/**
	 * Constructor for the MineSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system (instead, call the
	 * Sprite.create(entity) static method).
	 * 
	 * @param tree
	 *            Reference to the Mine that this MineSprite represents.
	 */
	MineSprite(Mine Mine)
	{
		super(DrawLayer.STATIONARY_ELEMENTS, Mine);

		allFrames = TextureUtil.splitFrames(
				Graphics.getTexture(Graphics.TEXTURE_PATH + "Mine.png"), 21, 21);
		explodingFrames = new TextureRegion[][] { allFrames[2], allFrames[3], allFrames[4],
				allFrames[5] };
		idleFrames = new TextureRegion[][] { allFrames[0], allFrames[1], allFrames[2], allFrames[1] };
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		if (!this.getEntity().isOwned())
		{
			animationState = 0;
			colorId = ColorSets.NEUTRAL;
		}
		else if (this.getEntity().isLocalPlayer())
		{
			colorId = ColorSets.BLUE;
		}
		else
		{
			colorId = ColorSets.RED;
		}

		if (this.getEntity().isExploding())
			animationState = 1;
		else
			animationState = 0;

		switch (animationState)
		{
		case 0:
			if (lastAnimationState != 0)
			{
				lastAnimationState = 0;
				frameIndex = 0;
			}
			
			drawTexture(batch, camera, layer, idleFrames[frameIndex][colorId]);

			// Progress the Mine idle animation.
						frameTimeRemaining -= (System.currentTimeMillis() - lastFrameTime);
						lastFrameTime = System.currentTimeMillis();
						if (frameTimeRemaining < 0)
						{
							frameTimeRemaining = millisPerFrame;
							frameIndex = (frameIndex == idleFrames.length - 1) ? 0 : frameIndex + 1;
						}
			break;

		case 1:
			if (lastAnimationState != 1)
			{
				frameIndex = 0;
				lastAnimationState = 1;
			}
			drawTexture(batch, camera, layer, explodingFrames[frameIndex][colorId]);

			// Progress the mine exploding animation.
			frameTimeRemaining -= (System.currentTimeMillis() - lastFrameTime);
			lastFrameTime = System.currentTimeMillis();
			if (frameTimeRemaining < 0)
			{
				frameTimeRemaining = millisPerFrame;
				frameIndex = (frameIndex == explodingFrames.length - 1) ? 0 : frameIndex + 1;
			}
			break;

		default:
			throw new GameLogicException("Programming error in MineSprite: default case reached.");
		}
	}
}
