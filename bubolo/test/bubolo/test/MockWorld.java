package bubolo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import bubolo.controllers.ControllerFactory;
import bubolo.util.GameLogicException;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c, UUID id) throws GameLogicException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c, ControllerFactory controllerFactory)
			throws GameLogicException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c, UUID id, ControllerFactory controllerFactory)
			throws GameLogicException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entity> getTanks() {
		// TODO Auto-generated method stub
		return null;
	}

}
