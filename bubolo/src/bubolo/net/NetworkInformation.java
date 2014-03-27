/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net;

/**
 * Information about the network.
 * @author BU CS673 - Clone Productions
 */
public abstract class NetworkInformation
{	
	/**
	 * The port that the game server listens to.
	 */
	public static final int GAME_PORT = 19014;
	
	/**
	 * The number of network ticks per second. 
	 */
	public static final int TICKS_PER_SECOND = 10;
	
	/**
	 * The number of milliseconds per network tick. This is calculated by dividing
	 * the number of milliseconds in a second by the TICKS_PER_SECOND value.
	 */
	public static final long MILLIS_PER_TICK = 1000 / TICKS_PER_SECOND;
}
