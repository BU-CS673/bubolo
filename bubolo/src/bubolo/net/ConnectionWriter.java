package bubolo.net;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Sends <code>NetworkCommand</code>s to a connected socket. This is a light-weight
 * class that is designed to be used with a thread queue. 
 * @author BU CS673 - Clone Productions
 */
class ConnectionWriter implements Runnable
{
	private ObjectOutputStream outputStream;
	private List<NetworkCommand> commands;

	/**
	 * Constructs a new ConnectionWriter.
	 * @param client a connection to a client. 
	 * @throws NetworkException if the output stream is unavailable.
	 */
	ConnectionWriter(Socket client)
	{
		try
		{
			this.outputStream = new ObjectOutputStream(client.getOutputStream());
		}
		catch (IOException e)
		{
			throw new NetworkException(e);
		}
		this.commands = new ArrayList<NetworkCommand>();
	}
	
	/**
	 * Adds the command to the list of commands that will be sent.
	 * @param command
	 */
	void addCommand(NetworkCommand command)
	{
		commands.add(command);
	}
	
	@Override
	public void run()
	{
		for (NetworkCommand command : commands)
		{
			try
			{
				outputStream.writeObject(command);
			}
			catch (IOException e)
			{
				// TODO: How should we handle this type of error?
				e.printStackTrace();
			}
		}
	}

}
