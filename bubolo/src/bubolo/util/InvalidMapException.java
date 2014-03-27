package bubolo.util;

/**
 * Thrown to indicate an error in a provided map file that would generate an incomplete or illogical map 
 * @author BU673 - Clone Productions
 */
public class InvalidMapException extends GameException
{

	private static final long serialVersionUID = -2041925775039679247L;

	/**
	 * Constructs a InvalidMapException object with the specified message. 
	 * @param message the exception's detail message.
	 */
	public InvalidMapException(String message)
	{
		super(message);
	}

	/**
	 * Constructs a InvalidMapException object from the specified exception.
	 * @param exception the exception to wrap.
	 */
	public InvalidMapException(Throwable exception)
	{
		super(exception);
	}
}
