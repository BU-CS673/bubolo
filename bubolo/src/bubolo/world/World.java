package bubolo.world;

import java.util.List;
import java.util.UUID;

import bubolo.util.GameLogicException;

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
	 * Adds an entity to the world.
	 * @param e the entity to add.
	 * @throws GameLogicException if the entity already exists in the game world.
	 */
	public void addEntity(Entity e) throws GameLogicException;
	
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
}
