/**
 *
 */

package bubolo.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * The network game lobby, which allows users to message each other before starting the game..
 * 
 * @author BU CS673 - Clone Productions
 */
public class Lobby extends Screen
{
	/**
	 * Constructs the network game lobby.
	 */
	public Lobby()
	{
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
	    Skin skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
	    
	    
	}
    
	private static void createMessageBox(Table table, Skin skin)
	{
		table.row().height(500.f);
		
		BitmapFont font = new BitmapFont(Gdx.files.internal("arial_26_white.fnt"));
		LabelStyle messageBoxStyle = new LabelStyle();
		messageBoxStyle.font = font;
		
		Label title = new Label("Knowla Setup", messageBoxStyle);
		title.setWrap(true);
		table.add(title).colspan(3).expandX();
	}
}
