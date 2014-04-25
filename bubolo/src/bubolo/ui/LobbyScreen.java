/**
 *
 */

package bubolo.ui;

import bubolo.GameApplication;
import bubolo.GameApplication.State;
import bubolo.net.Network;
import bubolo.net.NetworkObserver;
import bubolo.net.NetworkSystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The network game lobby, which allows users to message each other before starting the game.
 * 
 * @author BU CS673 - Clone Productions
 */
public class LobbyScreen extends Screen implements NetworkObserver
{
	private Label messageHistory;
	private TextButton sendMessageButton;
	private TextField sendMessageField;
	private TextButton startGameButton;
	
	private final GameApplication app;

	/**
	 * Constructs the network game lobby.
	 * @param app reference to the Game Application.
	 */
	public LobbyScreen(GameApplication app)
	{
		this.app = app;
		
		TextureAtlas atlas = new TextureAtlas(
				new FileHandle(UserInterface.UI_PATH + "uiskin.atlas"));
		Skin skin = new Skin(new FileHandle(UserInterface.UI_PATH + "uiskin.json"), atlas);

		createMessageHistoryBox(skin);
		createSendMessageRow(skin);
	}

	private void createMessageHistoryBox(Skin skin)
	{
		table.row().height(500.f);
		
		BitmapFont font = new BitmapFont(
				new FileHandle(UserInterface.UI_PATH + "arial_26_white.fnt"));
		LabelStyle messageBoxStyle = new LabelStyle();
		messageBoxStyle.font = font;

		messageHistory = new Label("", messageBoxStyle);
		messageHistory.setWrap(true);
		
		ScrollPane scrollpane = new ScrollPane(messageHistory, skin);
		table.add(scrollpane).colspan(3).expand();
	}

	private void createSendMessageRow(Skin skin)
	{
		table.row();

		sendMessageButton = new TextButton("Send", skin);
		table.add(sendMessageButton).expandX().height(35.f).width(100.f);

		sendMessageButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				if (!sendMessageField.getText().isEmpty())
				{
					System.out.println(sendMessageField.getText());
					sendMessageField.setText(null);
				}
			}
		});

		sendMessageField = new TextField("", skin);
		table.add(sendMessageField).expandX().width(Gdx.graphics.getWidth() - 250.f);

		Network net = NetworkSystem.getInstance();
		if (net.isServer())
		{
			startGameButton = new TextButton("Start", skin);
			table.add(startGameButton).expandX().width(100.f);
			
			startGameButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					System.out.println("Start Game Clicked");
//					app.setState(State.GAME);
				}
			});
		}
	}

	@Override
	public void onConnect(String clientName, String serverName)
	{
	}

	@Override
	public void onClientConnected(String clientName)
	{
		appendToMessageHistory(messageHistory, clientName + " joined the game.");
	}

	@Override
	public void onClientDisconnected(String clientName)
	{
		appendToMessageHistory(messageHistory, clientName + " left the game.");
	}

	@Override
	public void onGameStart(int secondsUntilStart)
	{
		
	}

	@Override
	public void onMessageReceived(String message)
	{
		appendToMessageHistory(messageHistory, message);
	}
	
	private static void appendToMessageHistory(Label messageHistory, String message)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(messageHistory.getText());
		sb.append("\n");
		sb.append(message);
		
		messageHistory.setText(sb.toString());
	}
}
