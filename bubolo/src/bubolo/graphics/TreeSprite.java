package bubolo.graphics;

import java.util.Random;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.world.entity.Entity;

/**
 * The graphical representation of a Tree.
 * 
 * @author BU673 - Clone Industries
 */
class TreeSprite extends AbstractEntitySprite<Entity>
{
	private Texture image;

	private float rotation;
	
	/** The file name of the texture. */
	private static final String TEXTURE_FILE = "tree.png";
	
	/**
	 * Constructor for the TreeSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system.
	 * 
	 * @param tree
	 *            Reference to the Tree that this TreeSprite represents.
	 */
	TreeSprite(Entity tree)
	{
		super(DrawLayer.THIRD, tree);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + TEXTURE_FILE);
		Random rand = new Random();
		rotation = (float) (rand.nextInt(4) * (Math.PI/2));
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
			drawTexture(batch, camera, layer, image);
		}
	}
	
	@Override
	public float getRotation()
	{
		return rotation;
	}
}
