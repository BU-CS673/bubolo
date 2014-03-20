package bubolo.world;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class BoundingBoxTest
{
	static BoundingBox b1;
	static BoundingBox b2;
	static BoundingBox b3;

	@BeforeClass
	public static void setup()
	{
		b1 = new BoundingBox(0, 0, 5, 5);
		b2 = new BoundingBox(3, 3, 6, 6);
		b3 = new BoundingBox(-1, -1, -1, -1);
	}

	@Test
	public void doBoxesIntersect()
	{
		assertEquals("Boxes do not intersect, when they should!", true,
				BoundingBox.doBoxesIntersect(b1, b2));
	}

	@Test
	public void doBoxesNotIntersect()
	{
		assertEquals("Boxes intersect, when they shouldn't!", false,
				BoundingBox.doBoxesIntersect(b1, b3));
	}
}
