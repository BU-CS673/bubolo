package bubolo.world;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import bubolo.world.Entity;

public class EntityTest
{
	static final UUID TARGET_UUID = UUID.fromString("5231b533-ba17-4787-98a3-f2df37de2aD7"); // random UUID string
	static final float TARGET_X = 26.7f;
	static final float TARGET_Y = 72.5f;
	static final float TARGET_ROT = (float)Math.PI/2;
	static final int TARGET_WIDTH = 50;
	static final int TARGET_HEIGHT = 100;
	static Entity ent;
	
	@BeforeClass
	public static void setup(){
		ent = new Tank(TARGET_UUID);
		ent.setX(TARGET_X);
		ent.setY(TARGET_Y);
		ent.setRotation(TARGET_ROT);
		ent.setWidth(TARGET_WIDTH);
		ent.setHeight(TARGET_HEIGHT);
	}
	
	@Test
	public void constructEntityUUID(){
		assertEquals("UUID of new Entity consistent with input UUID.", TARGET_UUID, ent.getId());
	}
	
	@Test 
	public void getX(){
		assertEquals("Entity x position matches target.", TARGET_X, ent.getX(), .0001);
	}
	
	@Test 
	public void getY(){
		assertEquals("Entity y position matches target.", TARGET_Y, ent.getY(), .0001);
	}
	
	@Test
	public void getRotation(){
		assertEquals("Entity rotation matches target.", TARGET_Y, ent.getY(), .0001);
	}
	
	@Test
	public void getWidth(){
		assertEquals("Entity xSize matches target.", TARGET_WIDTH, ent.getWidth());
	}
	
	@Test
	public void getHeight(){
		assertEquals("Entity xSize matches target.", TARGET_HEIGHT, ent.getHeight());
	}

}
