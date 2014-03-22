package bubolo.ui.Preferences;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bubolo.util.PreferencesManager;

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
	 * Shows the PreferencesViewer Frame
	 */
	public void showView()
	{
		pv.setVisible(true);
	}
	
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
		this.pv.musicSliderListener(new MusicSliderListener());
		this.pv.sfxSliderListener(new SfxSliderListener());
		this.pv.screenSizeListener(new ScreenSizeListener());
		this.pv.saveListener(new SaveListener());
		this.pv.cancelListener(new CancelListener());
		this.pv.sfxTestListener(new SfxTestListener());
		this.pv.mfxTestListener(new MfxTestListener());
	}
	
	public int getSfxVol()
	{
		return pm.getSfxVol();
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
			PreferencesManager pmgr = new PreferencesManager();
			pmgr.SavePreference(pm);
		}
	}
	
	/**
	 * Hide the PreferencesView
	 * @author BU CS673 - Clone Productions
	 */
	class CancelListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			pv.setVisible(false);
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