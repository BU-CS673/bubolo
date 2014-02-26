package bubolo.events;

import bubolo.world.entity.Actor;

public class MoveForward implements Command{

	private Actor actor;
	public MoveForward(Actor actor){
		this.actor = actor;
	}
	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		actor.setY(actor.getY()+1);
	}

}
