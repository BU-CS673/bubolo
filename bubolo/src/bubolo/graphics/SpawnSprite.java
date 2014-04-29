package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.TextureUtil;
import bubolo.world.entity.Entity;

/**
 * The graphical representation of Spawn entity.
 * 
 * @author BU673 - Clone Industries
 */
class SpawnSprite extends AbstractEntitySprite<Entity>
{
	private Texture image;

	private TextureRegion[][] frames;

	// Whether the sprite should be drawn.
	private boolean visible;

	/** The file name of the texture. */
	private static final String TEXTURE_FILE = "spawn.png";

	/**
	 * Constructor for the SpawnSprite. This is Package-private because sprites should not be
	 * directly created outside of the graphics system.
	 * 
	 * @param spawn
	 *            Reference to the spawn that this SpawnSprite represents.
	 */
	SpawnSprite(Entity spawn)
	{
		super(DrawLayer.SECOND, spawn);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + TEXTURE_FILE);
		frames = TextureUtil.splitFrames(image, 32, 32);
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		if (isDisposed())
		{
			Sprites.getInstance().removeSprite(this);
		}
		else if (visible)
		{
			drawTexture(batch, camera, layer, frames[0][0]);
		}
	}

	/**
	 * Specifies whether the spawn sprite should be visible. The default is false.
	 * 
	 * @param visible
	 *            true if the spawn sprite should be visible, or false otherwise.
	 */
	void setVisible(boolean visible)
	{
		this.visible = visible;
	}
	
	/**
	 * Specifies whether the spawn sprite is visible.
	 * @return true if the spawn sprite is visible, or false otherwise.
	 */
	boolean getVisible()
	{
		return visible;
	}
}
