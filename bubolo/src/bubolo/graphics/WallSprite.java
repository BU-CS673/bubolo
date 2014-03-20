package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.TextureUtil;
import bubolo.world.entity.concrete.Wall;

/**
 * The graphical representation of a Wall
 * 
 * @author BU673 - Clone Industries
 */
class WallSprite extends Sprite<Wall>
{
	private TextureRegion[] frames;

	/**
	 * Constructor for the WallSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system.
	 */
	WallSprite(Wall wall)
	{
		super(DrawLayer.STATIONARY_ELEMENTS, wall);

		Texture tex = Graphics.getTexture(Graphics.TEXTURE_PATH + "wall.png");
		frames = TextureUtil.adaptiveSplit_16(tex);
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
			drawTexture(batch, camera, layer, frames[this.getEntity().getState()]);
		}

		/**
		 * Get the current HP of this object and determine currentState for Wall there are
		 * 4 appearances 100-75 = 3 74-50 = 2 49-25 = 1 24-0 = 0
		 */

		/*
		 * int currentState = 0; switch(currentState){ case 3:
		 * specificImage.setRegion(0,32,32,32); drawTexture(batch, camera, layer,
		 * specificImage); break; case 2: specificImage.setRegion(64,32,32,32);
		 * drawTexture(batch, camera, layer, specificImage); break; case 1:
		 * specificImage.setRegion(128,32,32,32); drawTexture(batch, camera, layer,
		 * specificImage); break; case 0: specificImage.setRegion(160,32,32,32);
		 * drawTexture(batch, camera, layer, specificImage); break; default: throw new
		 * GameLogicException("Programming error in WallSprite: default case reached."); }
		 */
	}
}