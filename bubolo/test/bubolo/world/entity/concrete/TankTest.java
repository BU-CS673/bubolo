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
import bubolo.world.entity.base.Entity;
import bubolo.world.entity.base.EntityTestCase;
import bubolo.world.entity.base.MockEntity;

public class TankTest
{
	static Tank tank;

	/**
	 * Constructs a Tank object and sets the default parameters.
	 */
	@BeforeClass
	public static void setup()
	{
		tank = new Tank(new MockSprite<Tank>());
		EntityTestCase.setTestParams(tank);
	}

	@Test
	public void Tank()
	{
		assert (true);
	}
}
