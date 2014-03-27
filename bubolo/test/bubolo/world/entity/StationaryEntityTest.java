package bubolo.world.entity;

import static org.junit.Assert.*;

import org.junit.Test;

import bubolo.world.World;

import static org.mockito.Mockito.mock;

public class StationaryEntityTest
{

	@Test
	public void update()
	{
		StationaryEntity mock = new MockStationaryEntity();
		mock.update(mock(World.class));
	}

}
