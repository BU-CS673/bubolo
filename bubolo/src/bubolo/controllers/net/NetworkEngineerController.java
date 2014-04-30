/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.controllers.net;

import bubolo.controllers.Controller;
import bubolo.world.World;

/**
 * An engineer controller for engineers controlled by network players.
 * 
 * @author BU CS673 - Clone Productions
 */
public class NetworkEngineerController implements Controller
{

	/**
	 * Constructs a network tank controller.
	 */
	public NetworkEngineerController()
	{
	}

	@Override
	public void update(World world)
	{
		// Do nothing. Everything is already handled by the engineer's update method.
	}
}
