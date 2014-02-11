package bubolo.world;

import java.util.UUID;

import bubolo.graphics.DrawLayer;
import bubolo.graphics.Sprite;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Road extends Modifier 
{
	Sprite<Road> sprite;
	public Road(){
		super();
		//TODO: Create RoadSprite class!
		//sprite = Sprite.create(this);
	}
	
	public Road(UUID id){
		super(id);
		//TODO: Create RoadSprite class!
		//sprite = Sprite.create(this);
	}

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = -5302600252810938564L;

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		if (sprite != null)
		{
			// TODO: Create RoadSprite class!
			// sprite.draw(batch, camera, layer);
		}
	}

	@Override
	public UUID getSpriteId()
	{
		// TODO: Create RoadSprite class!

		/*
		 * if (sprite != null){ return sprite.getId(); } else return null;
		 */

		return null;
	}

}
