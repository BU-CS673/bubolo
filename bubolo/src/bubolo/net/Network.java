/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

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
	 * Returns true if this the game server.
	 * @return true if this the game server, or false otherwise.
	 */
	boolean isServer();
	
	/**
	 * Returns the name of the player.
	 * @return the name of the player.
	 */
	String getPlayerName();
	
	/**
	 * Identifies this player as the game server, and begins accepting connections from other
	 * players. There should only be one game server per game.
	 * 
	 * @param serverName
	 *            the name of this server.
	 * @throws NetworkException
	 *             if a network error occurs.
	 * @throws IllegalStateException
	 *             if startServer or connect was already called.
	 */
	void startServer(String serverName) throws NetworkException, IllegalStateException;

	/**
	 * Attempts to connect to the specified IP address.
	 * 
	 * @param serverIpAddress
	 *            the IP address of a server.
	 * @param clientName
	 *            the name of this client.
	 * @throws NetworkException
	 *             if a network error occurs.
	 * @throws IllegalStateException
	 *             if startServer or connect was already called.
	 */
	void connect(InetAddress serverIpAddress, String clientName)
			throws NetworkException, IllegalStateException;

	/**
	 * Starts the network system in debug mode. Use this to run unit tests and integration tests
	 * that don't rely on the network.
	 */
	void startDebug();

	/**
	 * Queues a network command to be sent to the other players.
	 * 
	 * @param command
	 *            the network command to send.
	 */
	void send(NetworkCommand command);

	/**
	 * Performs all network system updates. This should be called once per game tick.
	 * 
	 * @param world
	 *            reference to the game world.
	 */
	void update(World world);

	/**
	 * Runs a NetworkCommand in the game logic thread.
	 * 
	 * @param command
	 */
	void postToGameThread(NetworkCommand command);

	/**
	 * Notifies the clients that the game should start. This method is not needed by clients.
	 * 
	 * @param world
	 *            reference to the game world.
	 */
	void startGame(World world);

	/**
	 * Adds an observer to the network observer list.
	 * 
	 * @param observer
	 *            the observer to add.
	 */
	void addObserver(NetworkObserver observer);

	/**
	 * Removes an observer from the network observer list.
	 * 
	 * @param observer
	 *            the observer to remove.
	 */
	void removeObserver(NetworkObserver observer);

	/**
	 * Returns a reference to the observer notifier.
	 * 
	 * @return reference to the observer notifier.
	 */
	NetworkObserverNotifier getNotifier();

	/**
	 * Shuts down the network system.
	 */
	void dispose();
}
