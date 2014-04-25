package bubolo.controllers.ai;

import bubolo.controllers.Controller;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.net.command.UpdateOwnable;
import bubolo.util.TileUtil;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Base;
import bubolo.world.entity.concrete.Tank;

/**
 * A controller for bases. This controller checks for contact with its owner 
 * and heals and reloads the owner accordingly.
 * 
 * @author BU CS673 - Clone Productions
 */
public class AIBaseController implements Controller
{
	/**
	 * the base this controller is controlling
	 */
	private Base base;
	
	/**
	 * hitpoints to heal per game tick
	 */
	private int healPointsPerTick = 1;
	
	/**
	 * ammo to add to tank per game tick
	 */
	private int ammoPerTick = 1;
	
	/**
	 * constructs an AI Base controller
	 * 
	 * @param base
	 *            the base this controller will correspond to.
	 */
	public AIBaseController(Base base)
	{
		this.base = base;
	}

	@Override
	public void update(World world)
	{	
		for(Entity entity:TileUtil.getLocalCollisions(this.base, world))
		{
			if (entity instanceof Tank)
			{
				Tank tank = (Tank)entity;
				if(!this.base.isOwned())
				{
					this.base.setOwned(true);
					this.base.setOwnerUID(tank.getId());
					if(tank.isLocalPlayer())
					{
						this.base.setLocalPlayer(true);
					}
					else
					{
						this.base.setLocalPlayer(false);
					}
					Network net = NetworkSystem.getInstance();
					net.send(new UpdateOwnable(this.base));
				}
				else
				{
					if(tank.getId() == this.base.getOwnerUID())
					{
						tank.heal(healPointsPerTick);
						tank.gatherAmmo(ammoPerTick);
					}
				}
			}
		}
	}
}