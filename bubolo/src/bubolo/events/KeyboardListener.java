package bubolo.events;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import bubolo.world.Tank;


public class KeyboardListener implements KeyListener {
	private Tank myTank = new Tank();
    public KeyboardListener() {
    	

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP){
        	Command cmd = new TankMoveForward(myTank);
        	ActionQueue.q.add(cmd);
        }
    }

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}