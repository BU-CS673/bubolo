package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import bubolo.test.MockTank;

/**
 * A mock Sprite class used for testing calls to drawTexture(batch, camera, layer, TextureRegion)
 * in the Graphics system.
 * @author BU CS673 - Clone Productions
 */
public class MockSpriteTextureRegion extends AbstractEntitySprite<MockTank>
{
	/**
	 * Constructs a mock Tank object.
	 */
	public MockSpriteTextureRegion()
	{
		super(DrawLayer.THIRD, new MockTank());
	}

	@Override
	void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
	}
}
