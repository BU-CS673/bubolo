package bubolo.graphics;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class CoordinatesTest
{
	@Test
	public void worldToCamera()
	{
		Camera camera = new OrthographicCamera();
		camera.translate(100.f, 40.f, 0.f);
		camera.update();
		Vector2 worldCoord = new Vector2(40.f, 200.f);
		Vector2 cameraCoord = Coordinates.worldToCamera(camera, worldCoord);
		
		assertEquals(-60, (int)cameraCoord.x);
		assertEquals(160, (int)cameraCoord.y);
	}
	
	@Test
	public void cameraToWorld()
	{
		Camera camera = new OrthographicCamera();
		camera.translate(100.f, 40.f, 0.f);
		camera.update();
		Vector2 cameraCoord = new Vector2(-60.f, 160.f);
		Vector2 worldCoord = Coordinates.cameraToWorld(camera, cameraCoord);
		
		assertEquals(40, (int)worldCoord.x);
		assertEquals(200, (int)worldCoord.y);
	}
}
