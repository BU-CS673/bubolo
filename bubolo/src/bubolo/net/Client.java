/**
 *
 */

package bubolo.net;

import java.net.InetAddress;

/**
 * The game client.
 * 
 * @author BU CS673 - Clone Productions
 */
public interface Client
{
	/**
	 * Attempts to connect to the specified IP address.
	 * 
	 * @param serverIpAddress the IP address of a server. Note that this isn't necessarily
	 * the <i>game</i> server, since clients also connect directly to each other.
	 * @throws NetworkException if a network error occurs.
	 */
	void connect(InetAddress serverIpAddress) throws NetworkException;
	
	/**
	 * Queues a network command to be sent to the other players.
	 * 
	 * @param command the network command to send.
	 */
	void send(NetworkCommand command);
}
