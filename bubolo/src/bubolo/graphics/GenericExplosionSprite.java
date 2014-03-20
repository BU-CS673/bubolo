package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.TextureUtil;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.GenericExplosion;

/**
 * The graphical representation of a GenericExplosion entity.
 * 
 * @author BU673 - Clone Industries
 */
class GenericExplosionSprite extends Sprite<Entity>
{
	private TextureRegion[][] frames;

	/**
	 * Constructor for the GenericExplosionSprite. This is Package-private because sprites
	 * should not be directly created outside of the graphics system.
	 * 
	 * @param GenericExplosion
	 *            Reference to the GenericExplosion that this GenericExplosionSprite
	 *            represents.
	 */
	GenericExplosionSprite(GenericExplosion exp)
	{
		super(DrawLayer.EFFECTS, exp);

		frames = TextureUtil.splitFrames(
				Graphics.getTexture(Graphics.TEXTURE_PATH + "genericExplosion.png"), 26, 26);
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		if (isEntityDisposed())
		{
			Sprites.getInstance().removeSprite(this);
		}
		else
		{
			drawTexture(batch, camera, layer, frames[0][0]);
		}
	}
}