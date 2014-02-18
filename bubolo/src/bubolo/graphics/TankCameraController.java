package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;

import bubolo.world.World;
import bubolo.world.entity.concrete.Tank;
import static com.google.common.base.Preconditions.*;

/**
 * Controller that moves the camera based on the tank's position. 
 * @author BU CS673 - Clone Productions
 */
class TankCameraController implements CameraController
{
	private Tank tank;
	private Camera camera;
	
	/**
	 * Constructs a TankCameraController. Package-private because TankCameraController's
	 * are internal to the Graphics system.
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
			throw  new IllegalStateException("No camera has been set for this TankCameraController.");
		
		float tankX = calculateCameraX(camera, tank, world);
		float tankY = calculateCameraY(camera, tank, world);
		
		// The libgdx camera's position is from the bottom left corner: https://github.com/libgdx/libgdx/wiki/Orthographic-camera 
		camera.position.set(tankY, tankX, 0.f);
		camera.update();
	}
	
	private static float calculateCameraX(Camera camera, Tank tank, World world)
	{
		float tankX = tank.getX() + tank.getWidth() / 2.f;
		
		float cameraX = tankX - camera.viewportWidth / 2.f;
		if (cameraX < 0)
		{
			cameraX = 0;
		}
		else if (cameraX > camera.viewportWidth + world.getMapWidth()) 
		{
			cameraX = world.getMapWidth() - camera.viewportWidth;
		}
		
		return cameraX;
	}
	
	private static float calculateCameraY(Camera camera, Tank tank, World world)
	{
		float tankY = tank.getY() + tank.getHeight() / 2.f;
		
		float cameraY = tankY - camera.viewportHeight / 2.f;
		if (cameraY < 0)
		{
			cameraY = 0;
		}
		else if (cameraY > camera.viewportHeight + world.getMapHeight()) 
		{
			cameraY = world.getMapHeight() - camera.viewportHeight;
		}
		
		return cameraY;
	}
}
