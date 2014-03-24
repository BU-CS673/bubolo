package bubolo.ui.Preferences;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeListener;

import bubolo.ui.UserInterface;

public class SetControlsView extends JFrame 
{

	// Define buttons for Tank controls
	private JButton btnTankFire, btnTankForward, btnTankStop, btnTankRight, btnTankLeft, btnTankMine;
	
	// Define labels for Tank controls
	private JLabel lblTankFire, lblTankForward, lblTankStop, lblTankRight, lblTankLeft, lblTankMine;
	
	// Define buttons for Engineer controls
	private JButton btnEngMine, btnEngWall, btnEngFire, btnEngTree, btnEngRoad, btnEngPillbox;
	
	// Define labels for Engineer controls
	private JLabel lblEngMine, lblEngWall, lblEngFire, lblEngTree, lblEngRoad, lblEngPillbox;
	
	public SetControlsView()
	{
		setIconImage(UserInterface.gameIcon.getImage());
				
		this.setSize(500,400);
		
		this.setResizable(false);
		this.setTitle("Set Controls");
		
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		this.add(ControlSelectPanel());
		this.add(ControlGridPanel());
		this.add(ApplySaveCancelPanel());	
	}

	private JPanel ControlSelectPanel()
	{
		JPanel controlSelectPanel = new JPanel();
		controlSelectPanel.setPreferredSize(new Dimension(100,50));
		controlSelectPanel.setBorder(BorderFactory.createTitledBorder("Select A Control Set"));
		
		String[] controlOptions = {"Default", "User Defined #1", "User Defined #2"};
		JComboBox controlList = new JComboBox(controlOptions);
		controlList.setPreferredSize(new Dimension(450,25));
		
		controlSelectPanel.add(controlList);
			
		return controlSelectPanel;
	}
		
	private JPanel ApplySaveCancelPanel()
	{
		JPanel ascPanel = new JPanel();
	
		ascPanel.setLayout(new GridLayout(1,3));
		
		ascPanel.add(new JButton("APPLY"));
		ascPanel.add(new JButton("SAVE"));
		ascPanel.add(new JButton("CANCEL"));
		
		return ascPanel;
	}
		
	private JPanel ControlGridPanel()
	{
		JPanel controlGridPanel = new JPanel();
		controlGridPanel.setLayout(new GridLayout(1,2));
		controlGridPanel.setBorder(BorderFactory.createTitledBorder("Click Button To Change Control"));
	
		controlGridPanel.setPreferredSize(new Dimension(500,250));
		controlGridPanel.setSize(500,250);
		
		JPanel tankPanel = new JPanel();
		tankPanel.setBorder(BorderFactory.createTitledBorder("Tank"));
		tankPanel.setLayout(new GridLayout(6,1));
		
		ControlPanel pnlTankFire = new ControlPanel("Fire", "Space");
		tankPanel.add(pnlTankFire);
		
		ControlPanel pnlTankForward = new ControlPanel("Forward", "W");
		tankPanel.add(pnlTankForward);
	
		ControlPanel pnlTankLeft = new ControlPanel("Left", "A");
		tankPanel.add(pnlTankLeft);
	
		ControlPanel pnlTankRight = new ControlPanel("Right", "D");
		tankPanel.add(pnlTankRight);
	
		ControlPanel pnlTankStop = new ControlPanel("Stop", "S");
		tankPanel.add(pnlTankStop);		
	
		ControlPanel pnlTankMine = new ControlPanel("Mine", "Q");
		tankPanel.add(pnlTankMine);
		
		
		JPanel engineerPanel = new JPanel();
		engineerPanel.setBorder(BorderFactory.createTitledBorder("Engineer"));
		engineerPanel.setLayout(new GridLayout(6,1));
		
		ControlPanel pnlEngMine = new ControlPanel("Mine", "`");
		engineerPanel.add(pnlEngMine);
		
		ControlPanel pnlEngTree = new ControlPanel("Tree", "2");
		engineerPanel.add(pnlEngTree);
	
		ControlPanel pnlEngWall = new ControlPanel("Wall", "1");
		engineerPanel.add(pnlEngWall);
		
		ControlPanel pnlEngRoad = new ControlPanel("Road", "3");
		engineerPanel.add(pnlEngRoad);
		
		ControlPanel pnlEngPillbox = new ControlPanel("Pillbox", "4");
		engineerPanel.add(pnlEngPillbox);
		
		controlGridPanel.add(tankPanel);
		controlGridPanel.add(engineerPanel);
		
		return controlGridPanel;
	}
	
	class ControlPanel extends JPanel
	{
		public ControlPanel(String name, String key)
		{
			
			this.setPreferredSize(new Dimension(215,30));
			//this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
			ControlButton button = new ControlButton(name);
			ControlLabel label = new ControlLabel(key);
			this.add(button);
			this.add(label);
		}
	}
	
	class ControlButton extends JButton
	{
		public ControlButton(String name)
		{
			this.setPreferredSize(new Dimension(100,25));
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setVerticalAlignment(SwingConstants.CENTER);
			this.setText(name);
		}
	}
	
	class ControlLabel extends JLabel
	{
		public ControlLabel(String key)
		{
			this.setPreferredSize(new Dimension(100, 25)); 
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setVerticalAlignment(SwingConstants.CENTER);
			this.setText(key);
			this.setBorder( new BevelBorder( BevelBorder.LOWERED ) );
		}
	}
	
	/**
	 * SFX Slider event listener
	 * @param event returns the event
	 */
	void tListener(ChangeListener event)
	{
		sfxSlider.addChangeListener(event);
	}
}
