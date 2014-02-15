package bubolo.graphics;

import com.badlogic.gdx.graphics.Camera;

import bubolo.controllers.Controller;

/**
 * Interface for controllers that control the camera.
 * @author BU CS673 - Clone Productions
 */
public interface CameraController extends Controller
{
	/**
	 * Sets a reference to the camera. The camera must be set before calling the <code>update</code> method.
	 * @param camera reference to the camera.
	 */
	public void setCamera(Camera camera);
	
	/**
	 * Returns true if this controller has a reference to the camera, 
	 * or false otherwise.
	 * @return true if this controller has a reference to the camera.
	 */
	public boolean hasCamera();
}
