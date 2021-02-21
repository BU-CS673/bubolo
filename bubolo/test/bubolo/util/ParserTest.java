package bubolo.util;

import static org.junit.Assert.*;
import org.junit.Test;

import bubolo.map.Parser;

public class ParserTest
{

	@Test
	public void getInstanceTest()
	{
		assertNotNull(Parser.getInstance());
	}
}

