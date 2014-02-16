package bubolo.graphics;

import java.util.UUID;

import bubolo.graphics.DrawLayer;
import bubolo.graphics.Sprite;
import bubolo.world.entity.base.Entity;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Mock sprites should be used for unit testing Model code. They have no other purpose,
 * and do not do anything.
 * @author BU CS673 - Clone Productions
 */
public class MockSprite<T extends Entity> extends Sprite<T>
{
	/**
	 * Constructs a MockSprite, 
	 */
	public MockSprite()
	{
		super(UUID.fromString("b6615e40-7390-4a38-a331-f3015925b68f"), DrawLayer.OBJECTS, null);
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		// Do nothing, since this is a mock object.
	}
	
	
}
