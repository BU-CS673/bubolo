/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Sends NetworkCommands across the network.
 * 
 * @author BU CS673 - Clone Productions
 */
class NetworkSender implements Runnable
{
	private final Socket client;
	private final NetworkCommand command;

	/**
	 * Constructs a NetworkSender.
	 * 
	 * @param client
	 *            reference to the client socket connection.
	 * @param command
	 *            the command to send.
	 */
	NetworkSender(Socket client, NetworkCommand command)
	{
		this.client = client;
		this.command = command;
	}

	@Override
	public void run()
	{
		try
		{
			ObjectOutputStream stream = new ObjectOutputStream(client.getOutputStream());
			stream.writeObject(command);
		}
		catch (IOException e)
		{
			// TODO: log this exception properly.
			e.printStackTrace();
			throw new NetworkException(e);
		}
	}
}
