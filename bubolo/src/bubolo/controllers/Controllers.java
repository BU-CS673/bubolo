package bubolo.controllers;

import java.util.ArrayList;
import java.util.List;

import bubolo.util.GameLogicException;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Road;
import bubolo.world.entity.concrete.Tank;
import bubolo.world.entity.concrete.Tree;

/**
 * Contains static methods for creating controllers.
 * @author BU CS673 - Clone Productions
 */
public class Controllers
{
	private List<Controller> controllers = new ArrayList<Controller>();
	
	/**
	 * Used by objects that don't have any controllers associated with them. It is
	 * a dummy method that returns immediately. It simplifies the creation of entities.
	 * @param entity reference to the entity.
	 * @param factory reference to a controller factory, or null if the default
	 * behavior should be used.
	 */
	public static void create(Entity entity, ControllerFactory factory)
	{
		// WARNING NOTE: The entity and factory parameters are not used with this overload. However,
		// the method needs to use the same interface as the other overloads, so accepting
		// these parameters is necessary.
		return;
	}
	
	public static void create(Tank tank, ControllerFactory factory)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public static void create(Tree tree, ControllerFactory factory)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public static void create(Road road, ControllerFactory factory)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public static void create(Grass grass, ControllerFactory factory)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
