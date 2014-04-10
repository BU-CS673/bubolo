/**
 *
 */

package bubolo.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

import bubolo.net.NetworkObserver;

/**
 * @author BU CS673 - Clone Productions
 */
public class NetworkLobby implements NetworkObserver, UiScreen
{
	private Stage screen;

	public NetworkLobby()
	{
		screen = new Stage();
		Gdx.input.setInputProcessor(screen);
		
		
	}

	@Override
	public void draw()
	{
		screen.act(Gdx.graphics.getDeltaTime());
		screen.draw();
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

	/**
	 * Disposes the NetworkLobby. This must be called when the lobby screen is removed.
	 */
	public void dispose()
	{
		screen.dispose();
	}
}
