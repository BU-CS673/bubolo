package bubolo.world;

import static org.junit.Assert.*;

import org.junit.Test;

public class EntityTestCase
{

	/**
	 * Tests to see if the initial parameters of this Entity are equivalent to those of
	 * another Entity.
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
	 * @return true if the Entities match each other and false if they do not.
	 */
	public static boolean matches_NO_UUID(Entity e1, Entity e2)
	{
		if (e1.getX() != e2.getX() || e1.getY() != e2.getY()
				|| e1.getWidth() != e2.getWidth() || e1.getHeight() != e2.getHeight()
				|| e1.getRotation() != e2.getRotation())
		{
			return false;
		}
		else
			return true;
	}
}
