/**
 *
 */

package bubolo.ui;

import bubolo.net.Network;
import bubolo.net.NetworkSystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The network game lobby, which allows users to message each other before starting the game..
 * 
 * @author BU CS673 - Clone Productions
 */
public class Lobby extends Screen
{
	private TextButton sendMessageButton;
	private TextField sendMessageField;
	private TextButton startGameButton;

	/**
	 * Constructs the network game lobby.
	 */
	public Lobby()
	{
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);

		createMessageHistoryBox(table, skin);
		createSendMessageRow(skin);
	}

	private static void createMessageHistoryBox(Table table, Skin skin)
	{
		table.row().height(500.f);

		BitmapFont font = new BitmapFont(Gdx.files.internal("arial_26_white.fnt"));
		LabelStyle messageBoxStyle = new LabelStyle();
		messageBoxStyle.font = font;

		Label title = new Label("", messageBoxStyle);
		title.setWrap(true);
		table.add(title).colspan(3).expandX();
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
		net.is
		
		startGameButton = new TextButton("Start", skin);
		table.add(startGameButton).expandX().width(100.f);
		
		startGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				System.out.println("Start Game Clicked");
			}
		});
	}
}
