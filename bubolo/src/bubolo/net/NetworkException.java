package bubolo.net;

import bubolo.util.GameException;

/**
 * Thrown to indicate that there is a problem with the network connection. 
 * @author BU CS673 - Clone Productions
 */
public class NetworkException extends GameException
{
	private static final long serialVersionUID = 7210544585250947253L;

	/**
	 * Constructs a NetworkException object with the specified message. 
	 * @param message the exception's detail message.
	 */
	public NetworkException(String message)
	{
		super(message);
	}

	/**
	 * Constructs a NetworkException object with the specified message and cause. 
	 * @param message the exception's detail message.
	 * @param cause the exception's cause.
	 */
	public NetworkException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
