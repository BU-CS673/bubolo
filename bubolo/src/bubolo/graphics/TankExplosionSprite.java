package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.TextureUtil;

/**
 * A tank explosion.
 * 
 * @author BU673 - Clone Industries
 */
class TankExplosionSprite extends Sprite
{
	private TextureRegion[][] frames;

	// The number of milliseconds per frame.
	private static final long millisPerFrame = 85;

	// The amount of time remaining for the current frame.
	private long frameTimeRemaining;

	// The time of the last frame.
	private long lastFrameTime;

	private int frameIndex;

	private boolean disposed;

	private final int x;
	private final int y;

	private static final int HEIGHT = 32;
	private static final int WIDTH = 32;

	/** The file name of the texture. */
	private static final String TEXTURE_FILE = "tank_explosion.png";

	/**
	 * Constructs a TankExplosionSprite. This is Package-private because sprites should not be
	 * directly created outside of the graphics system.
	 * 
	 * @param x
	 *            the x position of the explosion.
	 * @param y
	 *            the y position of the explosion.
	 */
	TankExplosionSprite(int x, int y)
	{
		super(DrawLayer.TOP);

		frames = TextureUtil.splitFrames(
				Graphics.getTexture(Graphics.TEXTURE_PATH + TEXTURE_FILE), WIDTH, HEIGHT);

		frameTimeRemaining = millisPerFrame;
		lastFrameTime = System.currentTimeMillis();

		this.x = x;
		this.y = y;
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
			animate();
		}
	}

	private void animate()
	{
		frameTimeRemaining -= (System.currentTimeMillis() - lastFrameTime);
		lastFrameTime = System.currentTimeMillis();
		if (frameTimeRemaining < 0)
		{
			frameTimeRemaining = millisPerFrame;

			if (frameIndex < frames.length - 1)
			{
				++frameIndex;
			}
			else
			{
				disposed = true;
			}
		}
	}

	@Override
	public boolean isDisposed()
	{
		return disposed;
	}

	@Override
	public float getX()
	{
		return x;
	}

	@Override
	public float getY()
	{
		return y;
	}

	@Override
	public int getWidth()
	{
		return WIDTH;
	}

	@Override
	public int getHeight()
	{
		return HEIGHT;
	}

	@Override
	public float getRotation()
	{
		return 0.f;
	}
}