package bubolo.ui;

import java.awt.GridLayout;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import bubolo.graphics.Graphics;

public class PreferenceScreen extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2997846132408492538L;
	
	public PreferenceScreen()
	{
		// Set the details of the Frame
		this.setTitle("Preferences");
		this.setSize(250,350);
		this.setResizable(false);
		this.setLayout(new GridLayout(3,1));
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon(Graphics.TEXTURE_PATH + "tank_icon.png").getImage());
		
		JPanel sfxPanel = new JPanel();
		sfxPanel.setLayout(new GridLayout(2,0));
		
		JLabel sfxLbl = new JLabel("Sound Effects Volume", JLabel.CENTER);
		JSlider sfxVol = new JSlider(JSlider.HORIZONTAL, 0, 100, 75);
		sfxVol.setMajorTickSpacing(5);
		sfxVol.setPaintTicks(true);
		
		Hashtable<Integer, JLabel> sfxLabelTable = new Hashtable<Integer, JLabel>();
		sfxLabelTable.put(new Integer (0), new JLabel("Mute"));
		sfxLabelTable.put(new Integer (100), new JLabel("11"));
		sfxVol.setLabelTable(sfxLabelTable);
		sfxVol.setPaintLabels(true);
		
		sfxPanel.add(sfxLbl);
		sfxPanel.add(sfxVol);
	
		
		JPanel mfxPanel = new JPanel();
		mfxPanel.setLayout(new GridLayout(2,0));
		
		JLabel mfxLbl = new JLabel("Music Volume", JLabel.CENTER);
		JSlider mfxVol = new JSlider(JSlider.HORIZONTAL, 0, 100, 75);
		mfxVol.setMajorTickSpacing(5);
		mfxVol.setPaintTicks(true);
		
		Hashtable<Integer, JLabel> mfxLabelTable = new Hashtable<Integer, JLabel>();
		mfxLabelTable.put(new Integer (0), new JLabel("Mute"));
		mfxLabelTable.put(new Integer (100), new JLabel("11"));
		mfxVol.setLabelTable(mfxLabelTable);
		mfxVol.setPaintLabels(true);
		
		mfxPanel.add(mfxLbl);
		mfxPanel.add(mfxVol);
		
		
		JPanel sizePanel = new JPanel();
		sizePanel.setLayout(new GridLayout(2,0));
		
		JLabel sizeLbl = new JLabel("Window Size", JLabel.CENTER);
		JSlider sizeVol = new JSlider(JSlider.HORIZONTAL, 1, 3, 2);
		sizeVol.setMajorTickSpacing(1);
		sizeVol.setPaintTicks(true);
		sizeVol.setSnapToTicks(true);
		
		Hashtable<Integer, JLabel> sizeLabelTable = new Hashtable<Integer, JLabel>();
		sizeLabelTable.put(new Integer (1), new JLabel("Small"));
		sizeLabelTable.put(new Integer (2), new JLabel("Normal"));
		sizeLabelTable.put(new Integer (3), new JLabel("Large"));
		sizeVol.setLabelTable(sizeLabelTable);
		sizeVol.setPaintLabels(true);
		
		sizePanel.add(sizeLbl);
		sizePanel.add(sizeVol);
		
		
		add(sfxPanel);
		add(mfxPanel);
		add(sizePanel);
	}
}
