package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.world.entity.concrete.Tree;

/**
 * The graphical representation of a Tree.
 * 
 * @author BU673 - Clone Industries
 */
class TreeSprite extends AbstractEntitySprite<Tree>
{
	private Texture image;

	/** The file name of the texture. */
	static final String TEXTURE_FILE = "tree.png";
	
	/**
	 * Constructor for the TreeSprite. This is Package-private because sprites should not
	 * be directly created outside of the graphics system.
	 * 
	 * @param tree
	 *            Reference to the Tree that this TreeSprite represents.
	 */
	TreeSprite(Tree tree)
	{
		super(DrawLayer.STATIONARY_ELEMENTS, tree);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + TEXTURE_FILE);
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
}
