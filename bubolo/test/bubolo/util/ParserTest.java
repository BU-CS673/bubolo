package bubolo.util;

import static org.junit.Assert.*;
import org.junit.Test;
import bubolo.util.Parser;

public class ParserTest
{

	@Test
	public void getInstanceTest()
	{
		assertNotNull(Parser.getInstance());
	}
}

