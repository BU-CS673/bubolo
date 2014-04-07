/**
 *
 */

package bubolo.net;

/**
 * Enables a subsystem to monitor network events. The subsystem must implement this interface and
 * then register with the network system using the following code:
 * <p>
 * <code>
 * Network net = NetworkSystem.getInstance();<br>
 * net.registerObserver(this);
 * </code>
 * </p>
 * 
 * @author BU CS673 - Clone Productions
 */
public interface NetworkObserver
{
	/**
	 * Called when this player has successfully connected to the server.
	 * 
	 * @param serverName
	 *            the name of the server player.
	 */
	void onConnect(String serverName);

	/**
	 * Called when a new client has connected to the server.
	 * 
	 * @param clientName
	 *            the name of the new client player.
	 */
	void onClientConnected(String clientName);

	/**
	 * Called when a client player has disconnected.
	 * 
	 * @param clientName
	 *            the name of the player who disconnected.
	 */
	void onClientDisconnected(String clientName);
}
