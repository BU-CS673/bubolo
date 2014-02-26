package bubolo.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import bubolo.world.entity.Actor;
import bubolo.events.*;

public class KeyboardInput implements Input, KeyListener{

	private Actor localTank;
	public KeyboardInput(Actor tank){
		this.localTank = tank;
	}
	@Override
	public void keyPressed(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_UP){
			EventQueue.q.add(new MoveForward(localTank));
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
