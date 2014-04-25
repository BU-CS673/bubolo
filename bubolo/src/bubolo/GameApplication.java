package bubolo;

import com.badlogic.gdx.ApplicationListener;

/**
 * Defines the interface for the main game class.
 * @author BU CS673 - Clone Productions
 */
public interface GameApplication extends ApplicationListener
{
	public enum State
	{
		MAIN_MENU,
		
		GAME_LOBBY,
		
		GAME
	}
	
	/**
	 * Returns true if the game's subsystems have been set up, or false otherwise.
	 * @return true if the game's subsystems have been set up.
	 */
	boolean isReady();
	
	/**
	 * Specifies whether the game has started.
	 * @return true if the game has started.
	 */
	boolean isGameStarted();
	
	void setState(State state);
}
