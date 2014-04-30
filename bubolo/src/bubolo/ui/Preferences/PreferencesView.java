package bubolo.ui.Preferences;

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
import javax.swing.event.ChangeListener;

import bubolo.ui.UserInterface;

/**
 * View (display) class for the Preferences package
 * 
 * @author BU CS673 - Clone Productions
 */
public class PreferencesView extends JFrame
{

	private static final long serialVersionUID = 5978853859761330342L;

	// Volume Max/Min
	private int VOL_MAX = 100;
	private int VOL_MIN = 0;
	
	// Screen Size Max/Min
	private int SCREENSIZE_MAX = 3;
	private int SCREENSIZE_MIN = 1;
	
	// The Sound Test icon
	private ImageIcon testIcon = new ImageIcon(UserInterface.ICONS_PATH + "test_sound.png");
	
	// Declaration of the controls that will appear on the window
	private JButton closeBtn;
	private JButton saveBtn;
	private JButton sfxTest;
	private JButton mfxTest;
	
	private JSlider sfxSlider;
	private JSlider mfxSlider;
	private JSlider screenSize;
	
	// Declation of the PreferencesModel that will be used
	private PreferencesModel pm;

	/**
	 * Constructor will create a new PreferencesView (a JFrame window)
	 * @param pm Preferences Model
	 */	
	public PreferencesView(PreferencesModel pm)
	{
		this.pm = pm;
		
		// Set the Frame(Window) details & icons
		setTitle("Preferences");
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(300,300);
		setIconImage(UserInterface.gameIcon.getImage());
		
		// Create the Main Panel for this Frame (Window)
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
				
		// Add SFX and MFX Volume Sliders to the Main Panel
		mainPanel.add(createSfxVolSliderPanel());
		mainPanel.add(createMfxVolSliderPanel());
		
		// Add Screen Size to the Main Panel
		mainPanel.add(createScreenSizePanel());
		
		// Add Save/Close to the Main Panel
		mainPanel.add(createSaveClosePanel());
		
		// Add Main Panel to the Frame (Window)
		add(mainPanel);
	}

	/**
	 * Internal Only -- Creates a JPanel with the ScreenSize slider
	 * @param currentSize The current Screen Size Setting
	 * @return
	 */
	private JPanel createScreenSizePanel()
	{
		// Create the screen size panel
		JPanel screenSizePanel = new JPanel();
		screenSizePanel.setBorder(BorderFactory.createTitledBorder("Screen Size"));
	
		// Create the Screen Size with MIN, MAX and DEFAULT
		screenSize = new JSlider(SCREENSIZE_MIN, SCREENSIZE_MAX, pm.getScreenSize());
		
		// Set the Ticking & Snapping
		screenSize.setMajorTickSpacing(1);
		screenSize.setPaintTicks(true);
		screenSize.setSnapToTicks(true);
		
		// Set Small, Medium, Large labels
		Hashtable<Integer, JLabel> screenSizeLabelTable = new Hashtable<Integer, JLabel>();
		screenSizeLabelTable.put(new Integer(SCREENSIZE_MIN), new JLabel("Small"));
		screenSizeLabelTable.put(new Integer(2), new JLabel("Normal"));
		screenSizeLabelTable.put(new Integer(SCREENSIZE_MAX), new JLabel("Large"));
		screenSize.setLabelTable(screenSizeLabelTable);
		screenSize.setPaintLabels(true);
		
		// Add Screen Size to the Panel
		screenSizePanel.add(screenSize);
		
		return screenSizePanel;
	}
	
	/**
	 * Internal Only -- Creates a panel with Save/Close buttons
	 * @return JPanel that represents the Save/Close buttons
	 */
	private JPanel createSaveClosePanel()
	{
		// Create Panel
		JPanel SaveClosePanel = new JPanel();
		SaveClosePanel.setLayout(new BoxLayout(SaveClosePanel, BoxLayout.LINE_AXIS));
		
		// Create the Save button
		saveBtn = new JButton("SAVE");
		
		// Create the Close button 
		closeBtn = new JButton("CLOSE");

		// Add the buttons to the panel
		SaveClosePanel.add(saveBtn);
		SaveClosePanel.add(closeBtn);
		
		// Return the panel
		return SaveClosePanel;
	}

	/**
	 * Provides the current Preference Slider's Position
	 * @return sfXSlider int the current slider value
	 */
	public int getSFXVol()
	{
		return sfxSlider.getValue();
	}
	
	/**
	 * Provides the current Preference Slider's Position
	 * @return mfXSlider int the current slider value
	 */
	public int getMFXVol()
	{
		return mfxSlider.getValue();
	}
	
	/**
	 * Provides the current Preference Slider's Position
	 * @return screenSize int the current slider value
	 */
	public int getScreenSize()
	{
		return screenSize.getValue();
	}
	
	/**
	 * Updates the panel
	 * @param updatedPreferencesModel preferences to use for the update
	 */
	public void updateView(PreferencesModel updatedPreferencesModel)
	{
		mfxSlider.setValue(updatedPreferencesModel.getMfxVol());
		sfxSlider.setValue(updatedPreferencesModel.getSfxVol());
		screenSize.setValue(updatedPreferencesModel.getScreenSize());
	}
	
	/**
	 * Internal Only -- Creates a JPanel with a volume slider and test button
	 * @return JPanel with titled border, slider and test button
	 */
	private JPanel createSfxVolSliderPanel()
	{	
		// Create the panel
		JPanel sfxVolPanel = new JPanel();
		
		// Set Border Title which appears on the GUI
		sfxVolPanel.setBorder(BorderFactory.createTitledBorder("Sound Effects Volume"));
		
		// Create the slider
		sfxSlider = new JSlider(VOL_MIN, VOL_MAX, pm.getSfxVol());
		
		// Set tick marks
		sfxSlider.setMajorTickSpacing(10);
		sfxSlider.setPaintTicks(true);
		
		// Create labels at the extreme ends of the slider
		Hashtable<Integer, JLabel> sfxVolLabelTable = new Hashtable<Integer, JLabel>();
		sfxVolLabelTable.put(new Integer(VOL_MIN), new JLabel("Mute"));
		sfxVolLabelTable.put(new Integer(VOL_MAX), new JLabel("11"));
		sfxSlider.setLabelTable(sfxVolLabelTable);
		sfxSlider.setPaintLabels(true);
		
		// Add the volume slider to the panel
		sfxVolPanel.add(sfxSlider);
		
		// Create a sound test button
		sfxTest = new JButton(testIcon);
		
		// Add the volume test button to the panel
		sfxVolPanel.add(sfxTest);
	
		return sfxVolPanel;	
	}
	
	/**
	 * Internal Only -- Creates a JPanel with a volume slider and test button
	 * @return JPanel with titled border, slider and test button
	 */
	private JPanel createMfxVolSliderPanel()
	{	
		// Create the panel
		JPanel mfxVolPanel = new JPanel();
		
		// Set Border Title which appears on the GUI
		mfxVolPanel.setBorder(BorderFactory.createTitledBorder("Music Volume"));
		
		// Create the slider
		mfxSlider = new JSlider(VOL_MIN, VOL_MAX, pm.getMfxVol());
		
		// Set tick marks
		mfxSlider.setMajorTickSpacing(10);
		mfxSlider.setPaintTicks(true);
		
		// Create labels at the extreme ends of the slider
		Hashtable<Integer, JLabel> mfxVolLabelTable = new Hashtable<Integer, JLabel>();
		mfxVolLabelTable.put(new Integer(VOL_MIN), new JLabel("Mute"));
		mfxVolLabelTable.put(new Integer(VOL_MAX), new JLabel("11"));
		mfxSlider.setLabelTable(mfxVolLabelTable);
		mfxSlider.setPaintLabels(true);
		
		// Add the volume slider to the panel
		mfxVolPanel.add(mfxSlider);
		
		// Create a sound test button
		mfxTest = new JButton(testIcon);
		
		// Add the volume test button to the panel
		mfxVolPanel.add(mfxTest);
	
		return mfxVolPanel;	
	}

	/**
	 * Save Button event listener
	 * @param event returns the event
	 */
	void saveListener(ActionListener event)
	{
		saveBtn.addActionListener(event);
	}
	
	/**
	 * Close Button event listener
	 * @param event returns the event
	 */
	void closeListener(ActionListener event)
	{
		closeBtn.addActionListener(event);
	}
	
	/**
	 * SFX Slider event listener
	 * @param event returns the event
	 */
	void sfxSliderListener(ChangeListener event)
	{
		sfxSlider.addChangeListener(event);
	}
	
	/**
	 * Music Slider event listener
	 * @param event returns the event
	 */
	void musicSliderListener(ChangeListener event)
	{
		mfxSlider.addChangeListener(event);
	}
	
	/**
	 * Screen Size Slider event listener
	 * @param event
	 */
	void screenSizeListener(ChangeListener event)
	{
		screenSize.addChangeListener(event);
	}
	
	/**
	 * SFX Test button listener
	 * @param event returns the event
	 */
	void sfxTestListener(ActionListener event)
	{
		sfxTest.addActionListener(event);
	}
	
	/**
	 * Music Test button listener
	 * @param event returns the event
	 */
	void mfxTestListener(ActionListener event)
	{
		mfxTest.addActionListener(event);
	}
}