package bubolo.net;

import java.net.InetAddress;
import bubolo.world.World;

/**
 * The interface for the Network system.
 * 
 * @author BU CS673 - Clone Productions
 */
public interface Network
{
	/**
	 * Returns true if the network is active, or false otherwise.
	 * 
	 * @return true if the network is active.
	 */
	boolean isActive();
	
	/**
	 * Returns true if this player is the game server, or false otherwise. Note that
	 * all players run clients and servers, because everyone connects to each other
	 * to reduce latency. The official server is only used for certain non-game
	 * functionality, such as acting as the central connection point and sending
	 * out ip addresses of all players.
	 * 
	 * @return true if this player is the game server, or false otherwise. 
	 */
	boolean isGameServer();
	
	/**
	 * Shuts down the network system.
	 */
	void destroy();
	
	/**
	 * Identifies this player as the server, and begins accepting connections 
	 * from other players. <code>startServer</code> must be called before
	 * calling <code>connect</code>.
	 * 
	 * @param isGameServer true if this player is the game server, or false otherwise. 
	 * There should only be one game server per game.
	 * @throws NetworkException if a network error occurs.
	 * @throws IllegalStateException if the server was already started.
	 */
	void startServer(boolean isGameServer) throws NetworkException, IllegalStateException;
	
	/**
	 * Attempts to connect to the specified IP address. <code>startServer</code> must be called before
	 * calling <code>connect</code>.
	 * 
	 * @param serverIpAddress the IP address of a server. Note that this isn't necessarily
	 * the <i>game</i> server, since clients also connect directly to each other.
	 * @throws NetworkException if a network error occurs.
	 * @throws IllegalStateException if <code>connect</code> is called before 
	 * <code>startServer</code> was called.
	 */
	void connect(InetAddress serverIpAddress) throws NetworkException, IllegalStateException;
	
	/**
	 * Queues a network command to be sent to the other players.
	 * 
	 * @param command the network command to send.
	 */
	void send(NetworkCommand command);
	
	/**
	 * Performs all network system updates. This should be called once 
	 * per game tick.
	 * 
	 * @param world reference to the game world. 
	 */
	void update(World world);
	
	/**
	 * Runs a NetworkCommand in the game logic thread.
	 * 
	 * @param command
	 */
	void postToGameThread(NetworkCommand command);
}
