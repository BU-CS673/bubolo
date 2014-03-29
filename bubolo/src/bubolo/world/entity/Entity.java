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
 * Base class for game objects. Anything that is part of the game logic should inherit from this
 * class.
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
	private Polygon bounds = new Polygon();
	public boolean intersecting = false;

	// The list of controllers attached to this Entity.
	private List<Controller> controllers;

	// true if this entity should be removed from the game, or false otherwise. This is
	// used by sprites.
	private boolean disposed;

	/**
	 * Construct a new Entity with the specified UUID.
	 * 
	 * @param newID
	 *            is the existing UUID to be assigned to the new Entity.
	 */
	public Entity(UUID newID)
	{
		updateBounds();
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
		updateBounds();
		return this;
	}

	protected boolean intersectsEntity(Entity e)
	{
		updateBounds();
		e.updateBounds();
		if (Intersector.overlapConvexPolygons(bounds, e.getBounds()))
		{
			e.intersecting = true;

			return true;
		}
		else
		{
			e.intersecting = false;
			return false;
		}
	}

	public List<Entity> getIntersectingEntities(World w)
	{
		ArrayList<Entity> intersects = new ArrayList<Entity>();
		List<Entity> allEntities = w.getEntities();
		for (int ii = 0; ii < allEntities.size(); ii++)
		{

			if (intersectsEntity(allEntities.get(ii)))
			{
				intersects.add(allEntities.get(ii));
			}
		}
		return intersects;
	}

	/**
	 * The Entity's unique id.
	 * 
	 * @return the Entity's unique id.
	 */
	public UUID getId()
	{
		return myID;
	}

	/**
	 * Sets the Entity's unique id.
	 * 
	 * @param id
	 *            the Entity's unique id.
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
	 * Updates the state of this Entity. Must be called once per game tick to maintain the Entity's
	 * state.
	 * 
	 * @param world
	 *            reference to the World
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
	 * Updates the bounding polygon for this Entity with its current position and rotation.
	 */
	public void updateBounds()
	{
		float x = getX();
		float y = getY();
		float w = getWidth();
		float h = getHeight();

		float[] corners = new float[] {
				w / 2f, h / 2f,
				w / 2f, -h / 2f,
				-w / 2f, h / 2f,
				-w / 2f, -h / 2f };
		bounds = new Polygon();
		bounds.setPosition(x, y);
		bounds.rotate((float)Math.toDegrees(getRotation() - Math.PI / 2));
		bounds.setVertices(corners);

	}

	/**
	 * Returns the bounding polygon for this Entity.
	 * 
	 * @return the Polygon representing the bounds for this Entity, for intersections/collisions.
	 * 
	 */
	public Polygon getBounds()
	{
		return bounds;
	}

	public Entity setBounds(Polygon p)
	{
		bounds = p;
		return this;
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
		if (newRotation < 0)
		{
			newRotation += 2 * Math.PI;
		}
		rotation = newRotation % (float)(2 * Math.PI);
		updateBounds();
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
		updateBounds();
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
		updateBounds();
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
	 * 
	 * @param c
	 *            the controller to add.
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
	 * 
	 * @param world
	 *            reference to the World.
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
	 * 
	 * @return the number of controllers attached to this Entity.
	 */
	public int getControllerCount()
	{
		return (controllers == null) ? 0 : controllers.size();
	}

	/**
	 * Returns true if the entity should be removed from the game. This is needed by the graphics
	 * system.
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
