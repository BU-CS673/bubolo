package bubolo.controllers;

import java.io.Serializable;

import bubolo.world.entity.Entity;

/**
 * Interface for controller factories, which allow the controllers that will be
 * used with an entity to be defined. If a controller factory is not used, the
 * default controllers for the entity will be used.
 * @author BU CS673 - Clone Productions
 */
public interface ControllerFactory extends Serializable
{
	/**
	 * The <code>create</code> method is called once, when the entity is created.
	 * This method should be used to add controllers that reference the entity.
	 * @param entity the new entity.
	 */
	public void create(Entity entity);
}
