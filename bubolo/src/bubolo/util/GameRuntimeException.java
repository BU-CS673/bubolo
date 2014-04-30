package bubolo.util;

/**
 * Thrown to indicate that a runtime error occurred.
 * 
 * @author BU CS673 - Clone Productions
 */
public class GameRuntimeException extends GameException
{
	private static final long serialVersionUID = 2454273982095380164L;

	/**
	 * Constructs a GameRuntimeException object from the specified exception.
	 * 
	 * @param exception
	 *            the exception to wrap.
	 */
	public GameRuntimeException(Throwable exception)
	{
		super(exception);
	}
}
