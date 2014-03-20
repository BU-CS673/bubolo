package bubolo.world.entity;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import bubolo.world.Tile;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Grass;

public class EntityTestCase
{

	/* Default Entity Testing Parameters */
	static final UUID TARGET_UUID = UUID.fromString("5231b533-ba17-4787-98a3-f2df37de2aD7"); // random
	// UUID
	// string
	static final float TARGET_X = 26.7f;
	static final float TARGET_Y = 72.5f;
	static final float TARGET_ROT = (float) Math.PI / 2;
	static final int TARGET_WIDTH = 50;
	static final int TARGET_HEIGHT = 100;
	
	static Tile TARGET_TILE= new Tile(2, 3, new Grass());

	public static Entity setTestParams(Entity e)
	{
		e.setParams(TARGET_X, TARGET_Y, TARGET_ROT);
		e.setWidth(TARGET_WIDTH);
		e.setHeight(TARGET_HEIGHT);
		return e;
	}

	/**
	 * Tests to see if the initial parameters of this Entity are equivalent to those of
	 * another Entity.
	 * 
	 * @return true if the Entities match each other and false if they do not.
	 */
	public static boolean matches(Entity e1, Entity e2)
	{
		if (!e1.getId().equals(e2.getId()) || e1.getX() != e2.getX() || e1.getY() != e2.getY()
				|| e1.getWidth() != e2.getWidth() || e1.getHeight() != e2.getHeight()
				|| e1.getRotation() != e2.getRotation())
		{
			return false;
		}
		else
			return true;
	}

	/**
	 * Same as matches(), but ignores UUID.
	 * 
	 * @return true if the Entities match each other and false if they do not.
	 */
	public static boolean matches_NO_UUID(Entity e1, Entity e2)
	{
		if (e1.getX() != e2.getX() || e1.getY() != e2.getY() || e1.getWidth() != e2.getWidth()
				|| e1.getHeight() != e2.getHeight() || e1.getRotation() != e2.getRotation())
		{
			return false;
		}
		else
			return true;
	}
}
