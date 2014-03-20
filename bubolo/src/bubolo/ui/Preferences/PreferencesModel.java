package bubolo.ui.Preferences;

public class PreferencesModel
{

	// Defaults
	private int SFXVOL_DEFAULT = 35;
	private int MFXVOL_DEFAULT = 35;
	private int SCREENSIZE_DEFAULT = 2;
	
	// Private variables that make up the valuable data
	private int sfxVol;
	private int mfxVol;
	private int screenSize;
	
	public PreferencesModel()
	{
		setSfxVol(SFXVOL_DEFAULT);
		setMfxVol(MFXVOL_DEFAULT);
		setScreenSize(SCREENSIZE_DEFAULT);
	}
	
	public boolean setSfxVol(int sfxVol)
	{
		if ((sfxVol >= 0) && (sfxVol <= 100))
		{
			this.sfxVol = sfxVol;
			return true;
		}
		else
			return false;
	}
	
	public int getSfxVol()
	{
		return sfxVol;
	}

	public boolean setMfxVol(int mfxVol)
	{
		if ((mfxVol >= 0) && (mfxVol <= 100))
		{
			this.mfxVol = mfxVol;
			return true;
		}
		else
			return false;
	}
	
	public int getMfxVol()
	{
		return mfxVol;
	}

	public boolean setScreenSize(int screenSize)
	{
		if ((screenSize >= 0) && (screenSize <= 100))
		{
			this.screenSize = screenSize;
			return true;
		}
		else
			return false;
	}
	
	public int getScreenSize()
	{
		return screenSize;
	}

	
}
