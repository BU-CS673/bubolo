package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.util.TextureUtil;
import bubolo.world.entity.concrete.Pillbox;

/**
 * The graphical representation of a Pillbox
 * 
 * @author BU673 - Clone Industries
 */
class PillboxSprite extends Sprite<Pillbox>
{
	private TextureRegion[][] allFrames;

	/**
	 * Represents the total number of different damaged states that exist in this sprite's
	 * texture.
	 */
	private final int DAMAGED_STATES = 5;

	private int colorId = ColorSets.NEUTRAL;

	/**
	 * Represents the discrete damaged state that the sprite should be in, calculated from
	 * the Entity's relative health. * 
	 * NOTE: Currently throws a "field not used" warning,
	 * as expected, since the damaged states aren't implemented yet.
	 */
	private int damagedState;

	/**
	 * Constructor for the PillboxSprite. This is Package-private because sprites should
	 * not be directly created outside of the graphics system.
	 * 
	 * @param pillbox
	 *            Reference to the pillbox that this PillboxSprite represents.
	 */
	PillboxSprite(Pillbox pillbox)
	{
		super(DrawLayer.STATIONARY_ELEMENTS, pillbox);

		allFrames = TextureUtil.splitFrames(
				Graphics.getTexture(Graphics.TEXTURE_PATH + "pillbox.png"), 32, 32);
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

	private void updateColorSet()
	{
		if (!this.getEntity().isOwned())
		{
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
		updateDamagedState();
		updateColorSet();

		if (isEntityDisposed())
		{
			Sprites.getInstance().removeSprite(this);
			return;
		}
		else
		{
			// TODO: Point to different texture regions based on the damagedState field,
			// which changes with Entity HP percentage.
			drawTexture(batch, camera, layer, allFrames[0][colorId]);
		}
	}
}
