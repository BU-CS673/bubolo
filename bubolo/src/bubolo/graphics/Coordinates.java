package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

/**
 * Utility methods for working with coordinates.
 * @author BU CS673 - Clone Productions
 */
final class Coordinates
{
	/**
	 * Used to determine the relationship between world units, map units, and tile units
	 */
	private static int WORLD_SCALE = 32;
	/**
	 * Private constructor to prevent instantiation.
	 */
	private Coordinates()
	{
	}
	
	/**
	 * Converts world coordinates to camera coordinates.
	 * @param camera the game's camera.
	 * @param worldCoordinates the coordinates to convert.
	 * @return the converted coordinates.
	 */
	static Vector2 worldToCamera(Camera camera, Vector2 worldCoordinates)
	{
		return new Vector2(worldCoordinates.x - camera.position.x + WORLD_SCALE/2,
				worldCoordinates.y - camera.position.y + WORLD_SCALE/2);
	}
	
	/**
	 * Converts camera coordinates to world coordinates.
	 * @param camera the game's camera.
	 * @param cameraCoordinates the coordinates to convert.
	 * @return the converted coordinates.
	 */
	static Vector2 cameraToWorld(Camera camera, Vector2 cameraCoordinates)
	{
		return new Vector2(cameraCoordinates.x + camera.position.x - WORLD_SCALE/2,
				cameraCoordinates.y + camera.position.y - WORLD_SCALE/2);
	}
}
