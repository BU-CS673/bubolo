/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.util;

/**
 * An unchecked exception that is thrown when File System
 * @author BU CS673 - Clone Productions
 */
public class FileException extends GameException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new FileException with the given Throwable.
	 * @param exception the exception to wrap.
	 */
	public FileException(Throwable exception)
	{
		super(exception);
	}

	/**
	 * Constructs a new FileException with the given message.
	 * @param message the exception's message.
	 */
	public FileException(String message)
	{
		super(message);
	}
}
