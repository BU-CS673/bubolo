package bubolo.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

import bubolo.util.GameLogicException;

/**
 * The concrete implementation of the World interface. GameWorld is the sole
 * owner of Entity objects.
 * @author BU CS673 - Clone Productions
 */
public class GameWorld implements World
{
	private List<Entity> entities = new ArrayList<Entity>();
	private Map<UUID, Entity> entityMap = new WeakHashMap<UUID, Entity>();

	private int worldMapWidth;
	private int worldMapHeight;
	
	/**
	 * Constructs the GameWorld object.
	 * @param worldMapWidth the width of the game world map.
	 * @param worldMapHeight the height of the game world map.
	 */
	public GameWorld(int worldMapWidth, int worldMapHeight)
	{
		this.worldMapWidth = worldMapWidth;
		this.worldMapHeight = worldMapHeight;
	}
	
	@Override
	public Entity getEntity(UUID id) throws GameLogicException
	{
		Entity entity = entityMap.get(id);
		if (entity == null)
		{
			throw new GameLogicException("The specified entity does not exist in the game world. Entity id: " + id);
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
	public void addEntity(Entity e) throws GameLogicException
	{
		if (entityMap.containsKey(e.getId()))
		{
			throw new GameLogicException("The specified entity already exists. Entity id: " + e.getId());
		}
		
		entities.add(e);
		entityMap.put(e.getId(), e);
	}

	@Override
	public void removeEntity(Entity e)
	{
		entities.remove(e);
	}

	@Override
	public void removeEntity(UUID id) throws GameLogicException
	{
		entities.remove(entityMap.get(id));
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
}
