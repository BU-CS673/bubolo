/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net.command;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.graphics.LibGdxAppTester;
import bubolo.mock.MockTank;
import bubolo.mock.MockWorld;
import bubolo.net.Network;
import bubolo.net.NetworkCommand;
import bubolo.net.NetworkSystem;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Tank;

/**
 * @author BU CS673 - Clone Productions
 */
public class NetworkCommandTest
{
	@BeforeClass
	public static void setupClass()
	{
		LibGdxAppTester.createApp();
	}

	@Test
	public void testCreateEntityCommand()
	{
		NetworkCommand c = new CreateEntity(Grass.class, UUID.randomUUID(), 0, 0, 0);
		c.execute(new MockWorld());
	}
	
	@Test
	public void createEntitygetId()
	{
		UUID id = UUID.randomUUID();
		NetworkCommand c = new CreateEntity(Grass.class, id, 0, 0, 0);
		assertEquals(id, ((CreateEntity)c).getId());
	}
	
	@Test
	public void testCreateTankCommand()
	{
		NetworkCommand c = new CreateTank(new MockTank());
		c.execute(new MockWorld());
	}
	
	@Test
	public void testMoveEntity()
	{
		Grass grass = new Grass();
		MockWorld world = new MockWorld();
		world.add(grass);
		
		NetworkCommand c = new MoveEntity(grass);
		c.execute(world);
	}

}
