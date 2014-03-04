package bubolo.world.entity;

import java.io.Serializable;
import java.util.UUID;

import bubolo.graphics.Drawable;

/**
 * Base class for game objects. Anything that is part of the game logic should inherit
 * from this class.
 * 
 * @author BU CS673 - Clone Productions
 */
public abstract class Entity implements Serializable, Drawable
{
	// Used when serializing and deserializing.
	private static final long serialVersionUID = -7558368147503376322L;
	private UUID myID;
	private int height; // height of this Entity in pixels
	private int width; // width of this Entity in pixels
	private float xPos;
	private float yPos;
	private float rotation; // rotation of this Entity in radians
	
	// true if this entity should be removed from the game, or false otherwise. This is
	// used by sprites.
	private boolean destroyed;

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
	 * Set the basic parameters for an Entity. Intended to be used after construction.
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
	 * @return a reference to this Entity.
	 */
	public Entity setParams(float x, float y, int w, int h, float rot)
	{
		setX(x);
		setY(y);
		setWidth(w);
		setHeight(h);
		setRotation(rot);
		return this;
	}

	/**
	 * The Entity's unique id.
	 * @return the Entity's unique id.
	 */
	public UUID getId()
	{
		return myID;
	}
	
	/**
	 * Sets the Entity's unique id.
	 * @param id the Entity's unique id.
	 */
	public void setId(UUID id)
	{
		this.myID = id;
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
	public void update()
	{
		// TODO: Implement update functionality for entities and subclasses!
	}

	@Override
	public float getRotation()
	{
		return rotation;
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

	/**
	 * Sets this Entity's x position.
	 * 
	 * @param x
	 *            is the desired x position in world coordinates.
	 * @return this Entity.
	 */

	public Entity setX(float x)
	{
		xPos = x;
		return this;
	}

	/**
	 * Set this Entity's y position.
	 * 
	 * @param y
	 *            is the desired y position in world coordinates.
	 * @return this Entity.
	 */
	public Entity setY(float y)
	{
		yPos = y;
		return this;
	}

	/**
	 * Set this Entity's width.
	 * 
	 * @param size
	 *            is the desired Entity width in world coordinates.
	 * @return this Entity.
	 */
	public Entity setWidth(int size)
	{
		width = size;
		return this;
	}

	/**
	 * Set this Entity's height.
	 * 
	 * @param size
	 *            is the desired Entity height in world coordinates.
	 * @return this Entity.
	 */
	public Entity setHeight(int size)
	{
		height = size;
		return this;
	}
	
	/**
	 * Returns true if the entity should be removed from the game. This is needed
	 * by the graphics system.
	 * 
	 * @return true if the entity should be removed from the game.
	 */
	public boolean isDestroyed()
	{
		return destroyed;
	}
	
	/**
	 * This method must be called when the entity should be removed from the game.
	 */
	protected void destroy()
	{
		destroyed = true;
	}
}
