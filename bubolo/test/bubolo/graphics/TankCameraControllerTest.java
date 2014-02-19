package bubolo.graphics;

import static org.junit.Assert.*;

import org.junit.Test;

import bubolo.world.entity.concrete.Tank;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class TankCameraControllerTest
{

	@Test
	public void testTankCameraController()
	{
		CameraController controller = new TankCameraController(new MockTank());
	}

	@Test
	public void testSetCamera()
	{
		Camera camera = new OrthographicCamera();
		CameraController controller = new TankCameraController(new MockTank());
		controller.setCamera(camera);
	}

	@Test
	public void testHasCameraFalse()
	{
		CameraController controller = new TankCameraController(new MockTank());
		assertFalse(controller.hasCamera());
	}
	
	@Test
	public void testHasCameraTrue()
	{
		Camera camera = new OrthographicCamera();
		CameraController controller = new TankCameraController(new MockTank());
		controller.setCamera(camera);
		assertTrue(controller.hasCamera());
	}

	@Test
	public void testUpdate()
	{
		Camera camera = new OrthographicCamera();
		CameraController controller = new TankCameraController(new MockTank());
		controller.setCamera(camera);
		controller.update(new MockWorld());
	}
	
	@Test
	public void testUpdateBad()
	{
		CameraController controller = new TankCameraController(new MockTank());
		try {
			controller.update(new MockWorld());
			fail("CameraController does not have a camera, but was able to update");
		} catch (Exception e) {}
	}
	
	@Test
	public void testUpdateLessThanZero()
	{
		Camera camera = new OrthographicCamera(20, 30);
		Tank tank = new MockTank();
		tank.setX(-100).setY(-120);
		CameraController controller = new TankCameraController(tank);
		controller.setCamera(camera);
		controller.update(new MockWorld());
	}

	@Test
	public void testUpdateGreaterThanWorldWidth()
	{
		Camera camera = new OrthographicCamera(20, 30);
		Tank tank = new MockTank();
		tank.setX(200).setY(200);
		CameraController controller = new TankCameraController(tank);
		controller.setCamera(camera);
		controller.update(new MockWorld());
	}
}
