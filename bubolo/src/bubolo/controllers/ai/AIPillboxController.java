package bubolo.controllers.ai;

import bubolo.controllers.Controller;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Pillbox;

public class AIPillboxController implements Controller
{
	private Pillbox pillbox;
	private final double range = 200;
	public AIPillboxController(Pillbox pillbox)
	{
		this.pillbox = pillbox;
	}
	@Override
	public void update(World world) 
	{
		// TODO Auto-generated method stub
		
		//System.out.print(targetDirection+" ");
		if(pillbox.isCannonReady())
		{
			Entity target = getTarget(world);
			if (targetInRange(target))
			{
				fire(getTargetDirection(target),world);
			}
		}
	}
	private Entity getTarget(World world)
	{
		Entity target=null;		
		double xdistance = 0;
		double ydistance = 0;
		double targetdistance = -1;
		double distance = 0;
		
		for(Entity entity : world.getTanks())
		{
			xdistance = Math.abs(pillbox.getCenterX() - entity.getCenterX());
			ydistance = Math.abs(pillbox.getCenterY() - entity.getCenterY());
			distance = Math.sqrt((xdistance*xdistance)+ (ydistance*ydistance));
			
			if (targetdistance > -1)
			{
				if(distance < targetdistance)
				{
					targetdistance = distance;
					target = entity;
				}
			}else
			{
				targetdistance = distance;
				target = entity;
			}
		}
		return target;
	}
	private boolean targetInRange(Entity target)
	{
		double xdistance = 0;
		double ydistance = 0;
		double distance = 0;

		xdistance = Math.abs(pillbox.getCenterX() - target.getCenterX());
		ydistance = Math.abs(pillbox.getCenterY() - target.getCenterY());
		distance = Math.sqrt((xdistance*xdistance)+ (ydistance*ydistance));
				
		return (distance < range); 
	}
	/**
	 * returns the angle to the closest target for the pillbox
	 * @param world
	 * @return 
	 * 		the angle toward the closest tank.  returns -1 if no tanks in range
	 */
	private float getTargetDirection(Entity target)
	{
		double xvector = 0;
		double yvector = 0;
		float direction = -1;
		
		xvector = target.getCenterX() - pillbox.getCenterX();
		yvector = target.getCenterY() - pillbox.getCenterY();
		direction = (float)Math.atan2(yvector,xvector);
		
		return direction;
	}
	/**
	 * tell the pillbox to aim and fire
	 * @param rotation
	 * @param world
	 */
	private void fire(float rotation, World world)
	{
		// TODO : calculate and update this with correct starting
		// offset.
		pillbox.aimCannon(rotation);
		pillbox.fireCannon(world);
	}

}