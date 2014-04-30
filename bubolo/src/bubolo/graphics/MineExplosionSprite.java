package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.TextureUtil;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.MineExplosion;

/**
 * The graphical representation of a MineExplosion Entity.
 * 
 * @author BU673 - Clone Industries
 */
class MineExplosionSprite extends AbstractEntitySprite<Entity>
{

	// The index representing which animation frame will be drawn.
	private int frameIndex;

	/**
	 * A two-sided array of TextureRegions representing the animation frames for this
	 * MineExplosion.
	 */
	private TextureRegion[][] frames;

	// The number of milliseconds per frame.
	private long millisPerFrame;

	// The amount of time remaining for the current frame.
	private long frameTimeRemaining;

	// The time of the last frame, in milliseconds.
	private long lastFrameTime;
	
	/** The file name of the texture. */
	static final String TEXTURE_FILE = "mineExplosion.png";

	/**
	 * Constructor for the MineExplosionSprite. This is Package-private because sprites
	 * should not be directly created outside of the graphics system.
	 * 
	 * @param exp
	 *            Reference to the MineExplosion that this MineExplosionSprite represents.
	 */
	MineExplosionSprite(MineExplosion exp)
	{
		super(DrawLayer.TOP, exp);

		frames = TextureUtil.splitFrames(
				Graphics.getTexture(Graphics.TEXTURE_PATH + TEXTURE_FILE), 60, 60);
		
		millisPerFrame = (((MineExplosion)this.getEntity()).getExplosionLength())/frames.length;
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
			drawTexture(batch, camera, layer, frames[frameIndex][0]);

			frameTimeRemaining -= (System.currentTimeMillis() - lastFrameTime);
			lastFrameTime = System.currentTimeMillis();
			if (frameTimeRemaining < 0 && frameIndex < frames.length-1)
			{
				frameTimeRemaining = millisPerFrame;
				frameIndex = frameIndex + 1;
			}
		}
	}
}