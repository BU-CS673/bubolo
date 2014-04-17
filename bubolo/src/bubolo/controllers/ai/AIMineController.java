package bubolo.controllers.ai;

import com.badlogic.gdx.math.Intersector;

import bubolo.controllers.Controller;
import bubolo.util.TileUtil;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Mine;
import bubolo.world.entity.concrete.MineExplosion;


/**
 * A controller for Mines. This controller automatically checks for collisions with tanks or bullets 
 * and explodes the mine on detection
 * 
 * @author BU CS673 - Clone Productions
 */
public class AIMineController implements Controller
{
	private Mine mine;

	/**
	 * constructs an AI Mine controller
	 * 
	 * @param mine
	 *            the mine this controller will correspond to.
	 */
	public AIMineController(Mine mine)
	{
		this.mine = mine;
	}

	@Override
	public void update(World world)
	{
		for(Entity collider:TileUtil.getLocalEntities(mine.getX(),mine.getY(), world))
		{
			if (collider.isSolid() && collider != mine)
			{
				if (Intersector.overlapConvexPolygons(collider.getBounds(), mine.getBounds()))
				{
					if(mine.isActive())
					{
						MineExplosion mineExplosion = world.addEntity(MineExplosion.class);
						mineExplosion.setParams(mine.getX(), mine.getY(), 0);
						
						TileUtil.getEntityTile(mine, world).clearElement();
						mine.dispose();
						return;	
					}
				}
			}
		}
	}
}