package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.TextureUtil;

/**
 * The graphical representation of a GenericExplosion entity.
 * 
 * @author BU673 - Clone Industries
 */
class BackgroundSprite extends Sprite
{
	private TextureRegion[][] frames;

	private int x;
	private int y;
	/** The sprite's height. **/
	static final int HEIGHT = 48;
	/** The sprite's width. **/
	static final int WIDTH = 48;

	/** The file name of the texture. */
	private static final String TEXTURE_FILE = "grass.png";

	/**
	 * Constructor for the GenericExplosionSprite. This is Package-private because sprites should
	 * not be directly created outside of the graphics system.
	 * 
	 * @param x
	 *            the x position of the explosion.
	 * @param y
	 *            the y position of the explosion.
	 */
	BackgroundSprite(int x, int y)
	{
		super(DrawLayer.BACKGROUND);

		Texture image = Graphics.getTexture(Graphics.TEXTURE_PATH + TEXTURE_FILE);
		frames = TextureUtil.splitFrames(image, HEIGHT, WIDTH);

		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		drawTexture(batch, camera, layer, frames[0][0]);
	}

	@Override
	public boolean isDisposed()
	{
		return false;
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