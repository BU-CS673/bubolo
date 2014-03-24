package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;

import bubolo.world.World;
import bubolo.world.entity.concrete.Tank;
import static com.google.common.base.Preconditions.*;

/**
 * Controller that moves the camera based on the tank's position.
 * 
 * @author BU CS673 - Clone Productions
 */
class TankCameraController implements CameraController
{
	private Tank tank;
	private Camera camera;

	/**
	 * Constructs a TankCameraController. Package-private because TankCameraController's are
	 * internal to the Graphics system.
	 * 
	 * @param tank
	 */
	TankCameraController(Tank tank)
	{
		this.tank = checkNotNull(tank);
	}

	@Override
	public void setCamera(Camera camera)
	{
		this.camera = checkNotNull(camera);
	}

	@Override
	public boolean hasCamera()
	{
		return (camera != null);
	}

	@Override
	public void update(World world)
	{
		if (camera == null)
		{
			throw new IllegalStateException("No camera has been set for this TankCameraController.");
		}

		float tankX = calculateCameraX(camera, tank, world);
		float tankY = calculateCameraY(camera, tank, world);

		// The libgdx camera's position is from the bottom left corner:
		// https://github.com/libgdx/libgdx/wiki/Orthographic-camera
		camera.position.set(tankX, tankY, 0.f);
		camera.update();
	}

	private static float calculateCameraX(Camera camera, Tank tank, World world)
	{
		float tankX = tank.getX();

		float cameraX = tankX - camera.viewportWidth / 2.f;
		if (cameraX < -16)
		{
			cameraX = -16 ;
		}
		else if (cameraX > world.getMapWidth() - camera.viewportWidth - 16)
		{
			cameraX = world.getMapWidth() - camera.viewportWidth - 16;
		}

		return cameraX;
	}

	private static float calculateCameraY(Camera camera, Tank tank, World world)
	{
		float tankY = tank.getY();
		float cameraY = tankY - camera.viewportHeight / 2.f;
		if (cameraY < -16)
		{
			cameraY = -16;
		}
		else if (cameraY > world.getMapHeight() - camera.viewportHeight - 16)
		{
			cameraY = world.getMapHeight() - camera.viewportHeight - 16;
		}

		return cameraY;
	}
}
