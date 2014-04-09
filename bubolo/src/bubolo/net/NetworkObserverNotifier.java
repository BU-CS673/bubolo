/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BU CS673 - Clone Productions
 */
public class NetworkObserverNotifier
{
	// The list of network observers.
	private List<NetworkObserver> observers;

	/**
	 * Constructs a NetworkObserverNotifier.
	 */
	NetworkObserverNotifier()
	{
		this.observers = new ArrayList<NetworkObserver>();
	}

	/**
	 * Adds an observer to the network observer list.
	 * 
	 * @param o
	 *            the observer to add.
	 */
	void addObserver(NetworkObserver o)
	{
		if (!observers.contains(o))
		{
			observers.add(o);
		}
	}

	/**
	 * Removes an observer from the network observer list.
	 * 
	 * @param o
	 *            the observer to remove.
	 */
	void removeObserver(NetworkObserver o)
	{
		observers.remove(o);
	}

	/**
	 * Notify observers that this client has connected to a server.
	 * 
	 * @param clientName
	 *            the name of the client that connected.
	 * @param serverName
	 *            the name of the server.
	 */
	public void notifyConnect(String clientName, String serverName)
	{
		for (NetworkObserver o : observers)
		{
			o.onConnect(clientName, serverName);
		}
	}

	/**
	 * Notify observers that a client has connected to this server.
	 * 
	 * @param clientName
	 *            the name of the client that connected.
	 */
	public void notifyClientConnected(String clientName)
	{
		for (NetworkObserver o : observers)
		{
			o.onClientConnected(clientName);
		}
	}

	/**
	 * Notify observers that a client has disconnected.
	 * 
	 * @param clientName
	 *            the name of the client that disconnected.
	 */
	public void notifyClientDisconnected(String clientName)
	{
		for (NetworkObserver o : observers)
		{
			o.onClientDisconnected(clientName);
		}
	}

	/**
	 * Notify observers that the game is starting.
	 * 
	 * @param timeUntilStart
	 *            countdown until the game begins.
	 */
	public void notifyGameStart(int timeUntilStart)
	{
		for (NetworkObserver o : observers)
		{
			o.onGameStart(timeUntilStart);
		}
	}
}
