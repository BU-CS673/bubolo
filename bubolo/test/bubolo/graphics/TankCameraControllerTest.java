package bubolo.graphics;

import static org.junit.Assert.*;

import org.junit.Test;

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

}
