/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net.command;

import bubolo.net.Network;
import bubolo.net.NetworkCommand;
import bubolo.net.NetworkSystem;
import bubolo.world.World;

/**
 * Notifies players that a new client has connected.
 * 
 * @author BU CS673 - Clone Productions
 */
public class ClientConnected implements NetworkCommand
{
	private static final long serialVersionUID = 1L;

	private final String playerName;

	/**
	 * Constructs a ClientConnected object.
	 * 
	 * @param playerName
	 *            the name of the player that connected.
	 */
	public ClientConnected(String playerName)
	{
		this.playerName = playerName;
	}

	/**
	 * Returns the name of the client.
	 * 
	 * @return the name of the client.
	 */
	public String getClientName()
	{
		return playerName;
	}

	@Override
	public void execute(World world)
	{
		Network net = NetworkSystem.getInstance();
		net.getNotifier().notifyClientConnected(playerName);
	}
}
