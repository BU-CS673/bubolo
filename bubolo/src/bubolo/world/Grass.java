package bubolo.world;

import java.util.UUID;

import bubolo.graphics.DrawLayer;
import bubolo.graphics.Sprite;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Grass extends Terrain
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 5319713357245800006L;
	Sprite<Grass> sprite;

	public Grass(UUID id)
	{
		super(id);
		// TODO: Create GrassSprite class!
		// sprite = Sprite.create(this);
	}

	public Grass()
	{
		super();
		// TODO: Create GrassSprite class!
		//sprite = Sprite.create(this);
	}
	
	public Grass(UUID id, float x, float y, int w, int h, float rot)
	{
		super(id,x,y,w,h,rot);
	}
	
	public Grass(float x, float y, int w, int h, float rot)
	{
		super(x,y,w,h,rot);
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		if (sprite != null)
		{
			// TODO: Create GrassSprite class!
			// sprite.draw(batch, camera, layer);
		}
	}

	@Override
	public UUID getSpriteId()
	{
		// TODO: Create GrassSprite class!

		/*
		 * if (sprite != null){ return sprite.getId(); } else return null;
		 */

		return null;
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub

	}

}
