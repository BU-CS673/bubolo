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

/**
 * Runnable that accepts client connections, queues <code>NetworkCommand</code>s, and 
 * sends the queued commands over the network.
 * @author BU CS673 - Clone Productions
 */
class Server implements Runnable
{
	private ServerSocket serverSocket;
	private Network network;
	
	// The list of clients that are connected to this server.
	private List<Socket> clients;
	
	// Thread pool for sending NetworkCommands.
	private Executor executor = Executors.newCachedThreadPool();
	
	// Queue of commands that will be sent over the network.
	private Queue<NetworkCommand> networkCommands = new  ArrayDeque<NetworkCommand>();
	
	/**
	 * Constructs a new Server object.
	 * @param networkSystem reference to the network system.
	 * @param server reference to an instantiated server socket.
	 */
	Server(Network networkSystem, ServerSocket server)
	{
		// Use a CopyOnWriteArrayList since writes are rare, but reads are frequent.
		this.clients = new CopyOnWriteArrayList<Socket>();
		this.serverSocket = server;
		this.network = networkSystem;
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
	 * Sends queued network commands across the network.
	 */
	void update()
	{
		if (!clients.isEmpty() && !networkCommands.isEmpty())
		{
			// Watch for performance problems with this.
			List<ConnectionWriter> writers = new ArrayList<ConnectionWriter>(clients.size());
			for (Socket client : clients)
			{
				if (client.isConnected() && !client.isClosed())
				{
					writers.add(new ConnectionWriter(client));
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
				clients.add(serverSocket.accept());
			}
			catch (IOException e)
			{
				// TODO: does this need to be handled?
			}
		}
	}
}
