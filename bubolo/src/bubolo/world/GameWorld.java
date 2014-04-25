package bubolo.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Preconditions.checkArgument;
import bubolo.controllers.Controller;
import bubolo.controllers.ControllerFactory;
import bubolo.controllers.Controllers;
import bubolo.controllers.ai.AITreeController;
import bubolo.graphics.Sprites;
import bubolo.util.GameLogicException;
import bubolo.world.entity.Actor;
import bubolo.world.entity.Effect;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Spawn;
import bubolo.world.entity.concrete.Tank;

/**
 * The concrete implementation of the World interface. GameWorld is the sole owner of Entity
 * objects.
 * 
 * @author BU CS673 - Clone Productions
 */
public class GameWorld implements World
{
	private List<Entity> entities = new ArrayList<Entity>();
	private Map<UUID, Entity> entityMap = new HashMap<UUID, Entity>();

	private Tile[][] mapTiles = null;

	// The list of entities to remove. The entities array can't be modified while it
	// is being iterated over.
	private List<Entity> entitiesToRemove = new ArrayList<Entity>();

	// The list of entities to add. The entities array can't be modified while it is
	// being iterated over.
	private List<Entity> entitiesToAdd = new ArrayList<Entity>();

	// the list of Tanks that exist in the world
	private List<Entity> tanks = new ArrayList<Entity>();

	// list of world controllers
	private List<Controller> worldControllers = new ArrayList<Controller>();

	// the list of all Effects that currently exist in the world
	private List<Entity> effects = new ArrayList<Entity>();

	// the list of all Actors which currently exist in the world
	private List<Entity> actors = new ArrayList<Entity>();

	// the list of all Spawn Locations currently in the world
	private List<Entity> spawns = new ArrayList<Entity>();

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
		this.worldMapWidth = worldMapWidth;
		this.worldMapHeight = worldMapHeight;

		addController(AITreeController.class);
	}

	/**
	 * Constructs a default game world. This is intended for use by the network. The map's height
	 * and width must be set before calling the <code>update</code> method.
	 */
	public GameWorld()
	{
		this(0, 0);
	}

	@Override
	public void setMapHeight(int height)
	{
		checkArgument(height > 0, "height parameter must be greater than zero: %s", height);
		worldMapHeight = height;
	}

	@Override
	public void setMapWidth(int width)
	{
		checkArgument(width > 0, "width parameter must be greater than zero: %s", width);
		worldMapWidth = width;
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
	public <T extends Entity> T addEntity(Class<T> c, ControllerFactory controllerFactory)
			throws GameLogicException
	{
		return addEntity(c, UUID.randomUUID(), controllerFactory);
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c, UUID id, ControllerFactory controllerFactory)
			throws GameLogicException, IllegalStateException
	{
		if (entityMap.containsKey(id))
		{
			throw new GameLogicException("The specified entity already exists. Entity id: " + id +
					". Entity type: " + entityMap.get(id).getClass().getName());
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

		if (entity instanceof Tank)
		{
			tanks.add(entity);
		}

		if (entity instanceof Effect)
		{
			effects.add(entity);
		}

		if (entity instanceof Actor)
		{
			actors.add(entity);
		}

		if (entity instanceof Spawn)
		{
			spawns.add(entity);
		}

		entitiesToAdd.add(entity);
		entityMap.put(entity.getId(), entity);

		return entity;
	}

	@Override
	public void removeEntity(Entity e)
	{
		e.dispose();
		entityMap.remove(e.getId());

		if (e instanceof Tank)
		{
			tanks.remove(e);
		}

		if (e instanceof Actor)
		{
			actors.remove(e);
		}

		if (e instanceof Effect)
		{
			effects.remove(e);
		}

		if (e instanceof Spawn)
		{
			spawns.remove(e);
		}
	}

	@Override
	public List<Entity> getActors()
	{
		return actors;
	}

	@Override
	public List<Entity> getEffects()
	{
		return effects;
	}

	@Override
	public void removeEntity(UUID id) throws GameLogicException
	{
		removeEntity(entityMap.get(id));
	}

	@Override
	public Tile[][] getMapTiles()
	{
		return mapTiles;
	}

	@Override
	public void setMapTiles(Tile[][] mapTiles)
	{
		this.mapTiles = mapTiles;
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
		// Update all world controllers
		for (Controller c : worldControllers)
		{
			c.update(this);
		}

		checkState(worldMapWidth > 0,
				"worldMapWidth must be greater than 0. worldMapWidth: %s", worldMapWidth);
		checkState(worldMapHeight > 0,
				"worldMapHeight must be greater than 0. worldMapHeight: %s", worldMapHeight);

		// Update all entities.
		for (Entity e : entities)
		{
			e.update(this);
			if (e.isDisposed())
			{
				entitiesToRemove.add(e);
			}
		}

		entities.removeAll(entitiesToRemove);
		entitiesToRemove.clear();

		entities.addAll(entitiesToAdd);
		entitiesToAdd.clear();
	}

	@Override
	public List<Entity> getTanks()
	{
		List<Entity> copyOfTanks = Collections.unmodifiableList(tanks);
		return copyOfTanks;
	}

	@Override
	public List<Entity> getSpawns()
	{
		List<Entity> copyOfSpawns = Collections.unmodifiableList(spawns);
		return copyOfSpawns;
	}
	
	@Override
	public void addController(Class<? extends Controller> controllerType)
	{
		for (Controller c : worldControllers)
		{
			if (c.getClass() == controllerType)
			{
				return;
			}
		}
		
		try
		{
			worldControllers.add(controllerType.newInstance());
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			throw new GameLogicException(e);
		}
	}
	
	@Override
	public void removeController(Class<? extends Controller> controllerType)
	{
		for (Controller c : worldControllers)
		{
			if (c.getClass() == controllerType)
			{
				worldControllers.remove(c);
				return;
			}
		}
	}
	
	@Override
	public int getControllerCount()
	{
		return worldControllers.size();
	}
}
