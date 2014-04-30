/**
 *
 */

package bubolo.ui;

import bubolo.GameApplication;
import bubolo.GameApplication.State;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The join game screen, which allows the user to enter a name and ip address.
 * 
 * @author BU CS673 - Clone Productions
 */
public class PlayerInfoScreen extends Screen
{
	private TextField playerNameField;
	private TextField ipAddressField;

	private final GameApplication app;
	
	private final boolean isClient;

	/**
	 * Constructs the network game lobby.
	 * 
	 * @param app
	 *            reference to the Game Application.
	 * @param isClient
	 *            true if this is a client player.
	 */
	public PlayerInfoScreen(GameApplication app, boolean isClient)
	{
		this.app = app;
		this.isClient = isClient;

		TextureAtlas atlas = new TextureAtlas(
				new FileHandle(UserInterface.UI_PATH + "uiskin.atlas"));
		Skin skin = new Skin(new FileHandle(UserInterface.UI_PATH + "uiskin.json"), atlas);

		createPlayerNameRow(skin);
		
		if (isClient)
		{
			createIpAddressRow(skin);
		}

		addOkButtonRow(skin);
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
		table.row().colspan(4);
		table.row();
		
		table.add(new Label("Server IP Address:", skin));
		
		ipAddressField = new TextField("", skin);
		table.add(ipAddressField);
	}
	
	private void addOkButtonRow(Skin skin)
	{
		table.row();

		TextButton okButton = new TextButton("Send", skin);

		okButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				if (!playerNameField.getText().isEmpty() &&
						!(isClient && ipAddressField.getText().isEmpty()))
				{
					app.setState(State.GAME_LOBBY);
				}
			}
		});

		table.add(okButton).expandX().width(100.f);
	}
	
	@Override
	public void dispose()
	{
	}
}
