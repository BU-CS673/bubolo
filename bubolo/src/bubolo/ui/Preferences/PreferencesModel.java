package bubolo.ui.Preferences;

import com.google.common.base.Preconditions;

/**
 * Model (data) holding class for the Preferences package
 * 
 * @author BU CS673 - Clone Productions
 */
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
	
	/**
	 * Constructor will create a new PreferencesModel object with default values
	 * 
	 * @author BU CS673 - Clone Productions
	 */
	public PreferencesModel()
	{
		setSfxVol(SFXVOL_DEFAULT);
		setMfxVol(MFXVOL_DEFAULT);
		setScreenSize(SCREENSIZE_DEFAULT);
	}
	
	/**
	 * Sets the sound effect volume, from 0 (mute) to 100 (max volume).
	 * @param sfxVol the new sound effect volume, ranging from 0 to 100.
	 * @throws IllegalArgumentException if sfxVol is less than 0 or greater than 100.
	 */
	public void setSfxVol(int sfxVol)
	{
		Preconditions.checkArgument(sfxVol >= 0, "Sound effect volume was less than zero: %s", sfxVol);
		Preconditions.checkArgument(sfxVol <= 100, "Sound effect volume was greater than 100: %s", sfxVol);
		this.sfxVol = sfxVol;
	}
	
	/**
	 * Gets the sound effect volume, in the range [0, 100].
	 * @return the sound effect volume.
	 */
	public int getSfxVol()
	{
		return sfxVol;
	}

	/**
	 * Sets the music volume, from 0 (mute) to 100 (max volume).
	 * @param mfxVol the new music volume, ranging from 0 to 100.
	 * @throws IllegalArgumentException if mfxVol is less than 0 or greater than 100.
	 */
	public void setMfxVol(int mfxVol)
	{
		Preconditions.checkArgument(mfxVol >= 0, "Sound effect volume was less than zero: %s", mfxVol);
		Preconditions.checkArgument(mfxVol <= 100, "Sound effect volume was greater than 100: %s", mfxVol);
		this.mfxVol = mfxVol;
	}
	
	/**
	 * Gets the music volume, in the range [0, 100].
	 * @return the music volume.
	 */
	public int getMfxVol()
	{
		return mfxVol;
	}

	/**
	 * Sets the screen size, from 1 (small) to 3 (large).
	 * @param screenSize the new screen size, ranging from 1 to 3.
	 * @throws IllegalArgumentException if screenSize is less than 1 or greater than 3.
	 */
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
	
	/**
	 * Gets the screen size, in the range [1, 3].
	 * @return the screen size.
	 */
	public int getScreenSize()
	{
		return screenSize;
	}
}
