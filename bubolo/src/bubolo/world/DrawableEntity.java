package bubolo.world;

import bubolo.graphics.DrawLayer;
import bubolo.graphics.Sprite;

public class DrawableEntity extends Entity
{

	private Sprite sprite;
	private int height; // height of this entity in pixels
	private int width; // width of this entity in pixels
	private float rotation; // rotation of this entity in radians

	public void draw(DrawLayer layer)
	{
		// TODO: Establish drawing behavior for DrawableEntities.
	}

	public int getHeight()
	{
		return height;
	}

	public int getWidth()
	{
		return width;
	}

	public void update()
	{
		// TODO: Establish updating methods for Entities.
	}

	public float getRotation()
	{
		return rotation;
	}

	public Entity setRotation(float newRotation)
	{
		rotation = newRotation;
		return this;
	}
}
