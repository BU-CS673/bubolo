package bubolo.ui;

 
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
public class Main  {
    public static void main(String args[])throws Exception
    {
        Splash s=new Splash();
        s.setVisible(true);
        Thread t=Thread.currentThread();
        Thread.sleep(10000);
        s.dispose();
        FirstPage fp = new FirstPage();
        fp.setVisible(true);
        
        SwingUtilities.invokeLater(new Runnable(){
            public void run()
            {
                //opening the main application
                 //new MainApplication().setVisible(true);
                
            }
        });
    }
 
}