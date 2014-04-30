/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.controllers.net;

import org.junit.Test;

import bubolo.world.World;

import static org.mockito.Mockito.mock;

/**
 * @author BU CS673 - Clone Productions
 */
public class NetworkEngineerControllerTest
{
	/**
	 * Test method for
	 * {@link bubolo.controllers.net.NetworkEngineerController#update(bubolo.world.World)}.
	 */
	@Test
	public void update()
	{
		NetworkEngineerController controller = new NetworkEngineerController();
		controller.update(mock(World.class));
	}
}
