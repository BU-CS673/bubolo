package bubolo.ui.Preferences;

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
import java.awt.event.ActionListener;
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
	
	private int SCREENSIZE_MAX = 3;
	private int SCREENSIZE_MIN = 1;
	
	private ImageIcon testIcon = new ImageIcon(UserInterface.ICONS_PATH + "test_sound.png");

	private ChangeListener sfxVolChange;
	private ChangeListener mfxVolChange;
	
	private ActionListener sndTest;
	
	private ActionListener saveButton;
		
	private ChangeListener screenSizeChange;
	
	
	/**
	 * Constructor will create a new PreferencesView (a JFrame window)
	 * 
	 */	
	public PreferencesView()
	{
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
		mainPanel.add(createVolSliderPanel("Sound", UserInterface.SFXVOL_DEFAULT, sfxVolChange, sndTest));
		mainPanel.add(createVolSliderPanel("Music", UserInterface.MFXVOL_DEFAULT, mfxVolChange, sndTest));
		
		// Add Screen Size to the Main Panel
		mainPanel.add(createScreenSizePanel(UserInterface.SCREENSIZE_DEFAULT));
		
		// Add Save/Cancel to the Main Panel
		mainPanel.add(createSaveCancelPanel());
		
		// Add Main Panel to the Frame (Window)
		add(mainPanel);
	}

/**
 * Internal Only -- Creates a JPanel with the ScreenSize slider
 * @param currentSize The current Screen Size Setting
 * @return
 */
private JPanel createScreenSizePanel(int currentSize)
{
	// Create the screen size panel
	JPanel screenSizePanel = new JPanel();
	screenSizePanel.setBorder(BorderFactory.createTitledBorder("Screen Size"));

	// Create the Screen Size with MIN, MAX and DEFAULT
	JSlider screenSize = new JSlider(SCREENSIZE_MIN, SCREENSIZE_MAX, currentSize);
	
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
	screenSize.setEnabled(false);
	
	// Set Event
	screenSize.addChangeListener(screenSizeChange);
	
	// Add Screen Size to the Panel
	screenSizePanel.add(screenSize);
	
	return screenSizePanel;
}

/**
 * Internal Only -- Creates a panel with Save/Cancel buttons
 * @return JPanel that represents the Save/Cancel buttons
 */
private JPanel createSaveCancelPanel()
{
	// Create B
	JPanel SaveCancelPanel = new JPanel();
	SaveCancelPanel.setLayout(new BoxLayout(SaveCancelPanel, BoxLayout.LINE_AXIS));
	
	// Create the Save button
	JButton saveBtn = new JButton("SAVE");
	saveBtn.addActionListener(saveButton);
	
	// Create & Handle the Cancel button 
	JButton cancelBtn = new JButton("CANCEL");
	cancelBtn.addActionListener(new java.awt.event.ActionListener()
	{
		@Override
		public void actionPerformed(java.awt.event.ActionEvent evt)
		{
			System.exit(0);
		}
	});
	
	SaveCancelPanel.add(saveBtn);
	SaveCancelPanel.add(cancelBtn);
	
	return SaveCancelPanel;
}

/**
 * Internal Only -- Creates a JPanel with a volume slider and test button
 * @param name The name of the volume slider (e.g. Sound Effects, Music)
 * @param currentVol The current volume the slider should be positioned at
 * @param changeListener The slider change event
 * @param actionListener The test button pushed event
 * @return JPanel with titled border, slider and test button
 */
private JPanel createVolSliderPanel(String name, int currentVol, ChangeListener changeListener, ActionListener actionListener)
	{	
	// Create the panel
	JPanel volPanel = new JPanel();
	
	// Set Border Title which appears on the GUI
	volPanel.setBorder(BorderFactory.createTitledBorder(name + "Volume"));
	
	// Create the slider
	JSlider volSlider = new JSlider(VOL_MIN, VOL_MAX, currentVol);
	
	// Set tick marks
	volSlider.setMajorTickSpacing(10);
	volSlider.setPaintTicks(true);
	
	// Create labels at the extreme ends of the slider
	Hashtable<Integer, JLabel> volLabelTable = new Hashtable<Integer, JLabel>();
	volLabelTable.put(new Integer(VOL_MIN), new JLabel("Mute"));
	volLabelTable.put(new Integer(VOL_MAX), new JLabel("11"));
	volSlider.setLabelTable(volLabelTable);
	volSlider.setPaintLabels(true);
	
	// Take action when the slider is updated
	volSlider.addChangeListener(changeListener);
	
	// Add the volume slider to the panel
	volPanel.add(volSlider);
	
	// Create a sound test button
	JButton volTest = new JButton(testIcon);
	volTest.addActionListener(actionListener);
	
	// Add the volume test button to the panel
	volPanel.add(volTest);

	return volPanel;	
	}	
}
