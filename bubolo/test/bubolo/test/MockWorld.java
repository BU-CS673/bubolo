package bubolo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import bubolo.controllers.Controller;
import bubolo.controllers.ControllerFactory;
import bubolo.util.GameLogicException;
import bubolo.world.Tile;
import bubolo.world.World;
import bubolo.world.entity.Entity;

/**
 * Mock class used for testing components that need a world implementation
 * (Which was not available at the time that this was implemented).
 * @author BU CS673 - Clone Productions
 */
public class MockWorld implements World
{
	private List<Entity> entities = new ArrayList<Entity>();
	private Map<UUID, Entity> entityMap = new HashMap<UUID, Entity>();
	
	/**
	 * Adds the entity to the MockWorld.
	 * @param e the entity to add.
	 */
	public void add(Entity e)
	{
		entities.add(e);
		entityMap.put(e.getId(), e);
	}

	@Override
	public Entity getEntity(UUID id) throws GameLogicException
	{
		return entityMap.get(id);
	}

	@Override
	public List<Entity> getEntities()
	{
		return entities;
	}

	@Override
	public void removeEntity(Entity e)
	{
	}

	@Override
	public void removeEntity(UUID id) throws GameLogicException
	{
		removeEntity(getEntity(id));
	}

	@Override
	public int getMapWidth()
	{
		return 0;
	}

	@Override
	public int getMapHeight()
	{
		return 0;
	}

	@Override
	public void update()
	{
		// do nothing.
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c) throws GameLogicException
	{
		return addEntity(c, null, null);
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c, UUID id) throws GameLogicException
	{
		return addEntity(c, id, null);
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c, ControllerFactory controllerFactory)
			throws GameLogicException
	{
		return addEntity(c, null, controllerFactory);
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c, UUID id, ControllerFactory controllerFactory)
			throws GameLogicException
	{
		T entity = null;
		try
		{
			entity = c.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			throw new GameLogicException(e);
		}
		entity.setId(id);
		return entity;
	}

	@Override

	public List<Entity> getTanks() 
	{
		return null;
	}

	@Override
	public void setMapTiles(Tile[][] mapTiles)
	{
	}
	
	@Override
	public Tile[][] getMapTiles()
	{
		return null;
	}

	@Override
	public List<Entity> getActors()
	{
		return null;
	}

	@Override
	public List<Entity> getEffects()
	{
		return null;
	}

	@Override
	public void setMapHeight(int height)
	{
	}

	@Override
	public void setMapWidth(int width)
	{
	}

	@Override
	public List<Entity> getSpawns() {
		// do nothing
		return null;
	}

	@Override
	public void addController(Class<? extends Controller> controllerType)
	{	
	}

	@Override
	public void removeController(Class<? extends Controller> controllerType)
	{
	}
}
