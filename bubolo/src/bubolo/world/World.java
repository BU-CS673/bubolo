package bubolo.world;

import java.util.List;
import java.util.UUID;

import bubolo.controllers.Controller;
import bubolo.controllers.ControllerFactory;
import bubolo.util.GameLogicException;
import bubolo.util.Nullable;
import bubolo.world.entity.Entity;

/**
 * Provides access to game entities. This is the primary interface between the Model and other
 * systems.
 * 
 * @author BU CS673 - Clone Productions
 */
public interface World
{
	/**
	 * Returns an entity from a user id. Throws a GameLogicException if the entity is not found.
	 * 
	 * @param id
	 *            the entity's unique id.
	 * @return the requested entity.
	 * @throws GameLogicException
	 *             if the entity is not found.
	 */
	public Entity getEntity(UUID id) throws GameLogicException;

	/**
	 * Returns the list of all entities in the world. Ordering should not be assumed, and may change
	 * between calls.
	 * 
	 * @return the list of entities.
	 */
	public List<Entity> getEntities();

	/**
	 * Returns the list of all tanks in the world. Ordering should not be assumed, and may change
	 * between calls.
	 * 
	 * @return the list of tanks.
	 */
	public List<Entity> getTanks();

	/**
	 * Returns the list of all Spawn Locations in the world. Ordering should not be assumed, and may
	 * change between calls.
	 * 
	 * @return the list of Spawns.
	 */
	public List<Entity> getSpawns();

	/**
	 * Returns the list of all actors in the world. Ordering should not be assumed, and may change
	 * between calls.
	 * 
	 * @return the list of actors.
	 */
	public List<Entity> getActors();

	/**
	 * Returns the list of all actors in the world. Ordering should not be assumed, and may change
	 * between calls.
	 * 
	 * @return the list of effects.
	 */
	public List<Entity> getEffects();

	/**
	 * Performs the following actions:
	 * <ol>
	 * <li>A new Entity of the specified type is created.</li>
	 * <li>The new Entity is added to the World</li>
	 * <li>A new Sprite is created and added to the Sprites list.</li>
	 * <li>One or more Controllers are created and added to the Controllers list.</li>
	 * </ol>
	 * 
	 * @param c
	 *            the entity's class object. For example, to create a new Tank, call this method
	 *            using the following form: <code>World.addEntity(Tank.class).</code>
	 * @return reference to the new entity.
	 * @throws GameLogicException
	 *             if the entity cannot be instantiated, or if the UUID already belongs to an
	 *             entity.
	 */
	public <T extends Entity> T addEntity(Class<T> c) throws GameLogicException;

	/**
	 * @see World#addEntity(Class)
	 * @param c
	 *            the entity's class object. For example, to create a new Tank, call this method
	 *            using the following form: <code>World.addEntity(Tank.class).</code>
	 * @param id
	 *            the UUID that will be used for the entity.
	 * @return reference to the new entity.
	 * @throws GameLogicException
	 *             if the entity cannot be instantiated, or if the UUID already belongs to an
	 *             entity.
	 */
	public <T extends Entity> T addEntity(Class<T> c, @Nullable UUID id) throws GameLogicException;

	/**
	 * @see World#addEntity(Class)
	 * @param c
	 *            the entity's class object. For example, to create a new Tank, call this method
	 *            using the following form: <code>World.addEntity(Tank.class).</code>
	 * @param controllerFactory
	 *            an object that implements the ControllerFactory interface. This should be used to
	 *            override the default controller settings. In other words, use a controller factory
	 *            to set different controller(s) for an entity than the default.
	 * @return reference to the new entity. Note that the entity has already been added to the
	 *         World.
	 * @throws GameLogicException
	 *             if the entity cannot be instantiated, or if the UUID already belongs to an
	 *             entity.
	 */
	public <T extends Entity> T addEntity(Class<T> c, @Nullable ControllerFactory controllerFactory)
			throws GameLogicException;

	/**
	 * @see World#addEntity(Class)
	 * @param c
	 *            the entity's class object. For example, to create a new Tank, call this method
	 *            using the following form: <code>World.addEntity(Tank.class).</code>
	 * @param id
	 *            the UUID that will be used for the entity.
	 * @param controllerFactory
	 *            an object that implements the ControllerFactory interface. This should be used to
	 *            override the default controller settings. In other words, use a controller factory
	 *            to set different controller(s) for an entity than the default.
	 * @return reference to the new entity.
	 * @throws GameLogicException
	 *             if the entity cannot be instantiated, or if the UUID already belongs to an
	 *             entity.
	 */
	public <T extends Entity> T addEntity(Class<T> c, @Nullable UUID id, 
			@Nullable ControllerFactory controllerFactory)
			throws GameLogicException;

	/**
	 * Removes an entity from the world. After this method is called, the specified entity will no
	 * longer be drawn or updated.
	 * 
	 * @param e
	 *            the entity to remove.
	 */
	public void removeEntity(Entity e);

	/**
	 * Removes an entity from the world. After this method is called, the specified entity will no
	 * longer be drawn or updated. Throws a GameLogicException if the entity is not found.
	 * 
	 * @param id
	 *            the unique id of the entity to remove.
	 * @throws GameLogicException
	 *             if the entity is not found.
	 */
	public void removeEntity(UUID id) throws GameLogicException;

	/**
	 * Returns the width of the game map.
	 * 
	 * @return the width of the game map.
	 */
	public int getMapWidth();

	/**
	 * Returns the height of the game map.
	 * 
	 * @return the width of the game map.
	 */
	public int getMapHeight();

	/**
	 * Sets the world's height.
	 * 
	 * @param height
	 *            the world's height.
	 */
	public void setMapHeight(int height);

	/**
	 * Sets the world's width.
	 * 
	 * @param width
	 *            the world's width.
	 */
	public void setMapWidth(int width);

	/**
	 * Updates the game world. Must be called once per game tick.
	 */
	public void update();

	/**
	 * Returns a 2d Tile Array representation of stationary objects in the world
	 * 
	 * @return the 2d Tile Array representing the stationary objects in the world
	 */
	public Tile[][] getMapTiles();

	/**
	 * Allows the setting of a 2d Tile Array representation of stationary objects in the world
	 * 
	 * @param mapTiles
	 *            is the representation to set the MapTiles field to
	 */
	public void setMapTiles(Tile[][] mapTiles);

	/**
	 * Adds a controller of the specified type to the world.
	 * 
	 * @param controllerType
	 *            the type of the controller to add.
	 */
	public void addController(Class<? extends Controller> controllerType);

	/**
	 * Removes a controller of the specified type to the world.
	 * 
	 * @param controllerType
	 *            the type of the controller to remove.
	 */
	public void removeController(Class<? extends Controller> controllerType);
	
	/**
	 * Returns the world controller count.
	 * @return the world controller count.
	 */
	public int getControllerCount();
}
