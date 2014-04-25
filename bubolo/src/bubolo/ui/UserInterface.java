/**
 *
 */

package bubolo.ui;

import javax.swing.ImageIcon;

import bubolo.audio.Audio;


/**
 * Top level class for User Interface
 * @author BU CS673 - Clone Productions
 */
public class UserInterface
{

	/**
	 * 
	 */
	public static final String FILENAME = "Prefs.bolo";
	
	/**
	 * Path where UI icons are stored.
	 */
	public static final String ICONS_PATH = "res/icons/";
	
	/**
	 * Path where the user interface files are located.
	 */
	public static final String UI_PATH = "res/ui/";
	
	/**
	 * Sound Effects Volume Default
	 */
	public static final int SFXVOL_DEFAULT = Audio.getSoundEffectVolume();
	
	/**
	 * Music Volume Default
	 */
	public static final int MFXVOL_DEFAULT = Audio.getMusicVolume();
	
	/**
	 * Screen Size Default
	 */
	public static final int SCREENSIZE_DEFAULT = 2;
	
	/**
	 * The game icon
	 */
	public static ImageIcon gameIcon = new ImageIcon(ICONS_PATH + "tank_icon.png");
}
