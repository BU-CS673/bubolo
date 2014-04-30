package bubolo.world.entity.concrete;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;

import bubolo.audio.Audio;
import bubolo.audio.Sfx;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.net.command.MoveTank;
import bubolo.net.command.NetTankSpeed;
import bubolo.util.TileUtil;
import bubolo.world.Damageable;
import bubolo.world.Tile;
import bubolo.world.World;
import bubolo.world.entity.Actor;
import bubolo.world.entity.Entity;
import bubolo.world.entity.StationaryElement;
import bubolo.world.entity.Terrain;

/**
 * The tank, which may be controlled by a local player, a networked player, or an AI bot.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Tank extends Actor implements Damageable
{
	/**
	 * UID of the owner of this Tank
	 */
	private UUID ownerUID;
	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 457933513574468829L;

	// Max speed in pixels per tick.
	private static final float maxSpeed = 4.f;

	/**
	 * Used to calculate the maxSpeed based upon the interaction with the intersected
	 * terrains
	 */
	private float modifiedMaxSpeed = maxSpeed;

	// The tank's current speed.
	private float speed = 0.f;

	// The rate of acceleration, in pixels per tick.
	private static final float accelerationRate = 0.01f;

	// The rate of deceleration, in pixels per tick.
	private static final float decelerationRate = 0.03f;

	// Specifies whether the tank accelerated this tick.
	private boolean accelerated;

	// Specifies whether the tank decelerated this tick.
	private boolean decelerated;

	// Specifies whether the tank is hidden in trees
	private boolean hidden;

	// The tank's rate of rotation per tick.
	private static final float rotationRate = 0.05f;

	// The reload speed of the tank's cannon, in milliseconds.
	private static final long cannonReloadSpeed = 500;

	// Boolean for whether this tank is currently alive
	private boolean isAlive = true;
	
	// The time that the tank will respawn.
	private long respawnTime;
	
	private static final long TANK_RESPAWN_TIME = 1000L;

	// Minimum amount of time between laying mines.
	private static final long MINE_RELOAD_SPEED = 500;

	// The last time that the cannon was fired. Populate this with
	// System.currentTimeMillis().
	private long cannonFireTime = 0;

	// The last time a mine was laid. Used to prevent multiple mines from being dropped.
	private long mineLayingTime = 0;

	private Polygon leftBumper = new Polygon();
	private Polygon rightBumper = new Polygon();
	private float bumperWidth = 4.0f;
	private float bumperHeight = 4.0f;

	/**
	 * The default amount to rotate the Tank by when a bumper collision is detected. Used
	 * to prevent getting 'stuck' on walls.
	 */
	private float rotationOffsetAmount = (float) Math.toRadians(1);

	/**
	 * The default amount to reposition the Tank by when a bumper collision is detected.
	 * Used to prevent getting 'stuck' on walls.
	 */
	private float positionOffsetAmount = 0.1f;

	/**
	 * Construct a new Tank with a random UUID.
	 */

	/**
	 * The health of the tank
	 */
	private int hitPoints;

	/**
	 * The amount of ammo of the tank
	 */

	public static final int TANK_MAX_HIT_POINTS = 100;

	private int ammoCount;

	/**
	 * The amount of the tree resource the tank has
	 */

	public static final int TANK_MAX_AMMO = 100;

	private int treeCount;

	/**
	 * The number of mines in the tank
	 */

	public static final int TANK_MAX_TREE_INVENTORY = 100;

	private int mineCount;

	/**
	 * The number of pillboxes in the tank
	 */

	public static final int TANK_MAX_MINE_COUNT = 10;



	private int pillboxCount;

	private Random randomGenerator = new Random();

	/**
	 * Constructor for the Tank object
	 */

	public Tank()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new Tank with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tank.
	 */
	public Tank(UUID id)
	{
		super(id);
		setWidth(20);
		setHeight(22);
		updateBounds();
		setSolid(true);
		hitPoints = TANK_MAX_HIT_POINTS;
		ammoCount = TANK_MAX_AMMO;
		treeCount = 0;
		pillboxCount = 0;
		mineCount = TANK_MAX_MINE_COUNT;
	}

	/**
	 * Returns the tank's speed.
	 * 
	 * @return the tank's speed.
	 */
	public float getSpeed()
	{
		return speed;
	}

	/**
	 * Sets the tank's speed. Intended for use with the network system.
	 * 
	 * @param newSpeed
	 *            a NetTankSpeed object that contains the tank's new speed.
	 */
	public void setSpeed(NetTankSpeed newSpeed)
	{
		if (newSpeed.getSpeed() > this.speed)
		{
			accelerated = true;
		}

		this.speed = newSpeed.getSpeed();
	}

	/**
	 * Accelerates the tank.
	 */
	public void accelerate()
	{
		if (speed > modifiedMaxSpeed)
		{
			// this.decelerate();
			speed = modifiedMaxSpeed;
		}

		else if (speed < modifiedMaxSpeed && !accelerated)
		{
			speed += accelerationRate;
			if (speed > modifiedMaxSpeed)
			{
				speed = modifiedMaxSpeed;
			}
			accelerated = true;
		}
	}

	/**
	 * Decelerates the tank.
	 */
	public void decelerate()
	{
		if (speed > modifiedMaxSpeed)
		{
			speed = modifiedMaxSpeed;
		}
		if (speed > 0 && !decelerated)
		{
			speed -= decelerationRate;
			if (speed < 0)
			{
				speed = 0;
			}
			decelerated = true;
		}
	}

	/**
	 * Rotates the tank clockwise.
	 */
	public void rotateRight()
	{
		setRotation(getRotation() + rotationRate);
	}

	/**
	 * Rotates the tank counter-clockwise.
	 */
	public void rotateLeft()
	{
		setRotation(getRotation() - rotationRate);
	}

	/**
	 * Returns true if the cannon is ready to fire.
	 * 
	 * @return true if the cannon is ready to fire.
	 */
	public boolean isCannonReady()
	{
		return (System.currentTimeMillis() - cannonFireTime > cannonReloadSpeed);
	}
	
	/**
	 * Returns true if the tank is alive. This is needed since the tank is reused on death, rather 
	 * than disposed.
	 * @return true if the tank is alive, or false otherwise.
	 */
	public boolean isAlive()
	{
		return isAlive;
	}

	/**
	 * Fires the tank's cannon, which adds a bullet to the world and initiates a cannon
	 * reload.
	 * 
	 * @param world
	 *            reference to the world.
	 * @param startX
	 *            the bullet's start x position.
	 * @param startY
	 *            the bullet's start y position.
	 * 
	 * @return bullet reference to the new bullet or null if the tank cannot fire.
	 */
	public Bullet fireCannon(World world, float startX, float startY)
	{
		if ((ammoCount > 0) && (cannonFireTime - System.currentTimeMillis() < 0))
		{
			cannonFireTime = System.currentTimeMillis();

			Bullet bullet = world.addEntity(Bullet.class);
			bullet.setParent(this);
			bullet.setX(startX).setY(startY);
			bullet.setRotation(getRotation());
			ammoCount--;

			return bullet;
		}

		else
		{
			return null;
		}
	}

	private Polygon lookAheadBounds()
	{
		Polygon lookAheadBounds = getBounds();

		float newX = (float) (getX() + Math.cos(getRotation()) * speed);
		float newY = (float) (getY() + Math.sin(getRotation()) * speed);

		lookAheadBounds.setPosition(newX, newY);
		return lookAheadBounds;
	}

	/**
	 * Checks to see whether this Tank is currently hidden (ex. by being in a clump of
	 * trees)
	 * 
	 * @return true if the Tank is hidden, false otherwise.
	 */
	public boolean isHidden()
	{
		return hidden || !isAlive;
	}

	/**
	 * Returns a list of all Entities that would overlap with this Tank if it was where it
	 * will be in one game tick, along its current trajectory.
	 */
	private List<Entity> getLookaheadEntities(World w)
	{
		ArrayList<Entity> intersects = new ArrayList<Entity>();
		List<Entity> localEntities = TileUtil.getLocalEntities(getX(), getY(), w);
		for (int ii = 0; ii < localEntities.size(); ii++)
		{
			if (localEntities.get(ii) != this)
			{
				if (overlapsEntity(localEntities.get(ii))
						|| Intersector.overlapConvexPolygons(lookAheadBounds(),
								localEntities.get(ii).getBounds()))
				{
					intersects.add(localEntities.get(ii));
				}
			}
		}
		return intersects;
	}

	/**
	 * Update the left and right bumpers to use current positioning and speed information.
	 */
	private void updateBumpers()
	{
		updateLeftBumper();
		updateRightBumper();
	}

	/**
	 * Updates the bounding polygon for this Entity with its current position and
	 * rotation.
	 */
	private void updateLeftBumper()
	{
		float newX = (float) (getX() + Math.cos(getRotation()) * (speed));
		float newY = (float) (getY() + Math.sin(getRotation()) * (speed));
		float w = getWidth();
		float h = getHeight();

		// Defines the corners of the left bumper as a 4x4 pixel box, placed at the
		// top-left edge of the tank, with its left edge along the left edge of the
		// tank and its topmost edge aligned with the front edge of the tank.
		float[] corners = new float[] { -w / 2f, h / 2f, -w / 2f + 4, h / 2f, -w / 2f, h / 2f - 4,
				-w / 2f + 4, h / 2f - 4 };
		leftBumper = new Polygon();
		leftBumper.setPosition(newX, newY);
		leftBumper.setOrigin(0, 0);
		leftBumper.setVertices(corners);
		leftBumper.rotate((float) Math.toDegrees(getRotation() - Math.PI / 2));
	}

	/**
	 * Updates the bounding polygon for this Entity with its current position and
	 * rotation.
	 */
	private void updateRightBumper()
	{
		float newX = (float) (getX() + Math.cos(getRotation()) * (speed));
		float newY = (float) (getY() + Math.sin(getRotation()) * (speed));
		float w = getWidth();
		float h = getHeight();

		// Defines the corners of the right bumper as a 4x4 pixel box, placed at the
		// top-right edge of the tank, with its left edge along the left edge of the
		// tank and its topmost edge aligned with the front edge of the tank.
		float[] corners = new float[] { w / 2f, h / 2f, w / 2f - bumperWidth, h / 2f, w / 2f,
				h / 2f - bumperHeight, w / 2f - bumperWidth, h / 2f - bumperHeight };
		rightBumper = new Polygon();
		rightBumper.setPosition(newX, newY);
		rightBumper.setOrigin(0, 0);
		rightBumper.setVertices(corners);
		rightBumper.rotate((float) Math.toDegrees(getRotation() - Math.PI / 2));
	}

	/**
	 * Checks to see if an Entity overlaps with this Tank's left bumper.
	 */
	private boolean hitLeftBumper(Entity e)
	{
		return Intersector.overlapConvexPolygons(e.getBounds(), leftBumper);
	}

	/**
	 * Checks to see if an Entity overlaps with this Tank's right bumper.
	 */
	private boolean hitRightBumper(Entity e)
	{
		return Intersector.overlapConvexPolygons(e.getBounds(), rightBumper);
	}

	/**
	 * Checks to see if this Tank is facing to the Northeast (for bumper collisions)
	 */
	private boolean facingNE()
	{
		return (getRotation() >= 0 && getRotation() < (Math.PI / 2));
	}

	/**
	 * Checks to see if this Tank is facing to the Northwest (for bumper collisions)
	 */
	private boolean facingNW()
	{
		return (getRotation() >= (Math.PI / 2) && getRotation() < Math.PI);
	}

	/**
	 * Checks to see if this Tank is facing to the Southwest (for bumper collisions)
	 */
	private boolean facingSW()
	{
		return (getRotation() >= Math.PI && getRotation() < (3 * Math.PI) / 2);
	}

	/**
	 * Checks to see if this Tank is facing to the Southeast (for bumper collisions)
	 */
	private boolean facingSE()
	{
		return (getRotation() >= (3 * Math.PI) / 2 && getRotation() < (2 * Math.PI));
	}

	private void checkTrees(World world)
	{
		int gridX = TileUtil.getClosestTileX(getX());
		int gridY = TileUtil.getClosestTileY(getY());
		Tile[][] allTiles = world.getMapTiles();
		if (allTiles == null || TileUtil.isValidTile(gridX, gridY, world) == false)
		{
			hidden = false;
			return;
		}
		Tile closeTile = allTiles[gridX][gridY];
		StationaryElement closeElement;

		if (!closeTile.hasElement())
		{
			hidden = false;
			return;
		}

		closeElement = closeTile.getElement();
		if (!(closeElement instanceof Tree))
		{
			hidden = false;
			return;
		}
		boolean[] corners = TileUtil.getCornerMatches(closeTile, world, new Class[] { Tree.class });
		boolean[] edges = TileUtil.getEdgeMatches(closeTile, world, new Class[] { Tree.class });
		for (int i = 0; i < 4; i++)
		{
			if (corners[i] == false || edges[i] == false)
			{
				hidden = false;
				return;
			}
		}

		hidden = true;
	}

	@Override
	public void update(World world)
	{
		if (!isAlive)
		{
			respawn(world);
		}
		updateControllers(world);
		moveTank(world);
		checkTrees(world);
	}

	/**
	 * Updates the Tank's world position according to its speed, acceleration/deceleration
	 * state, and collision information.
	 * 
	 * @param world
	 *            is a reference to the world that this Tank belongs to.
	 */
	private void moveTank(World world)
	{
		Terrain currentTerrain = TileUtil.getTileTerrain(getX(), getY(), world);
		if (currentTerrain != null)
		{
			modifiedMaxSpeed = maxSpeed * currentTerrain.getMaxSpeedModifier();
		}

		/**
		 * Booleans used to record which, if any, bumpers were hit.
		 */
		boolean collidingLeft = false;
		boolean collidingRight = false;

		/**
		 * Floats used the offset that should be applied to the Tank to record wall
		 * collisions.
		 */
		float rotationOffset = 0f;
		float xOffset = 0;
		float yOffset = 0;

		/**
		 * Store the Tank's current positioning and speed data, for use in calculations.
		 */
		float xPos = getX();
		float yPos = getY();
		float rotation = getRotation();

		/**
		 * The position where the Tank will be after one game tick, if it continues its
		 * current trajectory and speed.
		 */
		float newX = (float) (xPos + Math.cos(rotation) * (speed));
		float newY = (float) (yPos + Math.sin(rotation) * (speed));

		/**
		 * Update (replace) the right and left bumper polygons to make sure collisions are
		 * accurate.
		 */
		updateBumpers();

		// Currently checks against all Entities in the world, then checks each of the
		// ones that
		// overlap to see if they overlap the bumpers.
		List<Entity> possibleCollisions = getLookaheadEntities(world);
		for (int i = 0; i < possibleCollisions.size(); i++)
		{
			Entity collider = possibleCollisions.get(i);
			if (collider.isSolid())
			{
				if (hitLeftBumper(collider))
				{
					collidingLeft = true;
				}
				if (hitRightBumper(collider))
				{
					collidingRight = true;
				}
			}
		}

		/**
		 * If the Tank hit something with its left bumper, restrict travel in the
		 * appropriate direction, and offset/rotate the Tank to 'slide' away from the
		 * collision.
		 */
		if (collidingLeft)
		{
			rotationOffset -= rotationOffsetAmount;
			if (facingNE())
			{
				if (newY > yPos)
				{
					newY = yPos;
					yOffset -= positionOffsetAmount;
				}
			}
			else if (facingNW())
			{
				if (newX < xPos)
				{
					newX = xPos;
					xOffset += positionOffsetAmount;
				}
			}
			else if (facingSW())
			{
				if (newY < yPos)
				{
					newY = yPos;
					yOffset += positionOffsetAmount;
				}
			}
			else if (facingSE())
			{
				if (newX > xPos)
				{
					newX = xPos;
					xOffset -= positionOffsetAmount;

				}
			}
		}

		/**
		 * If the Tank hit something with its right bumper, restrict travel in the
		 * appropriate direction, and offset/rotate the Tank to 'slide' away from the
		 * collision.
		 */
		if (collidingRight)
		{
			rotationOffset += rotationOffsetAmount;
			if (facingNE())
			{
				if (newX > xPos)
				{
					newX = xPos;
					xOffset -= positionOffsetAmount;
				}
			}
			else if (facingNW())
			{
				if (newY > yPos)
				{
					newY = yPos;
					yOffset -= positionOffsetAmount;
				}
			}
			else if (facingSW())
			{
				if (newX < xPos)
				{
					newX = xPos;
					xOffset += positionOffsetAmount;
				}
			}
			else if (facingSE())
			{
				if (newY < yPos)
				{
					newY = yPos;
					yOffset += positionOffsetAmount;
				}
			}
		}

		/**
		 * If the speed of the Tank is greater than zero, modify its position and rotation
		 * by the offsets given earlier. Note that if a Tank collides on the left and
		 * right bumpers simultaneously, the rotational offsets will cancel each other
		 * out.
		 */
		if (speed > 0)
		{
			setX(newX + xOffset);
			setY(newY + yOffset);
			setRotation(rotation + rotationOffset);

			if (!accelerated)
			{
				decelerate();
			}
		}

		accelerated = false;
		decelerated = false;
	}

	/**
	 * Returns the current health of the tank
	 * 
	 * @return current hit point count
	 */
	@Override
	public int getHitPoints()
	{
		return hitPoints;
	}

	/**
	 * Method that returns the maximum number of hit points the entity can have.
	 * 
	 * @return - Max Hit points for the entity
	 */
	@Override
	public int getMaxHitPoints()
	{
		return TANK_MAX_HIT_POINTS;
	}

	/**
	 * Returns the current ammo count of the tank
	 * 
	 * @return current ammo count
	 */
	public int getAmmoCount()
	{
		return ammoCount;
	}

	/**
	 * Returns the current number of trees that the tank has gathered
	 * 
	 * @return the current tree resource count
	 */
	public int getTreeCount()
	{
		return treeCount;
	}

	/**
	 * Returns the number of mines the tank currently contains
	 * 
	 * @return the current mine count
	 */
	public int getMineCount()
	{
		return mineCount;
	}

	/**
	 * Returns the number of pillboxes the tank has on board
	 * 
	 * @return the pillbox count for the tank
	 */
	public int getPillboxCount()
	{
		return pillboxCount;
	}

	/**
	 * Changes the hit point count after taking damage
	 * 
	 * @param damagePoints
	 *            how much damage the tank has taken
	 */
	@Override
	public void takeHit(int damagePoints)
	{
		hitPoints -= Math.abs(damagePoints);
		Audio.play(Sfx.TANK_HIT);
		if (this.hitPoints <= 0)
		{
			onDeath();
		}
	}
	
	/**
	 * Called when the tank dies.
	 */
	private void onDeath()
	{
		if (isAlive)
		{
			Audio.play(Sfx.TANK_EXPLOSION);
			isAlive = false;
			respawnTime = System.currentTimeMillis() + TANK_RESPAWN_TIME;
		}
	}

	/**
	 * Increments the tanks health by a given amount
	 * 
	 * @param healPoints
	 *            - how many points the tank is given
	 */
	@Override
	public void heal(int healPoints)
	{
		if (hitPoints + Math.abs(healPoints) < TANK_MAX_HIT_POINTS)
		{
			hitPoints += Math.abs(healPoints);
		}
		else
		{
			hitPoints = TANK_MAX_HIT_POINTS;
		}
	}

	/**
	 * Supplies the tank ammo with given a set amount
	 * 
	 * @param newAmmo
	 *            - amount of ammo being transfered to the tank
	 */
	public void gatherAmmo(int newAmmo)
	{
		if (ammoCount + Math.abs(newAmmo) < TANK_MAX_AMMO)
		{
			ammoCount += Math.abs(newAmmo);
		}
		else
		{
			ammoCount = TANK_MAX_AMMO;
		}
	}

	/**
	 * Used to increment the tree count upon gathering a tree
	 */
	public void gatherTree()
	{
		if (treeCount < TANK_MAX_TREE_INVENTORY)
		{
			treeCount++;
		}
	}

	/**
	 * This method is used to consume trees
	 * 
	 * @param treesUsed
	 *            - the number of trees consumed in the action
	 */
	public void useTrees(int treesUsed)
	{
		if (treeCount - Math.abs(treesUsed) >= 0)
		{
			treeCount -= Math.abs(treesUsed);
		}
	}

	/**
	 * This method supplies the tank with mines
	 * 
	 * @param minesGathered
	 *            - the number of mines to supply the tank with
	 */
	public void gatherMine(int minesGathered)
	{
		if (mineCount + Math.abs(minesGathered) < TANK_MAX_MINE_COUNT)
		{
			mineCount += minesGathered;
		}
		else
		{
			mineCount = TANK_MAX_MINE_COUNT;
		}
	}

	/**
	 * This method creates the mine in world and passes it back to the caller
	 * 
	 * @param world
	 *            - the world to create the mine in
	 * @param startX
	 *            - The integer X position of the mine in world coordinates
	 * @param startY
	 *            - The integer Y position of the mine in world coordinates
	 * @return - the mine that is created is returned or null if there are none to place
	 *         or invalid placement location
	 */
	public Mine dropMine(World world, float startX, float startY)
	{
		if ((System.currentTimeMillis() - mineLayingTime < MINE_RELOAD_SPEED && mineLayingTime != 0)
				||startX < 0 || startX > world.getMapWidth() || startY < 0 || startY > world.getMapHeight())
		{
			return null;
		}
		
		int xTileCoord = (int) startX / 32;
		int yTileCoord = (int) startY / 32;

		if (world.getMapTiles()[xTileCoord][yTileCoord].getTerrain().getClass() != Water.class
				&& world.getMapTiles()[xTileCoord][yTileCoord].getTerrain().getClass() != DeepWater.class)
		{
			if ((!world.getMapTiles()[xTileCoord][yTileCoord].hasElement()) && (mineCount > 0))
			{
				mineLayingTime = System.currentTimeMillis();
				Mine mine = world.addEntity(Mine.class);
				world.getMapTiles()[xTileCoord][yTileCoord].setElement(mine);
				mine.setX(startX).setY(startY);
				mine.setRotation(getRotation());
				mineCount--;
				return mine;
			}
		}
		return null;
	}

	/**
	 * This method increments the pillbox count of the tank. The caller should remove the
	 * pillbox from the world.
	 */
	public void gatherPillbox()
	{
		pillboxCount++;
	}

	/**
	 * This method creates a pillbox in the world from the tank's inventory
	 * 
	 * @param world
	 *            - the world in which the pillbox is to be created
	 * @param startX
	 *            - the integer X position of the pillbox in world coordinates
	 * @param startY
	 *            - the integer Y position of the pillbox in world coordinates
	 * @return - returns the created pillbox or null if there are none to place or invalid
	 *         placement location
	 */
	public Pillbox dropPillbox(World world, int startX, int startY)
	{

		// TODO: Once Engineer functionality is created this code will need to be moved to
		// the engineer and replaced with sending the engineer out to drop the Pillbox
		if ((!world.getMapTiles()[startX / 32][startY / 32].hasElement()) && (pillboxCount > 0))
		{
			Pillbox pillbox = world.addEntity(Pillbox.class);
			world.getMapTiles()[startX / 32][startY / 32].setElement(pillbox);
			pillbox.setX(startX / 32 + 16).setY(startY / 32 + 16);
			pillbox.setRotation(getRotation());
			pillboxCount--;
			return pillbox;
		}
		else
		{
			return null;
		}
	}

	private void respawn(World world)
	{
		// Don't allow the tank to respawn until its respawn timer has expired.
		if (respawnTime > System.currentTimeMillis())
		{
			return;
		}
		
		List<Entity> spawns = world.getSpawns();
		if (spawns.size() > 0)
		{
			Entity spawn = spawns.get(randomGenerator.nextInt(spawns.size()));
			this.setParams(spawn.getX(), spawn.getY(), 0);

			Network net = NetworkSystem.getInstance();
			net.send(new MoveTank(this));
		}
		
		this.hitPoints = TANK_MAX_HIT_POINTS;
		this.ammoCount = TANK_MAX_AMMO;
		this.mineCount = TANK_MAX_MINE_COUNT;
		this.treeCount = 0;
		this.isAlive = true;
	}

	@Override
	public UUID getOwnerUID() 
	{	
		return this.ownerUID;
	}

	@Override
	public void setOwnerUID(UUID ownerUID) 
	{
		this.ownerUID = ownerUID;
	}
	
	/**
	 * Maximum amount of ammo for tank
	 * @return maximum ammo count of tank
	 */
	public static int getTankMaxAmmo() 
	{
		return TANK_MAX_AMMO;
	}
	
	/**
	 * Maximum amount of mines for tank
	 * @return maximum amount of mines a tank can carry
	 */
	public static int getTankMaxMineCount() 
	{
		return TANK_MAX_MINE_COUNT;
	}
}
