package bubolo.graphics;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import bubolo.world.entity.concrete.Tank;

public class MockSpriteTest
{

	@Test
	public void createMockSprite()
	{
		Sprite<Tank> mock = new MockSprite<Tank>();
	}

	@Test
	public void drawMockSprite()
	{
		Sprite<Tank> mock = new MockSprite<Tank>();
		mock.draw(null, null, DrawLayer.TANKS);
	}
	
	@Test
	public void getId()
	{
		Sprite<Tank> mock = new MockSprite<Tank>();
		assertEquals(UUID.fromString("b6615e40-7390-4a38-a331-f3015925b68f"), mock.getId());
	}
	
	@Test
	public void getDrawLayer()
	{
		Sprite<Tank> mock = new MockSprite<Tank>();
		assertEquals(DrawLayer.OBJECTS, mock.getDrawLayer());
	}
}
