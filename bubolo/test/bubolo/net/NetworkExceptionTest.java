package bubolo.net;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class NetworkExceptionTest
{

	@Test
	public void testNetworkExceptionString()
	{
		try
		{
			throw new NetworkException("");
		}
		catch (Exception e) {}
	}

	@Test
	public void testNetworkExceptionThrowable()
	{
		try
		{
			throw new NetworkException(mock(RuntimeException.class));
		}
		catch (Exception e) {}
	}

}
