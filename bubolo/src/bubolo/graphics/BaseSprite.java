package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.GameLogicException;
import bubolo.util.TextureUtil;
import bubolo.world.entity.concrete.Base;

/**
 * The graphical representation of a Base Entity.
 * 
 * @author BU673 - Clone Industries
 */
class BaseSprite extends AbstractEntitySprite<Base>
{
	// The index representing which animation frame will be drawn.
	private int frameIndex;

	// An index representing which row of the sprite sheet to use, based on color set.
	private int colorId = ColorSets.BLUE;

	// An array of all frames held in the texture sheet, by ROW and COLUMN.
	private TextureRegion[][] allFrames;

	// The list of texture regions to be used for the charging animation of an owned base.
	private TextureRegion[][] chargingFrames;

	// The texture regions to be used for an owned base's idle animation.
	private TextureRegion[] idleFrames;

	// The number of milliseconds per frame.
	private static final long millisPerFrame = 100;

	// The amount of time remaining for the current frame.
	private long frameTimeRemaining;

	// The time of the last frame, in milliseconds.
	private long lastFrameTime;

	// The current animation state of the Base, determines which animation to play.
	private int animationState = 0;

	// The last animation state that the Base was in, used to determine when to reset
	// the starting frame.
	private int lastAnimationState = 0;

	/** The file name of the texture. */
	private static final String TEXTURE_FILE = "base.png";

	/**
	 * Constructor for the BaseSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system (instead, call the
	 * Sprite.create(entity) static method).
	 * 
	 * @param base
	 *            Reference to the Base that this BaseSprite represents.
	 */
	BaseSprite(Base base)
	{
		super(DrawLayer.THIRD, base);

		allFrames = TextureUtil.splitFrames(
				Graphics.getTexture(Graphics.TEXTURE_PATH + TEXTURE_FILE), 32, 32);
		chargingFrames = new TextureRegion[][] { allFrames[0], allFrames[1], allFrames[2],
				allFrames[3], allFrames[4], allFrames[5], allFrames[6], allFrames[7] };
		idleFrames = allFrames[0];
	}

	private void updateColorSet()
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

			animationState = (this.getEntity().isCharging()) ? 1 : 0;

			switch (animationState)
			{
			case 0:
				if (lastAnimationState != 0)
				{
					lastAnimationState = 0;
					frameIndex = 0;
				}
				drawTexture(batch, camera, layer, idleFrames[colorId]);
				break;

			case 1:
				if (lastAnimationState != 1)
				{
					frameIndex = 0;
					lastAnimationState = 1;
				}
				drawTexture(batch, camera, layer, chargingFrames[frameIndex][colorId]);

				// Progress the Base charging animation.
				frameTimeRemaining -= (System.currentTimeMillis() - lastFrameTime);
				lastFrameTime = System.currentTimeMillis();
				if (frameTimeRemaining < 0)
				{
					frameTimeRemaining = millisPerFrame;
					frameIndex = (frameIndex == chargingFrames.length - 1) ? 0 : frameIndex + 1;
				}
				break;

			default:
				throw new GameLogicException(
						"Programming error in BaseSprite: default case reached.");
			}
		}
	}
}
