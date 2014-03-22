package bubolo.ui.Preferences;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

/**
 * Controller class for Preferences, handles disk activity for Models
 * 
 * @author BU CS673 - Clone Productions
 */

public class PreferencesController
{
	
	private PreferencesModel prefs;
	
	/**
	 * Constructor will create a new PreferencesController
	 * @param prefs the PreferencesModel to use
	 */
	public PreferencesController(PreferencesModel prefs)
	{
		this.prefs = prefs;
	}
	
	/**
	 * Does nothing at the moment, will eventually save to disk
	 * @return true if the operation is completed successfully
	 */
	public boolean saveToDisk()
	{
		//TODO: Eventually add some code here instead of just printing to console
		System.out.println("SFXVol:" + prefs.getSfxVol() + " MFXVol:" + prefs.getMfxVol() + " ScreenSize:" + prefs.getScreenSize() + " saved!");
		return true;
	}
	
	/**
	 * Does nothing at the moment, will eventually load from disk
	 * @return PreferenceModel loaded from disk
	 */
	public PreferencesModel loadFromDisk()
	{
		//TODO: Load stuff, and things
		return prefs;
	}
	

/**
 * Handles changes 
 * @param ce
 */
	public void stateChanged(ChangeEvent ce)
	{
		int newVol = ((JSlider)ce.getSource()).getValue();
		System.out.print(newVol + "; "); 
	}
	
}
