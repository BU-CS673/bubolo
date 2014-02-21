package bubolo.net;

import java.net.InetSocketAddress;

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
	 * @return true if the network is active.
	 */
	boolean isActive();
	
	/**
	 * Shuts down the network.
	 */
	void shutdown();
	
	/**
	 * Identifies this player as a client, and attempts to connect to the 
	 * 
	 * @param serverIpAddress
	 * @throws IllegalStateException if <code>startServer</code> has been called before 
	 * <code>connect</code> is called. A player can only be a server or a client, not
	 * both.
	 * @throws NetworkException if a network error occurs.
	 */
	void connect(InetSocketAddress serverIpAddress) throws IllegalStateException, NetworkException;
	
	/**
	 * Identifies this player as the server, and begins accepting connections 
	 * from other players.
	 * 
	 * @throws IllegalStateException if <code>connect</code> has been called before 
	 * <code>startServer</code> is called. A player can only be a server or a client, not
	 * both.
	 * @throws NetworkException if a network error occurs.
	 */
	void startServer() throws IllegalStateException, NetworkException;
	
	/**
	 * Sends a network command to the other players.
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
