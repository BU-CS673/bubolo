package bubolo.ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JOptionPane;

import java.awt.*;

import javax.swing.border.TitledBorder;

public class Preferences extends JDialog
{
    JPanel panel = new JPanel();
    //JLabel title = new JLabel("B.U.B.O.L.O.", JLabel.CENTER);
    //JLabel subtitle = new JLabel("(Clone Productions)", JLabel.CENTER);
    JLabel soundLabel = new JLabel("Sound");
    JButton saveButton = new JButton("Save");
    JButton backButton = new JButton("Back");
    JLabel resLabel = new JLabel("Screen Resolution");

    static final int sound_min =0;
    static final int sound_max = 100;
    static final int sound_initial = 30;

    JSlider soundSlider = new JSlider(JSlider.HORIZONTAL, sound_min, sound_max, sound_initial);

    String choices[] = 
    {
        "400 x 400",
        "500 x 500",
        "800 x 800"
    };
    
    JComboBox<String> resComboBox = new JComboBox<>(choices);


    //public static void main(String[] args)
    //{
    //    new Preferences();
    //}
    
    public Preferences(JFrame owner)
    {
        super(owner, "Preferences", true);

        panel.setBorder(new TitledBorder("System Preferences"));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        
        setSize(400, 150);

        //c.fill = GridBagConstraints.HORIZONTAL;
        //c.gridx = 0;
        //c.gridy = 0;
        //c.gridwidth = 4;
        //panel.add(title, c);

        //c.fill = GridBagConstraints.HORIZONTAL;
        //c.gridx = 0;
        //c.gridy = 1;
        //c.gridwidth = 4;
        //c.insets = new Insets(0, 0, 10, 0);
        //panel.add(subtitle, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 10, 0);
        panel.add(soundLabel, c); 

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        c.insets = new Insets(0, 5, 10, 0);
        panel.add(soundSlider, c);
       
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 10, 0);
        panel.add(resLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        c.insets = new Insets(0, 10, 0, 0);
        panel.add(resComboBox, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 1;
        c.insets = new Insets(10, 0, 0, 0);
        backButton.setBackground(Color.GRAY);
        panel.add(backButton, c); 

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 8;
        c.gridwidth = 1;
        c.insets = new Insets(10, 50, 0, 0);
        saveButton.setBackground(Color.GREEN);
        panel.add(saveButton, c);  

        //addActionListener
        backButton.addActionListener(new java.awt.event.ActionListener() {
    		@Override
    		public void actionPerformed(java.awt.event.ActionEvent evt)
    		{
    			jBackButtonActionPerformed(evt);
    		}
    	});
        
        saveButton.addActionListener(new java.awt.event.ActionListener() {
    		@Override
    		public void actionPerformed(java.awt.event.ActionEvent evt)
    		{
    			jSaveButtonActionPerformed(evt);
    		}
    	});
        
        add(panel);        

        setVisible(true);
    }
    
    private void jBackButtonActionPerformed(java.awt.event.ActionEvent evt)
	{
		// TODO add your handling code here:
		this.dispose();
	}
    
    private void jSaveButtonActionPerformed(java.awt.event.ActionEvent evt)
	{
		// TODO add your handling code here:
		this.dispose();
	}
}
