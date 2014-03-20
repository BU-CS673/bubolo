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
	 * Represents the total number of different damaged states that exist in this sprite's
	 * texture.
	 */
	private final int DAMAGED_STATES = 4;

	/**
	 * Represents the discrete damaged state that the sprite should be in, calculated from
	 * the Entity's relative health.
	 */
	private int damagedState;

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

	/**
	 * Returns an integer between 0 and DAMAGED_STATES -1 representing the damaged state
	 * of this Sprite's Entity. 0 means 0 HP/fully dead!
	 */
	private void updateDamagedState()
	{
		// Compute the amount of health remaining for this Sprite's Entity as a fraction
		// of its max HP.
		float healthFraction = (float) this.getEntity().getHP() / this.getEntity().getMaxHP();
		// Convert that fraction to an integer between 0 and DAMAGED_STATES -1.
		damagedState = Math.round(healthFraction * DAMAGED_STATES);
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		updateDamagedState();
		if (isEntityDisposed())
		{
			Sprites.getInstance().removeSprite(this);
		}
		else
		{
			// TODO: Point to different texture regions based on the damagedState field,
			// which changes with Entity HP percentage.
			drawTexture(batch, camera, layer, frames[this.getEntity().getState()]);
		}
	}
}