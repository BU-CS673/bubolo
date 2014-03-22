/**
 *
 */

package bubolo.net;

/**
 * The game server.
 * 
 * @author BU CS673 - Clone Productions
 */
public interface Server
{
	/**
	 * Identifies this player as the game server, and begins accepting connections 
	 * from other players. <code>startServer</code> must be called before
	 * calling <code>connect</code>. There should only be one game server per game.
	 * 
	 * @throws NetworkException if a network error occurs.
	 * @throws IllegalStateException if the server was already started.
	 */
	void startServer() throws NetworkException, IllegalStateException;
	
	/**
	 * Queues a network command to be sent to the other players.
	 * 
	 * @param command the network command to send.
	 */
	void send(NetworkCommand command);
}
