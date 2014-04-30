/**
 *
 */

package bubolo.ui;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import bubolo.GameApplication;
import bubolo.GameApplication.State;
import bubolo.net.Network;
import bubolo.net.NetworkException;
import bubolo.net.NetworkSystem;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
public class PlayerInfoScreen extends Screen
{
	private TextField playerNameField;
	private TextField ipAddressField;
	private Label statusLabel1;
	private Label statusLabel2;

	private final GameApplication app;

	private final boolean isClient;

	// These variables enable the screen to be updated with a message before the connection attempt
	// is made. This is useful because the connection attempt may take a few seconds, and the screen
	// will appear frozen during that time otherwise.
	private boolean connectToServer;
	private int ticksUntilConnect;

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

		table.row().colspan(8).padTop(50.f).align(Align.center);
		statusLabel1 = new Label("", skin);
		table.add(statusLabel1);
		
		table.row().colspan(8).padTop(50.f).align(Align.left).padLeft(100.f);
		statusLabel2 = new Label("", skin);
		table.add(statusLabel2);
	}

	private void createPlayerNameRow(Skin skin)
	{
		table.row()
				.align(Align.left)
				.padTop(100.f);

		table.add(new Label("Name:", skin)).padLeft(300.f);

		playerNameField = new TextField("", skin);
		table.add(playerNameField).width(250.f);
	}

	private void createIpAddressRow(Skin skin)
	{
		table.row()
				.align(Align.left)
				.padTop(5.f);

		table.add(new Label("Server IP Address:", skin)).padLeft(300.f);

		ipAddressField = new TextField("", skin);
		table.add(ipAddressField).width(160.f);
	}

	private void addOkButtonRow(Skin skin)
	{
		table.row()
				.colspan(8)
				.padTop(25.f);

		TextButton okButton = new TextButton("OK", skin);

		okButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				if (!playerNameField.getText().isEmpty() &&
						!(isClient && ipAddressField.getText().isEmpty()))
				{
					if (isClient)
					{
						statusLabel1.setText("Connecting...");
						statusLabel2.setText("");
						connectToServer = true;
						ticksUntilConnect = 1;
					}
					else
					{
						final Network network = NetworkSystem.getInstance();
						network.startServer(playerNameField.getText());
						app.setState(State.GAME_LOBBY);
					}
				}
			}
		});

		table.add(okButton).expandX().width(100.f);
	}

	@Override
	public void onUpdate()
	{
		if (connectToServer)
		{
			if (ticksUntilConnect == 0)
			{
				connectToServer = false;

				InetAddress ipAddress;
				try
				{
					ipAddress = Inet4Address.getByName(ipAddressField.getText());

					final Network network = NetworkSystem.getInstance();
					network.connect(ipAddress, playerNameField.getText());
				}
				catch (UnknownHostException | NetworkException e)
				{
					statusLabel1.setText("");
					statusLabel2.setText("Unable to connect: " + e.getMessage());
					return;
				}

				app.setState(State.GAME_LOBBY);
			}
			else
			{
				--ticksUntilConnect;
			}
		}
	}

	@Override
	public void dispose()
	{
	}
}
