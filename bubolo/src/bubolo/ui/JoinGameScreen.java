/**
 *
 */

package bubolo.ui;

import bubolo.GameApplication;
import bubolo.GameApplication.State;
import bubolo.net.Network;
import bubolo.net.NetworkObserver;
import bubolo.net.NetworkSystem;
import bubolo.net.command.SendMessage;
import bubolo.world.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The join game screen, which allows the user to enter a name and ip address.
 * 
 * @author BU CS673 - Clone Productions
 */
public class JoinGameScreen extends Screen implements NetworkObserver
{
	private TextField playerNameField;
	private TextField ipAddressField;
	private TextButton okButton;

	private final GameApplication app;

	/**
	 * Constructs the network game lobby.
	 * 
	 * @param app
	 *            reference to the Game Application.
	 * @param isClient
	 *            true if this is a client player.
	 */
	public JoinGameScreen(GameApplication app, boolean isClient)
	{
		this.app = app;

		TextureAtlas atlas = new TextureAtlas(
				new FileHandle(UserInterface.UI_PATH + "uiskin.atlas"));
		Skin skin = new Skin(new FileHandle(UserInterface.UI_PATH + "uiskin.json"), atlas);

		createPlayerNameRow(skin);
		
		if (isClient)
		{
			createIpAddressRow(skin);
		}

		Network net = NetworkSystem.getInstance();
		net.addObserver(this);
	}

	private void createPlayerNameRow(Skin skin)
	{
		table.row().colspan(4);
		table.row();
		
		table.add(new Label("Name:", skin));
		
		playerNameField = new TextField("", skin);
		table.add(playerNameField);
	}

	private void createIpAddressRow(Skin skin)
	{
		table.row();

		Button sendMessageButton = new TextButton("Send", skin);

		sendMessageButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				sendMessage();
			}
		});

		table.add(sendMessageButton).expandX().width(100.f);

		sendMessageField = new TextField("", skin);
		final float width = net.isServer() ? Gdx.graphics.getWidth() - 250.f :
				Gdx.graphics.getWidth() - 150.f;
		table.add(sendMessageField).expandX().width(width);

		stage.addListener(new InputListener() {
			@Override
			public boolean keyUp(InputEvent event, int keycode)
			{
				if (keycode == Input.Keys.ENTER)
				{
					sendMessage();
				}
				return false;
			}
		});

	}
	
	private void addOkButtonRow(Skin skin)
	{
		
	}

	@Override
	protected void onUpdate()
	{

	}

	
	@Override
	public void dispose()
	{
	}
}
