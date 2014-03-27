package bubolo.util;

/**
 * Thrown to indicate that an operation requiring specific texture formatting was
 * attempted on a texture that does not meet the requirements.
 * 
 * @author BU CS673 - Clone Productions
 */
public class TextureFormatException extends GameException
{

	/**
	 * Used in serialization / de-serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a TextureFormatException object with the specified message.
	 * 
	 * @param message
	 *            the exception's detail message.
	 */
	public TextureFormatException(String message)
	{
		super(message);
	}

	/**
	 * Constructs a TextureFormatException object from the specified exception.
	 * 
	 * @param exception
	 *            the exception to wrap.
	 */
	public TextureFormatException(Throwable exception)
	{
		super(exception);
	}
}
