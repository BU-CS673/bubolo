package bubolo.controllers.ai;

import bubolo.controllers.Controller;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.net.command.UpdateOwnable;
import bubolo.util.TileUtil;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Pillbox;
import bubolo.world.entity.concrete.Tank;

/**
 * A controller for pillboxes. This controller automatically finds a target within range
 * of the pillbox and fires based on the set reload speed.
 * 
 * @author BU CS673 - Clone Productions
 */
public class AIPillboxController implements Controller
{
	private Pillbox pillbox;

	/**
	 * constructs an AI Pillbox controller
	 * 
	 * @param pillbox
	 *            the pillbox this controller will correspond to.
	 */
	public AIPillboxController(Pillbox pillbox)
	{
		this.pillbox = pillbox;
	}

	@Override
	public void update(World world)
	{
		if (pillbox.isCannonReady())
		{
			Tank target = (Tank)getTarget(world);
			if (target != null)
			{	
				if (targetInRange(target))
				{
					fire(getTargetDirection(target), world);
				}						
			}
		}
		
		if(!this.pillbox.isOwned() && this.pillbox.getHitPoints() <= 0)
		{
			for(Entity entity:TileUtil.getLocalCollisions(this.pillbox, world))
			{
				if (entity instanceof Tank)
				{
					Tank tank = (Tank)entity;
					this.pillbox.setOwned(true);
					this.pillbox.setOwnerUID(tank.getId());
					if(tank.isLocalPlayer())
					{
						this.pillbox.setLocalPlayer(true);
						sendNetUpdate(this.pillbox);
					}
					else
					{
						this.pillbox.setLocalPlayer(false);
					}
				}
			}
		}

	}

	/**
	 * find a target for the pillbox to shoot at
	 * 
	 * @param world
	 *            reference to the game world
	 * 
	 * @return target always will return the closest tank regardless if it is range or not
	 */
	private Entity getTarget(World world)
	{
		Entity target = null;
		double xdistance = 0;
		double ydistance = 0;
		double targetdistance = -1;
		double distance = 0;

		for (Entity entity : world.getTanks())
		{
			if(entity.getId() != this.pillbox.getOwnerUID())
			{
				xdistance = Math.abs(pillbox.getX() - entity.getX());
				ydistance = Math.abs(pillbox.getY() - entity.getY());
				distance = Math.sqrt((xdistance * xdistance) + (ydistance * ydistance));
	
				if (targetdistance > -1)
				{
					if (distance < targetdistance)
					{
						targetdistance = distance;
						target = entity;
					}
				}
				else
				{
					targetdistance = distance;
					target = entity;
				}
			}
		}

		if (target == null || ((Tank) target).isHidden())
		{
			return null;
		}
		else
		{
			return target;
		}
	}

	/**
	 * determine if the target tank is within range of the pillbox
	 * 
	 * @param target
	 *            the tank the pillbox is targeting
	 * @return targetInRange returns true if the target is within range of this pillbox
	 */
	private boolean targetInRange(Entity target)
	{
		double xdistance = 0;
		double ydistance = 0;
		double distance = 0;

		xdistance = Math.abs(pillbox.getX() - target.getX());
		ydistance = Math.abs(pillbox.getY() - target.getY());
		distance = Math.sqrt((xdistance * xdistance) + (ydistance * ydistance));

		return (distance < pillbox.getRange());
	}

	/**
	 * returns the angle to the closest target for the pillbox
	 * 
	 * @param Target
	 *            the Tank for the pillbox to target
	 * @return the angle toward the closest tank. returns -1 if no tanks in range
	 */
	private float getTargetDirection(Entity target)
	{
		double xvector = 0;
		double yvector = 0;
		float direction = -1;

		xvector = target.getX() - pillbox.getX();
		yvector = target.getY() - pillbox.getY();
		direction = (float) Math.atan2(yvector, xvector);

		return direction;
	}

	/**
	 * tell the pillbox to aim and fire
	 * 
	 * @param rotation
	 * @param world
	 */
	private void fire(float rotation, World world)
	{
		pillbox.aimCannon(rotation);
		pillbox.fireCannon(world);
	}
	private static void sendNetUpdate(Pillbox pillbox)
	{
		Network net = NetworkSystem.getInstance();
		net.send(new UpdateOwnable(pillbox));
	}
}