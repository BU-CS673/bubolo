package bubolo.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Runnable that accepts client connection.
 * @author BU CS673 - Clone Productions
 */
class Server implements Runnable
{
	private ServerSocket serverSocket;
	private Network network;
	
	private Queue<ClientConnection> clients;
	private Executor executor = Executors.newCachedThreadPool();
	
	/**
	 * Constructs a new Server object.
	 * @param networkSystem reference to the network system.
	 * @param server reference to an instantiated server socket.
	 */
	Server(Network networkSystem, ServerSocket server)
	{
		this.serverSocket = server;
		this.network = networkSystem;
	}
	
	/**
	 * Sends a network command to the other players.
	 * @param command the network command to send.
	 */
	public void send(NetworkCommand command)
	{
		for (ConnectionReader cr : clients)
		{
			if (cr.isActive())
			{
				cr.send(command);
			}
			// TODO: remove inactive ClientProcessors?
		}
	}
	
	@Override
	public void run()
	{
		while (network.isActive())
		{
			try
			{
				ClientConnection client = new ClientConnection(this.network, serverSocket.accept()); 
				clients.add(client);
				executor.execute(client);
			}
			catch (IOException e)
			{
				// TODO: does this need to be handled?
			}
		}
	}
}
