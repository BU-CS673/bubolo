package bubolo.util;

/**
 * Thrown to indicate that there is a mistake in the game logic. 
 * @author BU CS673 - Clone Productions
 */
public class GameLogicException extends RuntimeException
{
	private static final long serialVersionUID = 2454273982095380164L;

	/**
	 * Constructs a GameLogicException object with the specified message. 
	 * @param message
	 */
	public GameLogicException(String message)
	{
		super(message);
	}
}
