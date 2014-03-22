/**
 *
 */

package bubolo.ui;

import javax.swing.ImageIcon;


/**
 * Top level class for User Interface
 * @author BU CS673 - Clone Productions
 */
public class UserInterface
{

	/**
	 * File path were UI icons are stored
	 */
	public static final String ICONS_PATH = "res/icons/";
	
	/**
	 * Sound Effects Volume Default
	 */
	public static final int SFXVOL_DEFAULT = 35;
	
	/**
	 * Music Volume Default
	 */
	public static final int MFXVOL_DEFAULT = 35;
	
	/**
	 * Screen Size Default
	 */
	public static final int SCREENSIZE_DEFAULT = 2;
	
	/**
	 * The game icon
	 */
	public static ImageIcon gameIcon = new ImageIcon(ICONS_PATH + "tank_icon.png");
}
