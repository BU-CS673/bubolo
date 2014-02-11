package bubolo.world;

import java.io.Serializable;
import java.util.UUID;

import bubolo.graphics.Drawable;

/**
 * Base class for game objects. Anything that is part of the game logic should inherit
 * from this class.
 * 
 * @author BU673 - Clone Industries
 */
public abstract class Entity implements Serializable, Drawable
{
	// Used when serializing and deserializing.
	private static final long serialVersionUID = -7558368147503376322L;
	private UUID myID;
	private int height; // height of this entity in pixels
	private int width; // width of this entity in pixels
	private float xPos;
	private float yPos;
	private float rotation; // rotation of this entity in radians

	/**
	 * Construct a new Entity with a random UUID.
	 */
	public Entity()
	{
		myID = UUID.randomUUID();
	}

	/**
	 * Construct a new Entity with the specified UUID.
	 * 
	 * @param newID
	 *            is the existing UUID to be assigned to the new Entity.
	 */
	public Entity(UUID newID)
	{
		myID = newID;
	}

	/**
	 * Construct a new DummyEntity with the given initial parameters and the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Entity.
	 * @param x
	 *            is the initial x position in world coordinates.
	 * @param y
	 *            is the initial y position in world coordinates.
	 * @param w
	 *            is the initial width in world coordinates.
	 * @param h
	 *            is the initial height in world coordinates.
	 * @param rot
	 *            is the initial rotation in radians.
	 */
	public Entity(float x, float y, int w, int h, float rot, UUID id)
	{
		myID = id;
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		rotation = rot;
	}
	
	/**
	 * Construct a new Entity with the given initial parameters and a random UUID.
	 * 
	 * @param x
	 *            is the initial x position in world coordinates.
	 * @param y
	 *            is the initial y position in world coordinates.
	 * @param w
	 *            is the initial width in world coordinates.
	 * @param h
	 *            is the initial height in world coordinates.
	 * @param rot
	 *            is the initial rotation in radians.
	 */
	public Entity(float x, float y, int w, int h, float rot)
	{
		myID = UUID.randomUUID();
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		rotation = rot;
	}
	
	/**
	 * Tests to see if the initial parameters of this Entity are equivalent to those of another Entity.
	 * @param e is the Entity that this one should be compared against.
	 * @return true if the Entities match each other and false if they do not.
	 */
	public boolean matches(Entity e){
		if (!e.getId().equals(myID) || e.getX() != xPos || e.getY() != yPos || e.getWidth() != width || e.getHeight() != height || e.getRotation() != rotation)
		{
			return false;
		}
		else return true;
	}

	@Override
	public UUID getId()
	{
		return myID;
	}

	@Override
	public int getHeight()
	{
		return height;
	}

	@Override
	public int getWidth()
	{
		return width;
	}

	/**
	 * Updates the state of this Entity. Called multiple times per second to maintain
	 * current Entity state.
	 * 
	 */
	public abstract void update();

	@Override
	public float getRotation()
	{
		return rotation;
	}

	/**
	 * Set the rotation of this Entity.
	 * 
	 * @param newRotation
	 *            is the desired rotation state of this Entity in radians.
	 * @return this Entity, after the rotation action has been completed.
	 */
	public Entity setRotation(float newRotation)
	{
		rotation = newRotation;
		return this;
	}

	@Override
	public float getX()
	{
		return xPos;
	}

	@Override
	public float getY()
	{
		return yPos;
	}

	/**
	 * Sets this entity's x position.
	 * 
	 * @param x
	 *            is the desired x position in world coordinates.
	 */
	public void setX(float x)
	{
		xPos = x;
	}

	/**
	 * Set this entity's y position.
	 * 
	 * @param y
	 *            is the desired y position in world coordinates.
	 */
	public void setY(float y)
	{
		yPos = y;

	}

	/**
	 * Set this entity's width.
	 * 
	 * @param size
	 *            is the desired Entity width in world coordinates.
	 */
	public void setWidth(int size)
	{
		width = size;

	}

	/**
	 * Set this entity's height.
	 * 
	 * @param size
	 *            is the desired Entity height in world coordinates.
	 */
	public void setHeight(int size)
	{
		height = size;
	}
}
