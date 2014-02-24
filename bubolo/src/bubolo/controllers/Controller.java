package bubolo.controllers;

import bubolo.world.Entity;
import bubolo.world.World;

/**
 * Controllers update (control) the model. In other words, entity behavior belongs
 * in controllers, not within the entities. See the <a href="https://github.com/BU-CS673/bubolo/wiki/Sprint-1:-Design">wiki page</a>
 * for more information.
 * @author BU CS673 - Clone Productions
 */
public interface Controller
{
	/**
	 * Allows the controller to perform its processing. Called once per game tick.
	 * @param Entity reference to the Model object.
	 */
	public void update(World world);
}
