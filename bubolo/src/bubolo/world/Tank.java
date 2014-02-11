package bubolo.world;

import java.util.UUID;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.graphics.DrawLayer;
import bubolo.graphics.Sprite;

/**
 * The tank, which may be controlled by a local player, a networked player, or an AI bot.
 * 
 * @author BU673 - Clone Industries
 */
public class Tank extends Actor
{
	Sprite<Tank> sprite;

	public Tank(UUID id, float x, float y, int w, int h, float rot)
	{
		super(id, x, y, w, h, rot);
		sprite = Sprite.create(this);
		// TODO Auto-generated constructor stub
	}

	public Tank(float x, float y, int w, int h, float rot)
	{
		super(x, y, w, h, rot);
		sprite = Sprite.create(this);
		// TODO Auto-generated constructor stub
	}

	public Tank(UUID id)
	{
		super(id);
		sprite = Sprite.create(this);
		// TODO Auto-generated constructor stub
	}

	public Tank()
	{
		super();
	}

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 457933513574468829L;

	@Override
	public void update()
	{
		// TODO Determine update behavior for Tanks.

	}

	/* Drawable Methods */

	@Override
	public UUID getSpriteId()
	{
		if (sprite != null)
		{
			return sprite.getId();
		}

		else
			return null;
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{
		if (sprite != null)
		{
			sprite.draw(batch, camera, layer);
		}

	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}
}
