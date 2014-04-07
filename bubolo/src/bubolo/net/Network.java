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
	 * Identifies this player as the game server, and begins accepting connections from other
	 * players. There should only be one game server per game.
	 * 
	 * @param world
	 *            reference to the game world.
	 * @throws NetworkException
	 *             if a network error occurs.
	 * @throws IllegalStateException
	 *             if startServer or connect was already called.
	 */
	void startServer(World world) throws NetworkException, IllegalStateException;

	/**
	 * Identifies this player as the game server, and begins accepting connections from other
	 * players. There should only be one game server per game. The game will not start until the
	 * number of clients equals the clientCount parameter.
	 * 
	 * @param world
	 *            reference to the game world.
	 * @param clientCount
	 *            the number of clients to wait for.
	 * 
	 * @throws NetworkException
	 *             if a network error occurs.
	 * @throws IllegalStateException
	 *             if startServer or connect was already called.
	 */
	void startServer(World world, int clientCount) throws NetworkException, IllegalStateException;

	/**
	 * Attempts to connect to the specified IP address.
	 * 
	 * @param serverIpAddress
	 *            the IP address of a server.
	 * @throws NetworkException
	 *             if a network error occurs.
	 * @throws IllegalStateException
	 *             if startServer or connect was already called.
	 */
	void connect(InetAddress serverIpAddress) throws NetworkException, IllegalStateException;

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
	 * Shuts down the network system.
	 */
	void dispose();
}
