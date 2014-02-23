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
	 * Constructs a GameLogicException object from the specified exception.
	 * @param exception the exception to wrap.
	 */
	public GameLogicException(Throwable exception)
	{
		super(exception);
	}
}
