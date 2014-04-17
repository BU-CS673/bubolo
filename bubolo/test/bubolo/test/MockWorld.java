package bubolo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import bubolo.controllers.ControllerFactory;
import bubolo.util.GameLogicException;
import bubolo.world.Tile;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.MockEntity;

/**
 * Mock class used for testing components that need a world implementation
 * (Which is not available at the time that this was implemented).
 * @author BU CS673 - Clone Productions
 */
public class MockWorld implements World
{
	private List<Entity> entities = new ArrayList<Entity>();
	private Map<UUID, Entity> entityMap = new HashMap<UUID, Entity>();
	private Tile[][] mapTiles;
	
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
		// do nothing...
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c) throws GameLogicException
	{
		// do nothing
		return null;
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c, UUID id) throws GameLogicException
	{
		// do nothing
		return null;
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c, ControllerFactory controllerFactory)
			throws GameLogicException
	{
		// do nothing
		return null;
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c, UUID id, ControllerFactory controllerFactory)
			throws GameLogicException
	{
		// do nothing
		return null;
	}

	@Override

	public List<Entity> getTanks() 
	{
		// do nothing
		return null;
	}

	@Override
	public void setMapTiles(Tile[][] givenTiles)
	{
		// do nothing		
	}
	
	@Override
	public Tile[][] getMapTiles()
	{
		//do nothing
		return null;
	}

	@Override
	public List<Entity> getActors()
	{
		// do nothing
		return null;
	}

	@Override
	public List<Entity> getEffects()
	{
		// do nothing
		return null;
	}

	@Override
	public void setMapHeight(int height)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMapWidth(int width)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Entity> getSpawns() 
	{
		// do nothing
		return null;
	}
	
}
