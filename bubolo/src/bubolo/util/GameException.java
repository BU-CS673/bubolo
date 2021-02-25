package bubolo.util;

/**
 * Base class for Game exceptions.
 * @author BU CS673 - Clone Productions
 */
public abstract class GameException extends RuntimeException
{
	private static final long serialVersionUID = -2050159306779497402L;

	/**
	 * Constructs a GameException object with the specified message. 
	 * @param message the exception's detail message.
	 */
	public GameException(String message)
	{
		super(message);
	}
	
	/**
	 * Constructs a GameException object from the specified exception.
	 * @param exception the exception to wrap.
	 */
	public GameException(Throwable exception)
	{
		super(exception);
	}
}
