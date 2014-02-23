package bubolo.world;

import java.util.List;
import java.util.UUID;

import bubolo.controllers.ControllerFactory;
import bubolo.util.GameLogicException;
import bubolo.world.entity.Entity;

/**
 * Provides access to game entities. This is the primary interface between 
 * the Model and other systems. 
 * @author BU CS673 - Clone Productions
 */
public interface World
{
	/**
	 * Returns an entity from a user id. Throws a GameLogicException if the 
	 * entity is not found.
	 * @param id the entity's unique id.
	 * @return the requested entity.
	 * @throws GameLogicException if the entity is not found.
	 */
	public Entity getEntity(UUID id) throws GameLogicException;
	
	/**
	 * Returns the list of all entities in the world. Ordering should not be 
	 * assumed, and may change between calls.
	 * @return the list of entities.
	 */
	public List<Entity> getEntities();
	
	/**
	 * Performs the following actions:
	 * <ol>
	 * <li>A new Entity of the specified type is created.</li>
	 * <li>The new Entity is added to the World</li>
	 * <li>A new Sprite is created and added to the Sprites list.</li>
	 * <li>One or more Controllers are created and added to the Controllers list.</li>
	 * </ol>
	 * @param c the entity's class object. For example, to create a new Tank, 
	 * call this method using the following form: <code>World.addEntity(Tank.class).</code>
	 * @return reference to the new entity.
	 * @throws GameLogicException if the entity cannot be instantiated, or if the 
	 * UUID already belongs to an entity.
	 */
    public <T extends Entity> T addEntity(Class<T> c) throws GameLogicException;
    
    /**
     * @see World#addEntity(Class)
     * @param c @param c the entity's class object. For example, to create a new Tank, 
	 * call this method using the following form: <code>World.addEntity(Tank.class).</code>
     * @param id the UUID that will be used for the entity.
     * @return reference to the new entity.
     * @throws GameLogicException if the entity cannot be instantiated, or if the 
	 * UUID already belongs to an entity.
     */
    public <T extends Entity> T addEntity(Class<T> c, UUID id) throws GameLogicException;

    /**
     * @see World#addEntity(Class)
     * @param c the entity's class object. For example, to create a new Tank, 
	 * call this method using the following form: <code>World.addEntity(Tank.class).</code>
     * @param controllerFactory an object that implements the ControllerFactory 
     * interface. This should be used to override the default controller settings.
     * In other words, use a controller factory to set different controller(s) for
     * an entity than the default.
     * @return reference to the new entity. Note that the entity has already been added
	 * to the World.
     * @throws GameLogicException if the entity cannot be instantiated, or if the 
	 * UUID already belongs to an entity.
     */
    public <T extends Entity> T addEntity(Class<T> c, ControllerFactory controllerFactory) throws GameLogicException;
   
    /**
     * @see World#addEntity(Class)
     * @param c the entity's class object. For example, to create a new Tank, 
	 * call this method using the following form: <code>World.addEntity(Tank.class).</code>
	 * @param id the UUID that will be used for the entity.
     * @param controllerFactory an object that implements the ControllerFactory 
     * interface. This should be used to override the default controller settings.
     * In other words, use a controller factory to set different controller(s) for
     * an entity than the default.
     * @return reference to the new entity.
     * @throws GameLogicException if the entity cannot be instantiated, or if the 
	 * UUID already belongs to an entity.
     */
    public <T extends Entity> T addEntity(Class<T> c, UUID id, ControllerFactory controllerFactory) throws GameLogicException;
	
	/**
	 * Removes an entity from the world. After this method is called, the 
	 * specified entity will no longer be drawn or updated.
	 * @param e the entity to remove.
	 */
	public void removeEntity(Entity e);
	
	/**
	 * Removes an entity from the world. After this method is called, the 
	 * specified entity will no longer be drawn or updated. Throws a 
	 * GameLogicException if the entity is not found.
	 * @param id the unique id of the entity to remove.
	 * @throws GameLogicException if the entity is not found.
	 */
	public void removeEntity(UUID id) throws GameLogicException;
	
	/**
	 * Returns the width of the game map.
	 * @return the width of the game map.
	 */
	public int getMapWidth();
	
	/**
	 * Returns the height of the game map.
	 * @return the width of the game map.
	 */
	public int getMapHeight();
	
	/**
	 * Updates the game world. Must be called once per game tick.
	 */
	public void update();
}
