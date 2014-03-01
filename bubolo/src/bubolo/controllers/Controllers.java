package bubolo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bubolo.world.World;
import bubolo.world.entity.Entity;

/**
 * Contains static methods for creating controllers.
 * @author BU CS673 - Clone Productions
 */
public class Controllers
{
	private List<Controller> controllers = new ArrayList<Controller>();
	
	private Map<Class<? extends Entity>, ControllerFactory> defaultFactories;
	
	private static Controllers instance;
	
	/**
	 * Returns the instance of this singleton.
	 * @return the instance of this singleton.
	 */
	public static Controllers getInstance()
	{
		if (instance == null)
		{
			instance = new Controllers();
		}
		return instance;
	}
	
	/**
	 * Private constructor to prevent instantiation outside of getInstance().
	 */
	private Controllers()
	{
		defaultFactories = setDefaultControllerFactories();
	}
	
	/**
	 * Returns the number of controllers.
	 * @return the number of controllers.
	 */
	long getCount()
	{
		return controllers.size();
	}
	
	/**
	 * Calls the <code>update</code> method on all controllers.
	 * @param w the world object.
	 */
	public void update(World w)
	{
		for (Controller c : controllers)
		{
			c.update(w);
		}
	}
	
	/**
	 * Instantiates controllers for the specified entity. The optional ControllerFactory
	 * can be used to specify the exact controllers that will be created for the
	 * entity. Alternatively, passing a null reference will result in the creation
	 * of the default controllers for the entity.
	 * @param entity reference to the entity.
	 * @param factory reference to a controller factory, or null if the default
	 * behavior should be used.
	 */
	public void createController(Entity entity, ControllerFactory factory)
	{
		ControllerFactory controllerFactory = factory;
		if (controllerFactory == null)
		{
			controllerFactory = defaultFactories.get(entity.getClass());
		}
		
		if (controllerFactory != null)
		{
			controllerFactory.create(entity, this);
		}
	}
	
	/**
	 * Adds a controller to the list.
	 * @param controller the controller to add.
	 */
	void addController(Controller controller)
	{
		controllers.add(controller);
	}
	
	/**
	 * Creates a map that maps entity classes to default factories.
	 * @return reference to the ControllerFactory map.
	 */
	private static Map<Class<? extends Entity>, ControllerFactory> setDefaultControllerFactories()
	{
		Map<Class<? extends Entity>, ControllerFactory> factories = new HashMap<>();
		
		// TODO: No default factories exist yet. Add default factories here.
		
		return factories;
	}
}
