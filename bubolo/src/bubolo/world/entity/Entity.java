package bubolo.world.entity;

import java.io.Serializable;
import java.util.UUID;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bubolo.graphics.DrawLayer;
import bubolo.graphics.Drawable;
import bubolo.graphics.MockSprite;
import bubolo.graphics.Sprite;

/**
 * Base class for game objects. Anything that is part of the game logic should
 * inherit from this class.
 * 
 * @author BU CS673 - Clone Productions
 */
public abstract class Entity implements Serializable, Drawable {
	// Used when serializing and deserializing.
	private static final long serialVersionUID = -7558368147503376322L;
	private UUID myID;
	private int height; // height of this Entity in pixels
	private int width; // width of this Entity in pixels
	private float xPos;
	private float yPos;
	private float rotation; // rotation of this Entity in radians
	/**
	 * Each Entity's sprite must be created using Sprite.create(this) to ensure
	 * that the correct kind of Sprite is generated. This is transient to
	 * prevent sprite objects from being transferred during serialization. If
	 * the sprite is not initialized during the constructor of a subclass of
	 * Entity, a DefaultSprite is created so as to ensure that methods that
	 * interact with an Entity's sprite will not return null.
	 * 
	 */
	protected transient Sprite<?> sprite;

	/**
	 * Construct a new Entity with a random UUID.
	 */
	public Entity() {
		myID = UUID.randomUUID();
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer) {
		sprite.draw(batch, camera, layer);
	}

	@Override
	public UUID getSpriteId() {
		return sprite.getId();
	}

	/**
	 * Construct a new Entity with the specified UUID.
	 * 
	 * @param newID
	 *            is the existing UUID to be assigned to the new Entity.
	 */
	public Entity(UUID newID) {
		myID = newID;
	}

	/**
	 * Set the basic parameters for an Entity. Intended to be used after
	 * construction.
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
	public Entity setParams(float x, float y, int w, int h, float rot) {
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		rotation = rot;
		return this;
	}

	@Override
	public UUID getId() {
		return myID;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * Updates the state of this Entity. Called multiple times per second to
	 * maintain current Entity state.
	 * 
	 */
	public void update() {
		// TODO: Implement update functionality for entities and subclasses!
	}

	@Override
	public float getRotation() {
		return rotation;
	}

	@Override
	public float getX() {
		return xPos;
	}

	@Override
	public float getY() {
		return yPos;
	}

	/**
	 * Set the rotation of this Entity.
	 * 
	 * @param newRotation
	 *            is the desired rotation state of this Entity in radians.
	 * @return this Entity, after the rotation action has been completed.
	 */
	public Entity setRotation(float newRotation) {
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

	public Entity setX(float x) {
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
	public Entity setY(float y) {
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
	public Entity setWidth(int size) {
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
	public Entity setHeight(int size) {
		height = size;
		return this;
	}
}
