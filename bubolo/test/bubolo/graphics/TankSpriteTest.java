package bubolo.graphics;

import org.junit.Test;

import bubolo.world.entity.concrete.Tank;

public class TankSpriteTest
{

	@Test
	public Sprite<Tank> constructTankSprite()
	{
		// Fails if the constructor throws an exception.
		Tank tank = new Tank();
		Sprite<Tank> sprite = new TankSprite(tank);
		return sprite;
	}

	@Test
	public void drawTankSprite()
	{
		Tank tank = new Tank();
		Sprite<Tank> sprite = new TankSprite(tank);
		sprite.draw(null, null, null);
	}
}
