/**
 *
 */

package bubolo.app;

import bubolo.GameApplication;
import bubolo.graphics.Graphics;
import bubolo.net.Network;
import bubolo.world.World;

/**
 * @author BU CS673 - Clone Productions
 */
public class RunGameState implements AppState
{
	private final GameApplication gameApplication;
	private final Graphics graphics;
	private final World world;
	private final Network network;

	public RunGameState(GameApplication application, Graphics graphics, 
			World world, Network network)
	{
		this.gameApplication = application;
		this.graphics = graphics;
		this.world = world;
		this.network = network;
	}

	@Override
	public void update()
	{
		graphics.draw(world);
		world.update();
		network.update(world);
	}
}
