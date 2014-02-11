package bubolo.world;

import java.util.UUID;

import bubolo.graphics.DrawLayer;
import bubolo.graphics.Sprite;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tree extends StationaryElement
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 4072369464678115753L;
	Sprite<Tree> sprite;

	public Tree(UUID id)
	{
		super(id);
		//TODO: Create TreeSprite class!
		//sprite = Sprite.create(this);

	}

	public Tree()
	{
		super();
		//TODO: Create TreeSprite class!
		//sprite = Sprite.create(this);
	}
	
	public Tree(UUID id, float x, float y, int w, int h, float rot)
	{
		super(id,x,y,w,h,rot);
	}
	
	public Tree(float x, float y, int w, int h, float rot)
	{
		super(x,y,w,h,rot);
	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		if (sprite != null){
			//TODO: Create TreeSprite class!
			//sprite.draw(batch, camera, layer);
		}
	}

	@Override
	public UUID getSpriteId()
	{
		//TODO: Create TreeSprite class!
		/*
		if (sprite != null){
			return sprite.getId();
		}
		else return null;
		*/
		return null;
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub

	}

}
