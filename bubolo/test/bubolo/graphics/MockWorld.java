package bubolo.graphics;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import bubolo.util.GameLogicException;
import bubolo.world.World;
import bubolo.world.entity.base.Entity;

/**
 * Mock class used for testing components that need a world implementation
 * (Which is not available at the time that this was implemented).
 * @author BU CS673 - Clone Productions
 */
class MockWorld implements World
{

	@Override
	public Entity getEntity(UUID id) throws GameLogicException
	{
		throw new GameLogicException("MockWorld objects do not have any entities.");
	}

	@Override
	public List<Entity> getEntities()
	{
		return new ArrayList<Entity>();
	}

	@Override
	public void addEntity(Entity e)
	{
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

}
