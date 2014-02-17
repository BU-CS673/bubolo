package bubolo.graphics;

import java.util.UUID;

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
	 * be directly created outside of the graphics system (instead, call the
	 * Sprite.create(entity) static method).
	 * 
	 * @param tree
	 *            Reference to the Tree that this TreeSprite represents.
	 */
	TreeSprite(Tree tree)
	{
		super(UUID.fromString("689628e0-96c2-11e3-a5e2-0800200c9a66"), DrawLayer.OBJECTS, tree);

		image = Graphics.getTexture(Graphics.TEXTURE_PATH + "default.png");
		// TODO: Create a Tree texture!
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		drawTexture(batch, camera, layer, image);
	}
}
