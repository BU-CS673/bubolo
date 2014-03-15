package bubolo.util;

/**
 * Helper math functions.
 * @author BU CS673 - Clone Productions
 */
public abstract class GameMath
{
	/**
	 * Calculates the angle between two points, in radians.
	 * @param point1x the x starting point.
	 * @param point1y the y starting point.
	 * @param point2x the x ending point.
	 * @param point2y the y ending point.
	 * @return the angle between two points, in radians.
	 */
	public static <T extends Number> float angleInRadians(T point1x, T point1y, T point2x, T point2y)
	{
		double deltaX = point2x.doubleValue() - point1x.doubleValue();
		double deltaY = point2y.doubleValue() - point1y.doubleValue();
		return (float)Math.atan2(deltaY, deltaX);
	}
}
