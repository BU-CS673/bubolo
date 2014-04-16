/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.controllers.net;

import bubolo.controllers.Controller;
import bubolo.world.World;

/**
 * A tank controller for tanks controlled by network players.
 * 
 * @author BU CS673 - Clone Productions
 */
public class NetworkTankController implements Controller
{

	/**
	 * Constructs a network tank controller.
	 */
	public NetworkTankController()
	{
	}

	@Override
	public void update(World world)
	{
		// Do nothing. Dead reckoning is already handled by the tank's update method.
	}
}
