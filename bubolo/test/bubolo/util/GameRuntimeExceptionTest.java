/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author BU CS673 - Clone Productions
 */
public class GameRuntimeExceptionTest
{

	/**
	 * Test method for
	 * {@link bubolo.util.GameRuntimeException#GameRuntimeException(java.lang.Throwable)}.
	 */
	@Test
	public void testGameRuntimeException()
	{
		Throwable t = new Throwable();
		GameRuntimeException e = new GameRuntimeException(t);
		
		assertSame(t, e.getCause());
	}
}
