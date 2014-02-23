package bubolo.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MockSocket extends Socket
{
	class MockOutputStream extends OutputStream
	{
		@Override
		public void write(int arg0) throws IOException
		{	
		}
	}
	
	@Override
	public OutputStream getOutputStream()
	{
		return new MockOutputStream();
	}
}
