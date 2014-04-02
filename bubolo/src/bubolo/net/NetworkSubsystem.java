/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net;

/**
 * @author BU CS673 - Clone Productions
 */
public interface NetworkSubsystem
{
	/**
	 * Queues a network command to be sent to the other players.
	 * 
	 * @param command the network command to send.
	 */
	void send(NetworkCommand command);
	
	/**
	 * Shuts down the system.
	 */
	void dispose();
}
