package bubolo.ui.Preferences;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bubolo.audio.Audio;
import bubolo.util.PreferencesManager;

/**
 * Controller class for Preferences, handles disk activity for Models
 * 
 * @author BU CS673 - Clone Productions
 */

public class PreferencesController
{
	
	// Set this to print Preferences changes to the Console
	private boolean DEBUG = false;
	
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
		// The PreferencesView window & sliders might be pre-created, so force the PreferencesView to update itself first
		pv.updateView(pm);

		// Show PreferenceView on screen
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
		this.pv.closeListener(new CancelListener());
		this.pv.sfxTestListener(new SfxTestListener());
		this.pv.mfxTestListener(new MfxTestListener());
		
		// TODO: EMS--Is this the best way to handle this?
		// Create a new PreferencesManager
		PreferencesManager pmgr = new PreferencesManager();
		// Create a temporary PreferencesModel
		PreferencesModel pmtemp = new PreferencesModel();
		// Use the PreferencesManager to Load Preferences into this from disk
		pmtemp = pmgr.LoadPreference();
		// Set this PreferencesController's PreferencesModel to be equal to the temporary PreferencesModel
		this.pm.set(pmtemp);	
	}
	
	/**
	 * Returns the current Sound Effects Volume
	 * @return the current Sound Effects Volume
	 */
	public int getSfxVol()
	{
		return pm.getSfxVol();
	}
	
	/**
	 * Listens for Save button push; Saves the current PreferenceModel to disk
	 * @author BU CS673 - Clone Productions
	 */
	class SaveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// Create a Preferences Manager
			PreferencesManager pmgr = new PreferencesManager();
			
			// Send our PreferencesModel to be Saved by the PreferencesManager
			pmgr.SavePreference(pm);

			// Hide the Preferences Window
			// Fixes Issue #238
			pv.setVisible(false);
			
			// Debug Output to Console
			if (DEBUG)
			{
				System.out.println("Save Pushed");
			}

		}
	}
	
	/**
	 * Listens for Cancel button push; Hide the PreferencesView
	 * @author BU CS673 - Clone Productions
	 */
	class CancelListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// Hide the Preferences Window
			pv.setVisible(false);
			
			// Debug Output to Console
			if (DEBUG)
			{
				System.out.println("Cancel Pushed");
			}
		}
	}
	
	/**
	 * Listens for the Test Sound Effects button push; Plays a Sound Effect test
	 * TODO: Fix the play sound effects
	 * @author BU CS673 - Clone Productions
	 */
	class SfxTestListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// Play a sound effect
			//Audio.play(Sfx.TANK_IN_SHALLOW_WATER);
			
			// Debug Output to Console
			if (DEBUG)
			{
				System.out.println("SFX Test Pushed");
			}
		}
	}
	
	/**
	 * Listens for the Test Music Effects button push; Plays a Music Test
	 * TODO: Fix the play Music stuff
	 * @author BU CS673 - Clone Productions
	 */
	class MfxTestListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// Play some music
//			Audio.startMusic();
//			try
//			{
//				Thread.sleep(2000);
//			}
//			catch (InterruptedException e1)
//			{
//				e1.printStackTrace();
//			}
//			Audio.stopMusic();
			
			// Debug Output to Console
			if (DEBUG)
			{
				System.out.println("MFX Test Pushed");
			}
		}
	}
	
	/**
	 * Listens for Music Volume Slider Movement; Updates PreferencesModel when the Music Slider is moved
	 * TODO: Check that line for changing the Game's Music Volume
	 * @author BU CS673 - Clone Productions
	 */
	class MusicSliderListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent ce)
		{
			// Set the new Music Volume Level in PreferenceModel
			pm.setMfxVol(pv.getMFXVol());
			
			// Change the Game's Music Volume Level
			Audio.setMusicVolume(pv.getMFXVol());

			// Debug Output to Console
			if (DEBUG)
			{
				System.out.println("New MVol: " + pv.getMFXVol());
			}
		}
	}
	
	/**
	 * Listens for SFX Volume Slider Movement; Updates PreferencesModel when the SFX Slider is moved
	 * TODO: Change the Game's SFX Volume
	 * @author BU CS673 - Clone Productions
	 */
	class SfxSliderListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent ce)
		{
			// Set the new Sound Effects Volume Level in PreferencesModel
			pm.setSfxVol(pv.getSFXVol());
			
			// Change the Game's Sound Effects Volume Level
			Audio.setSoundEffectVolume(pv.getSFXVol());
			
			// Debug Output to Console
			if (DEBUG)
			{
				System.out.println("New SVol: " + pv.getSFXVol());
			}
		}
	}
	
	/**
	 * Lists for Screen Size Slider Movement; Updates PreferencesModel when the Screen Slider is moved
	 * TODO: Change the screen size in the game
	 * @author BU CS673 - Clone Productions
	 */
	class ScreenSizeListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent ce)
		{
			pm.setScreenSize(pv.getScreenSize());
			
			// Debug Output to Console
			if (DEBUG)
			{
				System.out.println("New SS: " + pv.getScreenSize());
			}
		}
	}
}