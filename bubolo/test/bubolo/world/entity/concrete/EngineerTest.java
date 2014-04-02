package bubolo.world.entity.concrete;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.World;
import bubolo.world.entity.EntityTestCase;

import static org.mockito.Mockito.mock;

public class EngineerTest
{
	static Engineer engi;

	/**
	 * Constructs a Man object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		engi = new Engineer();
		EntityTestCase.setTestParams(engi);
	}
	
	@Test
	public void isBuilding(){
		engi.setBuilding(true);
		assertEquals("Engineer building state set correctly.", true, engi.isBuilding());
	}
	
	@Test
	public void isRunning(){
		engi.setRunning(true);
		assertEquals("Engineer running state set correctly.", true, engi.isRunning());
	}
	
	@Test
	public void update() {
		engi.update(mock(World.class));
	}
}
