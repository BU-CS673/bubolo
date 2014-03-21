package bubolo.ui.Preferences;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bubolo.audio.Audio;
import bubolo.audio.Sfx;
import bubolo.graphics.Graphics;

/**
 * View (display) class for the Preferences package
 * 
 * @author BU CS673 - Clone Productions
 */

public class PreferencesView extends JFrame
{

	private static final long serialVersionUID = 5978853859761330342L;

	private int SFXVOL_MAX = 100;
	private int SFXVOL_MIN = 0;

	private int MFXVOL_MAX = 100;
	private int MFXVOL_MIN = 0;
	
	private int SCREENSIZE_MAX = 3;
	private int SCREENSIZE_MIN = 1;
	
	public static final String ICONS_PATH = "res/icons/";
	
	PreferencesModel prefs;

	/**
	 * Constructor will create a new PreferencesView (a JFrame window)
	 * 
	 * @param prefs the PreferencesModel to use
	 */	
	public PreferencesView(final PreferencesModel prefs)
	{

		this.prefs = prefs;
		
		ImageIcon testIcon = new ImageIcon(ICONS_PATH + "test_sound.png");
		
		//this.pc = new PreferencesController(prefs);
		
		setTitle("Preferences");
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(300,300);
		setIconImage(new ImageIcon(ICONS_PATH + "tank_icon.png").getImage());
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		JPanel sfxPanel = new JPanel();
		sfxPanel.setBorder(BorderFactory.createTitledBorder("Sound Effects Volume"));
		// Create the sfxVol with MIN, MAX and whatever our PreferencesModel says is the current level
		JSlider sfxVol = new JSlider(SFXVOL_MIN, SFXVOL_MAX, this.prefs.getSfxVol());
		sfxVol.setMajorTickSpacing(10);
		sfxVol.setPaintTicks(true);
		Hashtable<Integer, JLabel> sfxVolLabelTable = new Hashtable<Integer, JLabel>();
		sfxVolLabelTable.put(new Integer(SFXVOL_MIN), new JLabel("Mute"));
		sfxVolLabelTable.put(new Integer(SFXVOL_MAX), new JLabel("11"));
		sfxVol.setLabelTable(sfxVolLabelTable);
		sfxVol.setPaintLabels(true);
		// This mess will watch the sfxVol slider and constantly update our PreferencesModel & Audio sub-system
		sfxVol.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent ce)
			{
				int vol = ((JSlider)ce.getSource()).getValue();
				prefs.setSfxVol(vol);
				bubolo.audio.Audio.setSoundEffectVolume(vol);
			}
		});
		// Add sfxVol to the Panel
		JButton testSfx = new JButton(testIcon);
		sfxPanel.add(sfxVol);
		sfxPanel.add(testSfx);
		
		JPanel mfxPanel = new JPanel();
		mfxPanel.setBorder(BorderFactory.createTitledBorder("Music Effects Volume"));
		// Create the mfxVol with MIN, MAX and whatever our PreferencesModel says is the current level
		JSlider mfxVol = new JSlider(MFXVOL_MIN, MFXVOL_MAX, this.prefs.getMfxVol());
		mfxVol.setMajorTickSpacing(10);
		mfxVol.setPaintTicks(true);
		Hashtable<Integer, JLabel> mfxVolLabelTable = new Hashtable<Integer, JLabel>();
		mfxVolLabelTable.put(new Integer(MFXVOL_MIN), new JLabel("Mute"));
		mfxVolLabelTable.put(new Integer(MFXVOL_MAX), new JLabel("11"));
		mfxVol.setLabelTable(mfxVolLabelTable);
		mfxVol.setPaintLabels(true);
		// This mess will watch the mfxVol slider and constantly update our PreferencesModel & Audio sub-system
		mfxVol.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent ce)
			{
				int vol = ((JSlider)ce.getSource()).getValue();
				prefs.setMfxVol(vol);
				bubolo.audio.Audio.setMusicVolume(vol);
			}
		});
		// Add mfxVol to the Panel
		JButton testMfx = new JButton(testIcon);
		
		
		testMfx.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Audio.play(Sfx.CANNON_FIRED);
			}
		});
		
		mfxPanel.add(mfxVol);
		mfxPanel.add(testMfx);
		
		
		JPanel screenSizePanel = new JPanel();
		screenSizePanel.setBorder(BorderFactory.createTitledBorder("Screen Size"));
		// Create the Screen Size with MIN, MAX and whatever our PreferencesModel says is the current value
		JSlider screenSize = new JSlider(SCREENSIZE_MIN, SCREENSIZE_MAX, this.prefs.getScreenSize());
		screenSize.setMajorTickSpacing(1);
		screenSize.setPaintTicks(true);
		screenSize.setSnapToTicks(true);
		Hashtable<Integer, JLabel> screenSizeLabelTable = new Hashtable<Integer, JLabel>();
		screenSizeLabelTable.put(new Integer(SCREENSIZE_MIN), new JLabel("Small"));
		screenSizeLabelTable.put(new Integer(2), new JLabel("Normal"));
		screenSizeLabelTable.put(new Integer(SCREENSIZE_MAX), new JLabel("Large"));
		screenSize.setLabelTable(screenSizeLabelTable);
		screenSize.setPaintLabels(true);
		screenSize.setEnabled(false);
		// This mess will watch the Screen Size slider and constantly update our PreferencesModel
		screenSize.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent ce)
			{
				int vol = ((JSlider)ce.getSource()).getValue();
				prefs.setScreenSize(vol);
				//TODO: Tell the graphics system we're changing scren size 
			}
		});
		// Add Screen Size to the Panel
		screenSizePanel.add(screenSize);
		
		
		
		// Create the Save button and call the Controller's save method 
		JButton saveBtn = new JButton("SAVE");
		saveBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				PreferencesController pc = new PreferencesController(prefs);
				pc.saveToDisk();
			}
		});
		
		// Create the Cancel button 
		JButton cancelBtn = new JButton("CANCEL");
		cancelBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
				dispose();
			}
		});
		
		mainPanel.add(sfxPanel);
		mainPanel.add(mfxPanel);
		mainPanel.add(screenSizePanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(saveBtn);
		buttonPanel.add(cancelBtn);

		mainPanel.add(buttonPanel);
		
		add(mainPanel);

	}
	
}
