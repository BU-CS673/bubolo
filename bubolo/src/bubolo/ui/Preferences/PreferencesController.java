package bubolo.ui.Preferences;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Controller class for Preferences, handles disk activity for Models
 * 
 * @author BU CS673 - Clone Productions
 */

public class PreferencesController
{
	/**
	 * PreferencesModel used by the PreferencesController
	 */
	private PreferencesModel pm;
	
	/**
	 * PreferencesView used by the PreferenceController
	 */
	private PreferencesView pv;
	
	
	/**
	 * Constructor will create a new PreferencesController
	 * @param pm the PreferencesModel to use
	 * @param pv the PreferencesView to use
	 */
	public PreferencesController(PreferencesModel pm, PreferencesView pv)
	{
		// Setup the local variables
		this.pm = pm;
		this.pv = pv;
		
		// Setup the Listeners
		pv.musicSliderListener(new MusicSliderListener());
		pv.sfxSliderListener(new SfxSliderListener());
		pv.screenSizeListener(new ScreenSizeListener());
		pv.saveListener(new SaveListener());
		pv.sfxTestListener(new SfxTestListener());
		pv.mfxTestListener(new MfxTestListener());
	}
	
	/**
	 * Saves the current PreferenceModel to disk
	 * TODO: Write this
	 * @author BU CS673 - Clone Productions
	 */
	class SaveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Save");
		}
	}
	
	/**
	 * Plays a Sound Effect test
	 * @author BU CS673 - Clone Productions
	 */
	class SfxTestListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("SFX Test");
		}
	}
	
	/**
	 * Plays a Music Test
	 * @author BU CS673 - Clone Productions
	 */
	class MfxTestListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("MFX Test");
		}
	}
	
	/**
	 * Updates PreferencesModel when the Music Slider is moved
	 * @author BU CS673 - Clone Productions
	 */
	class MusicSliderListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent ce)
		{
			pm.setMfxVol(pv.getMFXVol());
			System.out.println("New MVol: " + pv.getMFXVol());
		}
	}
	
	/**
	 * Updates PreferencesModel when the SFX Slider is moved
	 * @author BU CS673 - Clone Productions
	 */
	class SfxSliderListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent ce)
		{
			pm.setSfxVol(pv.getSFXVol());
			System.out.println("New SVol: " + pv.getSFXVol());
		}
	}
	
	/**
	 * Updates PreferencesModel when the Screen Slider is moved
	 * @author BU CS673 - Clone Productions
	 */
	class ScreenSizeListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent ce)
		{
			pm.setScreenSize(pv.getScreenSize());
			System.out.println("New SS: " + pv.getScreenSize());
		}
	}
}