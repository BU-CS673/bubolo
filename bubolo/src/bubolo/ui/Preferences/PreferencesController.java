package bubolo.ui.Preferences;

public class PreferencesController
{
	
	PreferencesModel prefs;
	
	public PreferencesController(PreferencesModel prefs)
	{
		this.prefs = prefs;
	}
	
	public boolean saveToDisk()
	{
		//TODO: Eventually add some code here instead of just printing
		System.out.println("SFXVol:" + prefs.getSfxVol() + " MFXVol:" + prefs.getMfxVol() + " ScreenSize:" + prefs.getScreenSize() + " saved!");
		return true;
	}
	
}
