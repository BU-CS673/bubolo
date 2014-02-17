package bubolo.controllers;

import bubolo.world.entity.Entity;
import bubolo.world.World;

/**
 * Interface for controller factories, which allow a 
 * @author BU CS673 - Clone Productions
 */
public interface ControllerFactory
{
	/**
	 * The <code>create</code> method is called once, when the entity is created.
	 * This method should be used to add controllers that reference the entity.
	 * @param entity the new entity.
	 * @param world reference to the World object.
	 */
	public void create(Entity entity, World world);
}
