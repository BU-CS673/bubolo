package bubolo.audio;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.audio.Sound;

/**
 * The top-level class for the Sound system.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Audio
{
	// Stores the sounds, ensuring that only one is needed for all instances. 
	private static Map<String, Sound> sounds = new HashMap<String, Sound>();
}
