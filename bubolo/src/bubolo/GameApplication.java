package bubolo;

import com.badlogic.gdx.ApplicationListener;

/**
 * Defines the interface for the main game class.
 * @author BU CS673 - Clone Productions
 */
public interface GameApplication extends ApplicationListener
{
	/**
	 * Returns true if the game's subsystems have been set up, or false otherwise.
	 * @return true if the game's subsystems have been set up.
	 */
	public boolean isReady();
	
	/**
	 * Specifies whether the game has started.
	 * @return true if the game has started.
	 */
	public boolean isGameStarted();
}
