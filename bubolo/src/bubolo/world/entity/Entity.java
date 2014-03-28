package bubolo.world.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;





import com.badlogic.gdx.math.Intersector;


import com.badlogic.gdx.math.Polygon;

import bubolo.controllers.Controller;
import bubolo.graphics.Drawable;
import bubolo.world.World;

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
	private Polygon bounds;
	
	// The list of controllers attached to this Entity.
	private List<Controller> controllers;
	
	// true if this entity should be removed from the game, or false otherwise. This is
	// used by sprites.
	private boolean disposed;

	/**
	 * Construct a new Entity with a random UUID.
	 */
	public Entity()
	{
		this(UUID.randomUUID());
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
	 * @param rot
	 *            is the initial rotation in radians.
	 * @return a reference to this Entity.
	 */
	public Entity setParams(float x, float y, float rot)
	{
		setX(x);
		setY(y);
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
	 * Updates the state of this Entity. Must be called once per game tick to maintain
	 * the Entity's state.
	 * @param world reference to the World
	 */
	public abstract void update(World world);

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
	 * Returns the center x position.
	 * @return the center x position.
	 */
	public float getCenterX()
	{
		return (xPos + (width / 2.f));
	}
	
	/**
	 * Returns the center y position.
	 * @return the center y position.
	 */
	public float getCenterY()
	{
		return (yPos + (height / 2.f));
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
	 * Adds a controller to this Entity.
	 * @param c the controller to add.
	 */
	public void addController(Controller c)
	{
		if (controllers == null)
		{
			controllers = new ArrayList<Controller>();
		}
		controllers.add(c);
	}
	
	/**
	 * Updates all attached controllers.
	 * @param world reference to the World.
	 */
	protected void updateControllers(World world)
	{
		if (controllers != null)
		{
			for (Controller c : controllers)
			{
				c.update(world);
			}
		}
	}
	
	/**
	 * Returns the number of controllers attached to this Entity.
	 * @return the number of controllers attached to this Entity.
	 */
	public int getControllerCount()
	{
		return (controllers == null) ? 0 : controllers.size();
	}
	
	/**
	 * Returns true if the entity should be removed from the game. This is needed
	 * by the graphics system.
	 * 
	 * @return true if the entity should be removed from the game.
	 */
	public final boolean isDisposed()
	{
		return disposed;
	}
	
	/**
	 * This method must be called when the entity should be removed from the game.
	 */
	public final void dispose()
	{
		disposed = true;
	}
}
