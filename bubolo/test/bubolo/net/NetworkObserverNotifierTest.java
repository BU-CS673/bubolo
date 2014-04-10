/**
 *
 */

package bubolo.net;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * @author BU CS673 - Clone Productions
 */
public class NetworkObserverNotifierTest
{
	private NetworkObserverNotifier notifier;
	private NetObserver o;
	
	@Before
	public void setup()
	{
		this.notifier = new NetworkObserverNotifier();
		this.o = new NetObserver();
	}
	
	/**
	 * Test method for {@link bubolo.net.NetworkObserverNotifier#addObserver(bubolo.net.NetworkObserver)}.
	 */
	@Test
	public void testAddObserver()
	{
		notifier.addObserver(mock(NetworkObserver.class));
		assertEquals(1, notifier.getObserverCount());
	}

	/**
	 * Test method for {@link bubolo.net.NetworkObserverNotifier#removeObserver(bubolo.net.NetworkObserver)}.
	 */
	@Test
	public void testRemoveObserver()
	{
		notifier.addObserver(o);
		notifier.removeObserver(o);
		assertEquals(0, notifier.getObserverCount());
	}

	/**
	 * Test method for {@link bubolo.net.NetworkObserverNotifier#notifyConnect(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testNotifyConnect()
	{
		notifier.addObserver(o);
		
		final String CLIENT = "CLIENT";
		final String SERVER = "SERVER";
		notifier.notifyConnect(CLIENT, SERVER);
		
		assertEquals(CLIENT, o.getClientName());
		assertEquals(SERVER, o.getServerName());
	}

	/**
	 * Test method for {@link bubolo.net.NetworkObserverNotifier#notifyClientConnected(java.lang.String)}.
	 */
	@Test
	public void testNotifyClientConnected()
	{
		notifier.addObserver(o);
		
		final String CLIENT = "CLIENT";
		notifier.notifyClientConnected(CLIENT);
		
		assertEquals(CLIENT, o.getClientName());
	}

	/**
	 * Test method for {@link bubolo.net.NetworkObserverNotifier#notifyClientDisconnected(java.lang.String)}.
	 */
	@Test
	public void testNotifyClientDisconnected()
	{
		notifier.addObserver(o);
		
		final String CLIENT = "CLIENT";
		notifier.notifyClientDisconnected(CLIENT);
		
		assertEquals(CLIENT, o.getClientName());
	}

	/**
	 * Test method for {@link bubolo.net.NetworkObserverNotifier#notifyGameStart(int)}.
	 */
	@Test
	public void testNotifyGameStart()
	{
		notifier.addObserver(o);
		
		final int TIME = 8;
		notifier.notifyGameStart(TIME);
		
		assertEquals(TIME, o.getTimeUntilStart());
	}

	/**
	 * Test method for {@link bubolo.net.NetworkObserverNotifier#notifyMessageReceived(java.lang.String)}.
	 */
	@Test
	public void testNotifyMessageReceived()
	{
		notifier.addObserver(o);
		
		final String MESSAGE = "MESSAGE";
		notifier.notifyMessageReceived(MESSAGE);
		
		assertEquals(MESSAGE, o.getMessage());
	}

	private static class NetObserver implements NetworkObserver
	{
		private String clientName;
		private String serverName;
		private String message;
		private int timeUntilStart;
		
		private String getClientName()
		{
			return clientName;
		}
		
		private String getServerName()
		{
			return serverName;
		}
		
		private String getMessage()
		{
			return message;
		}
		
		private int getTimeUntilStart()
		{
			return timeUntilStart;
		}
		
		
		@Override
		public void onConnect(String clientName, String serverName)
		{
			this.clientName = clientName;
			this.serverName = serverName;
		}

		@Override
		public void onClientConnected(String clientName)
		{
			this.clientName = clientName;
		}

		@Override
		public void onClientDisconnected(String clientName)
		{
			this.clientName = clientName;
		}

		@Override
		public void onGameStart(int timeUntilStart)
		{
			this.timeUntilStart = timeUntilStart;
		}

		@Override
		public void onMessageReceived(String message)
		{
			this.message = message;	
		}
	}
}
