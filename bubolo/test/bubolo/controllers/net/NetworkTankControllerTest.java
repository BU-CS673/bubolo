/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.controllers.net;

import static org.junit.Assert.*;

import org.junit.Test;

import bubolo.world.World;

import static org.mockito.Mockito.mock;

/**
 * @author BU CS673 - Clone Productions
 */
public class NetworkTankControllerTest
{
	/**
	 * Test method for
	 * {@link bubolo.controllers.net.NetworkTankController#update(bubolo.world.World)}.
	 */
	@Test
	public void update()
	{
		NetworkTankController controller = new NetworkTankController();
		controller.update(mock(World.class));
	}
}
