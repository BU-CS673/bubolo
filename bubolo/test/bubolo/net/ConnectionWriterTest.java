package bubolo.net;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;

import bubolo.world.World;
import static org.mockito.Mockito.*;

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
