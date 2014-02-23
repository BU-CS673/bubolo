package bubolo.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MockSocket extends Socket
{	
	@Override
	public OutputStream getOutputStream()
	{
		return System.out;
	}
	
	@Override
	public InputStream getInputStream()
	{
		return System.in;
	}
}
