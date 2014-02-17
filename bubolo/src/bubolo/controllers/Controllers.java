package bubolo.controllers;

import java.util.ArrayList;
import java.util.List;

import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Tank;
import bubolo.world.entity.concrete.Tree;

/**
 * Contains static methods for creating controllers.
 * @author BU CS673 - Clone Productions
 */
public class Controllers
{
	private List<Controller> controllers = new ArrayList<Controller>();
	private World world;
	
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
	 * Initializes the Controllers System.
	 * @param world reference to the world object.
	 */
	public void initialize(World w)
	{
		this.world = w;
	}
	
	/**
	 * Calls the <code>update</code> method on all controllers.
	 * @param world 
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
	public void create(Entity entity, ControllerFactory factory)
	{
		if (world == null)
		{
			throw new IllegalStateException(
					"initialize must be called before calling create(Entity, ControllerFactory");
		}

		if (factory != null)
		{
			factory.create(entity, world);
		}
	}
	
	/**
	 * @see Controllers#create(Entity, ControllerFactory).
	 * @param tank reference to the entity.
	 * @param factory reference to a controller factory, or null if the default
	 * behavior should be used.
	 */
	public void create(Tank tank, ControllerFactory factory)
	{
		// WARNING NOTE: The warnings will be eliminated once this method has been implemented.
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/**
	 * @see Controllers#create(Entity, ControllerFactory).
	 * @param tree reference to the entity.
	 * @param factory reference to a controller factory, or null if the default
	 * behavior should be used.
	 */
	public void create(Tree tree, ControllerFactory factory)
	{
		// WARNING NOTE: The warnings will be eliminated once this method has been implemented.
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
