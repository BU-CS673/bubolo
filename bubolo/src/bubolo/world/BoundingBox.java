package bubolo.world;

public class BoundingBox
{
	private int xStart;
	private int xEnd;
	private int yStart;
	private int yEnd;

	public BoundingBox(int x1, int y1, int x2, int y2)
	{
		xStart = x1;
		xEnd = x2;
		yStart = y1;
		yEnd = y2;
	}

	/**
	 * Checks to see if two bounding boxes intersect by checking all corner points of one
	 * box against the bounds of the other. Note that the order here does not matter, and
	 * that the edges of boxes are inclusive to points.
	 * 
	 * @param b1
	 *            is the first bounding box to check for intersections.
	 * @param b2
	 *            is the second bounding box to check for intersections.
	 * @return true if the two boxes intersect, false otherwise.
	 */
	public static boolean doBoxesIntersect(BoundingBox b1, BoundingBox b2)
	{
		if (isPointInBox(b2, b1.xStart, b1.yStart) || isPointInBox(b2, b1.xStart, b1.yEnd)
				|| isPointInBox(b2, b1.xEnd, b1.yStart) || isPointInBox(b2, b1.xEnd, b1.yEnd))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private static boolean isPointInBox(BoundingBox targetBox, int x, int y)
	{
		if ((x >= targetBox.xStart && x <= targetBox.xEnd)
				&& (y >= targetBox.yStart && y <= targetBox.yEnd))
		{
			return true;
		}
		else
			return false;
	}
}
