package bubolo.world.entity.concrete;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import bubolo.graphics.Graphics;
import bubolo.graphics.LibGdxAppTester;
import bubolo.graphics.MockSprite;
import bubolo.world.entity.Entity;
import bubolo.world.entity.EntityTestCase;
import bubolo.world.entity.MockEntity;

public class TreeTest
{
	static Tree tree;

	/**
	 * Constructs a Tree object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		tree = new Tree(new MockSprite<Tree>());
		EntityTestCase.setTestParams(tree);
	}

	@Test
	public void Tree()
	{
		assert (true);
	}

}
