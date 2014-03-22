package bubolo.ui.Preferences;

import java.io.Serializable;

import bubolo.ui.UserInterface;

import com.google.common.base.Preconditions;

/**
 * Model (data) holding class for the Preferences package
 * 
 * @author BU CS673 - Clone Productions
 */
public class PreferencesModel implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3303278947278907653L;
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
		setSfxVol(UserInterface.SFXVOL_DEFAULT);
		setMfxVol(UserInterface.MFXVOL_DEFAULT);
		setScreenSize(UserInterface.SCREENSIZE_DEFAULT);
	}
	
	/**
	 * This get will return the current Preferences Model
	 * @return returns this object
	 */
	public PreferencesModel get()
	{
		return this;
	}
	
	/**
	 * Will set all values within this PreferencesModel object to be equal to the PreferencesModel passed to it
	 * @param newPrefModel the PreferencesModel you want to make this PreferencesModel equal to
	 */
	public void set(PreferencesModel newPrefModel)
	{
		this.setSfxVol(newPrefModel.getSfxVol());
		this.setMfxVol(newPrefModel.getMfxVol());
		this.setScreenSize(newPrefModel.getScreenSize());
	}
	
	/**
	 * Sets the sound effect volume, from 0 (mute) to 100 (max volume).
	 * @param sfxVol the new sound effect volume, ranging from 0 to 100.
	 * @throws IllegalArgumentException if sfxVol is less than 0 or greater than 100.
	 */
	public void setSfxVol(int sfxVol)
	{
		Preconditions.checkArgument(sfxVol >= 0, "Sound effect volume was less than 0: %s", sfxVol);
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
		Preconditions.checkArgument(mfxVol >= 0, "Music volume was less than 0: %s", mfxVol);
		Preconditions.checkArgument(mfxVol <= 100, "Music volume was greater than 100: %s", mfxVol);
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
	public void setScreenSize(int screenSize)
	{
		Preconditions.checkArgument(screenSize >= 1, "Screen was less than 1: %s", screenSize);
		Preconditions.checkArgument(screenSize <= 3, "Music volume was greater than 3: %s", screenSize);
		this.screenSize = screenSize;
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
