package bubolo.net;

import org.junit.Before;
import org.junit.Test;

import bubolo.world.World;

public class ConnectionWriterTest
{
	private ConnectionWriter cw;
	
	@Before
	public void setup()
	{
		cw = new ConnectionWriter(new MockSocket());
	}

	@Test
	public void addCommand()
	{
		cw.addCommand(new NetworkCommand() {
			@Override
			public void execute(World world)
			{
				// Do nothing. This is a mock object.
			}
		});
	}

	@Test
	public void testRun()
	{
		cw.run();
	}

}
