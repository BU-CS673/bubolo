package bubolo.world;

import java.util.UUID;

import bubolo.graphics.DrawLayer;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Dummy Entity class for unit testing methods without any Graphics involvement.
 * This allows the tests to run outside of an OpenGL environment, because no textures are fetched.
 * @author BU CS673 - Clone Productions
 */
public class DummyEntity extends Entity
{

	/** 
	 *  Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = -2381816338619957177L;
	
	public DummyEntity(float x, float y, int w,
			int h, float r)
	{
		super(x,y,w,h,r);
	}
	
	public DummyEntity(){
		super();
	}

	public DummyEntity(UUID id, float x, float y, int w,
			int h, float r)
	{
		super(id,x,y,w,h,r);
	}
	
	public DummyEntity(UUID id){
		super(id);
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		// do nothing
	}

	@Override
	public UUID getSpriteId()
	{
		// do nothing
		return null;
	}

	@Override
	public void update()
	{
		// do nothing
		
	}

}
