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
	
	private Queue<ClientProcessor> clients;
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
		for (ClientProcessor c : clients)
		{
			if (c.isActive())
			{
				c.send(command);
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
				ClientProcessor client = new ClientProcessor(this.network, serverSocket.accept()); 
				clients.add(client);
				executor.execute(client);
			}
			catch (IOException e)
			{
				// TODO: does this need to be handled?
			}
		}
	}
	
	
	/**
	 * Processes client requests, and sends data to a client.
	 * @author BU CS673 - Clone Productions
	 */
	private static class ClientProcessor implements Runnable
	{
		private Network network;
		private Socket socket;
		
		private AtomicBoolean isActive;
		
		private Queue<NetworkCommand> commands = new ConcurrentLinkedQueue<NetworkCommand>();
		
		ClientProcessor(Network network, Socket socket) throws SocketException
		{
			this.network = network;
			this.socket = socket;
			socket.setTcpNoDelay(true);
			this.isActive = new AtomicBoolean();
		}
		
		/**
		 * Enqueues a command to be sent across the network.
		 * @param command the command that will be sent.
		 */
		void send(NetworkCommand command)
		{
			commands.add(command);
		}
		
		/**
		 * Returns true if the ClientProcessor is active, or false if not.
		 * @return true if the ClientProcessor is active.
		 */
		boolean isActive()
		{
			return isActive.get();
		}
		
		@Override
		public void run()
		{
			while (network.isActive())
			{
				long startMillis = System.currentTimeMillis();
				// TODO: accept data from the client, and send data to it.
				// 1. Check for data in the queue, and send it if there is.
				// 2. Check for data coming in, and process it if there is.

				long differenceMillis = System.currentTimeMillis() - startMillis;
				if (differenceMillis > 0)
				{
					try
					{
						Thread.sleep(NetworkInformation.MILLIS_PER_TICK - differenceMillis);
					}
					catch (InterruptedException e)
					{
						isActive.set(false);
					}
				}
			}
		
			try
			{
				socket.close();
			}
			catch (IOException e)
			{
				// TODO: does this need to be handled?
			}
			
			isActive.set(false);
		}
	}
}
