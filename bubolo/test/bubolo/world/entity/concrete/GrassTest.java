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
import bubolo.world.entity.base.EntityTestCase;
import bubolo.world.entity.base.MockEntity;

public class GrassTest
{
	static Grass grass;

	/**
	 * Constructs a Grass object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		grass = new Grass(new MockSprite<Grass>());
		EntityTestCase.setTestParams(grass);
	}

	@Test
	public void Grass()
	{
		assert (true);
	}
}
