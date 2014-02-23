package bubolo.events;

import bubolo.world.Entity;


public class TankMoveForward implements Command{

	public TankMoveForward(Entity tank){
		//move forward one space
		tank.setY(tank.getY()+1);
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
