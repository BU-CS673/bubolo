package bubolo.graphics;

import java.util.UUID;

import bubolo.world.Tank;

/**
 * The graphical representation of a Tank.
 * @author BU673 - Clone Industries
 */
public class TankSprite extends Sprite<Tank>
{
	private Texture image;
	
	TankSprite()
	{
		super(UUID.fromString("13eb9d6a-8965-43fc-a4aa-82fb70c9045f");
	}

	@Override
	public void draw(DrawLayer layer, Tank entity)
	{
		// TODO: implement this once the graphics library is selected.
	}
}
