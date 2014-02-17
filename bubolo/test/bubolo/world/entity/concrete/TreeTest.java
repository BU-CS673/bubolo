package bubolo.world.entity.concrete;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.entity.EntityTestCase;

public class TreeTest
{
	static Tree tree;

	/**
	 * Constructs a Tree object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		tree = new Tree();
		EntityTestCase.setTestParams(tree);
	}

	@Test
	public void Tree()
	{
		assert (true);
	}

}
