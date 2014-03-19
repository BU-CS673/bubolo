package bubolo.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.common.base.Preconditions;

import bubolo.controllers.ControllerFactory;
import bubolo.controllers.Controllers;
import bubolo.graphics.Sprites;
import bubolo.util.GameLogicException;
import bubolo.world.entity.Entity;

/**
 * The concrete implementation of the World interface. GameWorld is the sole owner of
 * Entity objects.
 * 
 * @author BU CS673 - Clone Productions
 */
public class GameWorld implements World
{
	private List<Entity> entities = new ArrayList<Entity>();
	private Map<UUID, Entity> entityMap = new HashMap<UUID, Entity>();
	
	// The list of entities to remove. The entities array can't be modified while it
	// is being iterated over.
	private List<Entity> entitiesToRemove = new ArrayList<Entity>();
	// The list of entities to add. The entities array can't be modified while it is
	// being iterated over.
	private List<Entity> entitiesToAdd = new ArrayList<Entity>();
	
	private int worldMapWidth;
	private int worldMapHeight;

	/**
	 * Constructs the GameWorld object.
	 * 
	 * @param worldMapWidth
	 *            the width of the game world map.
	 * @param worldMapHeight
	 *            the height of the game world map.
	 */
	public GameWorld(int worldMapWidth, int worldMapHeight)
	{
		Preconditions.checkArgument(worldMapWidth > 0,
				"worldMapWidth must be greater than 0. worldMapWidth: %s", worldMapWidth);
		Preconditions.checkArgument(worldMapHeight > 0,
				"worldMapHeight must be greater than 0. worldMapHeight: %s", worldMapHeight);

		this.worldMapWidth = worldMapWidth;
		this.worldMapHeight = worldMapHeight;
	}

	@Override
	public Entity getEntity(UUID id) throws GameLogicException
	{
		Entity entity = entityMap.get(id);
		if (entity == null)
		{
			throw new GameLogicException(
					"The specified entity does not exist in the game world. Entity id: " + id);
		}
		return entity;
	}

	@Override
	public List<Entity> getEntities()
	{
		List<Entity> copyOfEntities = Collections.unmodifiableList(entities);
		return copyOfEntities;
	}
	
    @Override
	public <T extends Entity> T addEntity(Class<T> c) throws GameLogicException
    {
        return addEntity(c, UUID.randomUUID(), null);
    }
    
    @Override
	public <T extends Entity> T addEntity(Class<T> c, UUID id) throws GameLogicException
    {
        return addEntity(c, id, null);
    }

    @Override
	public <T extends Entity> T addEntity(Class<T> c, ControllerFactory controllerFactory) throws GameLogicException
    {
        return addEntity(c, UUID.randomUUID(), controllerFactory);
    }
   
    @Override
	public <T extends Entity> T addEntity(Class<T> c, UUID id, ControllerFactory controllerFactory) 
    		throws GameLogicException, IllegalStateException
    {
    	if (entityMap.containsKey(id))
		{
			throw new GameLogicException("The specified entity already exists. Entity id: " + id);
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
        
        Sprites.getInstance().createSprite(entity);
        Controllers.getInstance().createController(entity, controllerFactory);
        
        entitiesToAdd.add(entity);
		entityMap.put(entity.getId(), entity);
        
        return entity;
    }

	@Override
	public void removeEntity(Entity e)
	{
		e.dispose();
		entitiesToRemove.add(e);
		entityMap.remove(e.getId());
	}

	@Override
	public void removeEntity(UUID id) throws GameLogicException
	{
		removeEntity(entityMap.get(id));
	}

	@Override
	public int getMapWidth()
	{
		return worldMapWidth;
	}

	@Override
	public int getMapHeight()
	{
		return worldMapHeight;
	}

	@Override
	public void update()
	{
		// Update all entities.
		for (Entity e : entities)
		{
			e.update(this);
		}
		
		entities.removeAll(entitiesToRemove);
		entitiesToRemove.clear();
		
		entities.addAll(entitiesToAdd);
		entitiesToAdd.clear();
	}
}
