package bubolo.world;

import java.util.UUID;

import bubolo.controllers.ControllerFactory;
import bubolo.controllers.Controllers;
import bubolo.graphics.Sprites;
import bubolo.util.GameLogicException;
import bubolo.world.entity.Entity;

/**
 * Contains static methods for adding creating new entities. The <code>initialize</code>
 * method must be called before any of the <code>create</code> overloads are called.
 * @author BU CS673 - Clone Productions
 */
public class Entities 
{
	private static World world;
	private static Sprites sprites;
	private static Controllers controllers;
	
	/**
	 * private constructor to prevent Entities from being instantiated.
	 */
	private Entities()
	{
	}
	
	/**
	 * Initializes the world. This must be called before any of the other methods
	 * are called.
	 * @param w reference to the world.
	 * @param s reference to Sprites system.
	 * @param c reference to controllers system.
	 */
	static void initialize(World w, Sprites s, Controllers c)
	{
		Entities.world = w;
		Entities.sprites = s;
		Entities.controllers = c;
	}
	
	/**
	 * Performs the following actions:
	 * <ol>
	 * <li>A new Entity of the specified type is created.</li>
	 * <li>The new Entity is added to the World, using World.add(Entity).</li>
	 * <li>A new Sprite is created and added to the Sprites list.</li>
	 * <li>One or more Controllers are created and added to the Controllers list.</li>
	 * </ol>
	 * @param c the entity's class object. For example, to create a new Tank, 
	 * call this method using the following form: <code>Entities.create(Tank.class).</code>
	 * @return reference to the new entity. Note that the entity has already been added
	 * to the World.
	 * @throws GameLogicException if the Entity cannot be instantiated.
	 * @throws IllegalStateException if Entities has not been initialized.
	 */
    public static <T extends Entity> T create(Class<T> c) throws GameLogicException, IllegalStateException
    {
        return Entities.create(c, UUID.randomUUID(), null);
    }
    
    /**
     * @see Entities#create(Class)
     * @param c @param c the entity's class object. For example, to create a new Tank, 
	 * call this method using the following form: <code>Entities.create(Tank.class).</code>
     * @param id the UUID that will be used for the entity.
     * @return reference to the new entity. Note that the entity has already been added
	 * to the World.
     * @throws GameLogicException if the Entity cannot be instantiated.
     * @throws IllegalStateException if Entities has not been initialized.
     */
    public static <T extends Entity> T create(Class<T> c, UUID id) throws GameLogicException, IllegalStateException
    {
        return Entities.create(c, id, null);
    }

    /**
     * @see Entities#create(Class)
     * @param c the entity's class object. For example, to create a new Tank, 
	 * call this method using the following form: <code>Entities.create(Tank.class).</code>
     * @param controllerFactory an object that implements the ControllerFactory 
     * interface. This should be used to override the default controller settings.
     * In other words, use a controller factory to set different controller(s) for
     * an entity than the default.
     * @return reference to the new entity. Note that the entity has already been added
	 * to the World.
     * @throws GameLogicException if the Entity cannot be instantiated.
     * @throws IllegalStateException if Entities has not been initialized.
     */
    public static <T extends Entity> T create(Class<T> c, ControllerFactory controllerFactory) throws GameLogicException, IllegalStateException
    {
        return Entities.create(c, UUID.randomUUID(), controllerFactory);
    }
   
    /**
     * @see Entities#create(Class)
     * @param c the entity's class object. For example, to create a new Tank, 
	 * call this method using the following form: <code>Entities.create(Tank.class).</code>
	 * @param id the UUID that will be used for the entity.
     * @param controllerFactory an object that implements the ControllerFactory 
     * interface. This should be used to override the default controller settings.
     * In other words, use a controller factory to set different controller(s) for
     * an entity than the default.
     * @return reference to the new entity. Note that the entity has already been added
	 * to the World.
     * @throws GameLogicException if the Entity cannot be instantiated.
     * @throws IllegalStateException if Entities has not been initialized.
     */
    public static <T extends Entity> T create(Class<T> c, UUID id, ControllerFactory controllerFactory) 
    		throws GameLogicException, IllegalStateException
    {
    	if (world == null)
    	{
    		throw new IllegalStateException("Entities.initialize must be called before any other method is called.");
    	}
    	
        T entity;
		try
		{
			entity = c.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			throw new GameLogicException(e.getMessage());
		}

        entity.setId(id);
        
        sprites.create(entity);
        controllers.create(entity, controllerFactory);
        world.addEntity(entity);
        
        return entity;
    }
}
