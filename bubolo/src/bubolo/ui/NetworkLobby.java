/**
 *
 */

package bubolo.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;

import bubolo.net.NetworkObserver;

/**
 * @author BU CS673 - Clone Productions
 */
public class NetworkLobby implements NetworkObserver, GameScreen
{
	private Stage stage;
	
	public NetworkLobby()
	{
		
	}
	
	
	@Override
	public void draw()
	{
		
	}
	
	@Override
	public void onConnect(String clientName, String serverName)
	{
		
	}

	@Override
	public void onClientConnected(String clientName)
	{
		
	}

	@Override
	public void onClientDisconnected(String clientName)
	{
		
	}

	@Override
	public void onGameStart(int timeUntilStart)
	{
		
	}
	
	@Override
	public void onMessageReceived(String message)
	{
		
	}
	
	public void dispose()
	{
		
	}
}
