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
 * A controller for bases. This controller checks for contact with its owner and heals and
 * reloads the owner accordingly.
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
	 * Time allowed between supply orders
	 */
	private static final long resupplyDelayTime = 500;

	/**
	 * Time since last supply order
	 */
	private long lastSupplyTime = 0;

	/**
	 * Time allowed for base to gain supplies
	 */
	private static final long replinishTime = 750;
	
	/**
	 * How many Hit Points the base can heal per update
	 */
	private static final int HIT_POINTS_PER_HEAL = 10;
	
	/**
	 * Time since the last replinishment
	 */
	private long lastReplinishment = 0;

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
		this.base.setCharging(false);
		for (Entity entity : TileUtil.getLocalCollisions(this.base, world))
		{
			if (entity instanceof Tank)
			{
				Tank tank = (Tank) entity;
				if (!this.base.isOwned())
				{
					this.base.setOwned(true);
					this.base.setOwnerUID(tank.getId());
					this.base.heal(100);
					if (tank.isLocalPlayer())
					{
						this.base.setLocalPlayer(true);
						Network net = NetworkSystem.getInstance();
						net.send(new UpdateOwnable(this.base));
					}
					else
					{
						this.base.setLocalPlayer(false);
					}
				}
				else
				{
					if(tank.getId() == this.base.getOwnerUID())
					{
						this.base.setCharging(true);
						
						if((System.currentTimeMillis() - lastSupplyTime > resupplyDelayTime))
						{
							lastSupplyTime = System.currentTimeMillis();
							if (tank.getHitPoints() < tank.getMaxHitPoints())
							{
								tank.heal(base.giveHitPoints());
							}
							if(tank.getAmmoCount() < tank.getTankMaxAmmo())
							{
								tank.gatherAmmo(base.giveAmmo());
							}
							if(tank.getMineCount() < tank.getTankMaxMineCount())
							{
								tank.gatherMine(base.giveMine());
							}
						}
					}
				}
			}
		}
		if(System.currentTimeMillis() - lastReplinishment < replinishTime)
		{
			base.heal(HIT_POINTS_PER_HEAL);
			base.gatherAmmo();
			base.gatherMines();
		}
	}
}