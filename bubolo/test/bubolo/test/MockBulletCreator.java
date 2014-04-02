package bubolo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import bubolo.controllers.ControllerFactory;
import bubolo.util.GameLogicException;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.MockEntity;
import bubolo.world.entity.concrete.Bullet;

import static org.mockito.Mockito.mock;

/**
 * Mock class used for testing components that need a world implementation
 * (Which is not available at the time that this was implemented).
 * @author BU CS673 - Clone Productions
 */
public class MockBulletCreator extends MockWorld
{
	@Override
	public <T extends Entity> T addEntity(Class<T> c) throws GameLogicException
	{
		return (T) new Bullet();
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c, UUID id) throws GameLogicException
	{
		return (T) new Bullet();
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c, ControllerFactory controllerFactory)
			throws GameLogicException
	{
		return (T) new Bullet();
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c, UUID id, ControllerFactory controllerFactory)
			throws GameLogicException
	{
		return (T) new Bullet();
	}

}
