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

import bubolo.ui.UserInterface;


/** Creates a SetControlsView, this is the GUI for setting Controls
 * 
 * @author BU CS673 - Clone Productions
 */
public class SetControlsView extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5344872007185901704L;

	// Define buttons for Tank controls
	private ControlButton btnTankFire, btnTankForward, btnTankStop, btnTankRight, btnTankLeft, btnTankMine;
	
	// Define labels for Tank controls
	private ControlLabel lblTankFire, lblTankForward, lblTankStop, lblTankRight, lblTankLeft, lblTankMine;
	
	// Define buttons for Engineer controls
	private ControlButton btnEngMine, btnEngWall, btnEngTree, btnEngRoad, btnEngPillbox;
	
	// Define labels for Engineer controls
	private ControlLabel lblEngMine, lblEngWall, lblEngTree, lblEngRoad, lblEngPillbox;
	
	/** Constructor for SetControlsView
	 * 
	 */
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

	private static JPanel ControlSelectPanel()
	{
		JPanel controlSelectPanel = new JPanel();
		controlSelectPanel.setPreferredSize(new Dimension(100,50));
		controlSelectPanel.setBorder(BorderFactory.createTitledBorder("Select A Control Set"));
		
		String[] controlOptions = {"Default", "User Defined #1", "User Defined #2"};
		JComboBox<?> controlList = new JComboBox<Object>(controlOptions);
		controlList.setPreferredSize(new Dimension(450,25));
		
		controlSelectPanel.add(controlList);
			
		return controlSelectPanel;
	}
		
	private static JPanel ApplySaveCancelPanel()
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
		
		ControlPanel pnlTankFire = new ControlPanel("Fire", "Space", btnTankFire, lblTankFire);
		tankPanel.add(pnlTankFire);
		
		ControlPanel pnlTankForward = new ControlPanel("Forward", "W", btnTankForward, lblTankForward);
		tankPanel.add(pnlTankForward);
	
		ControlPanel pnlTankLeft = new ControlPanel("Left", "A", btnTankLeft, lblTankLeft);
		tankPanel.add(pnlTankLeft);
	
		ControlPanel pnlTankRight = new ControlPanel("Right", "D", btnTankRight, lblTankRight);
		tankPanel.add(pnlTankRight);
	
		ControlPanel pnlTankStop = new ControlPanel("Stop", "S", btnTankStop, lblTankStop);
		tankPanel.add(pnlTankStop);		
	
		ControlPanel pnlTankMine = new ControlPanel("Mine", "Q", btnTankMine, lblTankMine);
		tankPanel.add(pnlTankMine);
		
		
		JPanel engineerPanel = new JPanel();
		engineerPanel.setBorder(BorderFactory.createTitledBorder("Engineer"));
		engineerPanel.setLayout(new GridLayout(6,1));
		
		ControlPanel pnlEngMine = new ControlPanel("Mine", "`", btnEngMine, lblEngMine);
		engineerPanel.add(pnlEngMine);
		
		ControlPanel pnlEngTree = new ControlPanel("Tree", "2", btnEngTree, lblEngTree);
		engineerPanel.add(pnlEngTree);
	
		ControlPanel pnlEngWall = new ControlPanel("Wall", "1", btnEngWall, lblEngWall);
		engineerPanel.add(pnlEngWall);
		
		ControlPanel pnlEngRoad = new ControlPanel("Road", "3", btnEngRoad, lblEngRoad);
		engineerPanel.add(pnlEngRoad);
		
		ControlPanel pnlEngPillbox = new ControlPanel("Pillbox", "4", btnEngPillbox, lblEngPillbox);
		engineerPanel.add(pnlEngPillbox);
		
		controlGridPanel.add(tankPanel);
		controlGridPanel.add(engineerPanel);
		
		return controlGridPanel;
	}
	
	private class ControlPanel extends JPanel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -2397092295307201421L;

		public ControlPanel(String name, String key, ControlButton button, ControlLabel label)
		{
			this.setPreferredSize(new Dimension(215,30));
			button = new ControlButton(name);
			label = new ControlLabel(key);
			this.add(button);
			this.add(label);
		}
	}
	
	private class ControlButton extends JButton
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 8499963169791414178L;

		public ControlButton(String name)
		{
			this.setPreferredSize(new Dimension(100,25));
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setVerticalAlignment(SwingConstants.CENTER);
			this.setText(name);
		}
	}
	
	private class ControlLabel extends JLabel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -3481691236855305325L;

		public ControlLabel(String key)
		{
			this.setPreferredSize(new Dimension(100, 25)); 
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setVerticalAlignment(SwingConstants.CENTER);
			this.setText(key);
			this.setBorder( new BevelBorder( BevelBorder.LOWERED ) );
		}
	}
	
//	/**
//	 * SFX Slider event listener
//	 * @param event returns the event
//	 */
//	void tListener(ChangeListener event)
//	{
//		sfxSlider.addChangeListener(event);
//	}
}
