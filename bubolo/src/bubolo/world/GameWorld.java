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
	
	private Tile[][] mapTiles;

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
		
		mapTiles = new Tile[worldMapWidth][worldMapHeight];
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
        
        entities.add(entity);
		entityMap.put(entity.getId(), entity);
        
        return entity;
    }

	@Override
	public void removeEntity(Entity e)
	{
		entities.remove(e);
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
		for (Entity e : entities)
		{
			// TODO: reference to World (this) must be passed to entities.
			e.update();
		}
	}
}
