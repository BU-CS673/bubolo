package bubolo.graphics;

import static org.junit.Assert.*;

import org.junit.Test;

import bubolo.world.Tank;

public class TankSpriteTest
{

	@Test
	public void constructTankSprite()
	{
		// Fails if the constructor throws an exception.
		Sprite<Tank> sprite = new TankSprite();
	}

	@Test
	public void drawTankSprite()
	{
		Sprite<Tank> sprite = new TankSprite();
		Tank tank = new Tank();
		sprite.draw(DrawLayer.test, tank);
	}
}
