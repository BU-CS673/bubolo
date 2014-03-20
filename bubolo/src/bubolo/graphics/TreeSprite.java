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
class TreeSprite extends Sprite<Tree>
{
	private Texture image;

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

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "tree.png");
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		if (!isEntityDisposed())
		{
			drawTexture(batch, camera, layer, image);
		}
		else
		{
			Sprites.getInstance().removeSprite(this);
		}
	}
}
