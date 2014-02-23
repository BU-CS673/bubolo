package bubolo.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Runnable that accepts client connections, queues <code>NetworkCommand</code>s, and 
 * sends the queued commands over the network.
 * @author BU CS673 - Clone Productions
 */
class Connections implements Runnable
{
	private ServerSocket serverSocket;
	private Network network;
	
	// The list of connections that are connected to this player.
	private List<ConnectionReader> connections;
	
	// Thread pool for sending NetworkCommands.
	private Executor executor = Executors.newCachedThreadPool();
	
	// Queue of commands that will be sent over the network.
	private Queue<NetworkCommand> networkCommands = new  ArrayDeque<NetworkCommand>();
	
	private AtomicBoolean isActive = new AtomicBoolean(true);
	
	/**
	 * Constructs a new Server object.
	 * @param networkSystem reference to the network system.
	 * @param server reference to an instantiated server socket.
	 */
	Connections(Network networkSystem, ServerSocket server)
	{
		// Use a CopyOnWriteArrayList since writes are rare, but reads are frequent.
		this.connections = new CopyOnWriteArrayList<ConnectionReader>();
		this.serverSocket = server;
		this.network = networkSystem;
	}
	
	/**
	 * Specifies whether the Connections object is active.
	 * @return true if the Connections object is active.
	 */
	boolean isActive()
	{
		return isActive.get();
	}
	
	/**
	 * Queues a network command that will be sent to the other players.
	 * @param command the network command to send.
	 */
	void addCommand(NetworkCommand command)
	{
		networkCommands.add(command);
	}
	
	/**
	 * Adds a connection to the list.
	 * @param socket a connected socket.
	 */
	void addConnection(Socket socket)
	{
		connections.add(new ConnectionReader(network, socket));
	}
	
	/**
	 * Closes all active connections.
	 */
	void destroy()
	{
		for (ConnectionReader reader : connections)
		{
			reader.destroy();
		}
		isActive.set(false);
	}
	
	/**
	 * Sends queued network commands across the network.
	 */
	void update()
	{
		if (!connections.isEmpty() && !networkCommands.isEmpty())
		{
			// Watch for performance problems with this.
			List<ConnectionWriter> writers = new ArrayList<ConnectionWriter>(connections.size());
			for (ConnectionReader client : connections)
			{
				if (client.getSocket().isConnected() && !client.getSocket().isClosed())
				{
					writers.add(new ConnectionWriter(client.getSocket()));
				}
			}
			
			NetworkCommand command = null;
			while ((command = networkCommands.poll()) != null)
			{
				for (ConnectionWriter writer : writers)
				{
					writer.addCommand(command);
				}
			}
			
			for (ConnectionWriter writer : writers)
			{
				executor.execute(writer);
			}
		}
	}
	
	/**
	 * Accepts connections from clients, and adds them to the client list.
	 */
	@Override
	public void run()
	{
		while (network.isActive())
		{
			try
			{
				connections.add(new ConnectionReader(network, serverSocket.accept()));
			}
			catch (IOException e)
			{
				// TODO: does this need to be handled?
			}
		}
	}
}
