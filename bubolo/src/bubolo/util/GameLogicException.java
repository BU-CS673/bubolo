package bubolo.util;

/**
 * Thrown to indicate that there is a mistake in the game logic.
 * @author BU CS673 - Clone Productions
 */
public class GameLogicException extends GameException
{
	private static final long serialVersionUID = 2454273982095380164L;

	/**
	 * Constructs a GameLogicException object with the specified message. 
	 * @param message the exception's detail message.
	 */
	public GameLogicException(String message)
	{
		super(message);
	}
	
	/**
	 * Constructs a GameLogicException object with the specified message and cause. 
	 * @param message the exception's detail message.
	 * @param cause the exception's cause.
	 */
	public GameLogicException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
