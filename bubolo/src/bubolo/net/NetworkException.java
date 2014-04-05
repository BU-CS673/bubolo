/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

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
	 * Constructs a NetworkException object from the specified exception.
	 * @param exception the exception to wrap.
	 */
	public NetworkException(Throwable exception)
	{
		super(exception);
	}
}
