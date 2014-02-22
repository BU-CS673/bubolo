package bubolo.integration;

import static org.junit.Assert.*;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import bubolo.graphics.LibGdxAppTester;
import bubolo.test.MockTank;
import bubolo.test.Sprint1Application;
import bubolo.world.Entities;
import bubolo.world.GameWorld;
import bubolo.world.World;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Tank;

public class Sprint1
{
	static Sprint1Application app = new Sprint1Application(300, 300);
	
	public static void main(String args[])
	{
			LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
			cfg.title = "test";
			cfg.width = 32*30;
			cfg.height = 32*30;
			new LwjglApplication(app, cfg);
	}

	
}
