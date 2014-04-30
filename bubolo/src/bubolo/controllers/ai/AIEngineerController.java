package bubolo.controllers.ai;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

import bubolo.controllers.Controller;
import bubolo.graphics.Graphics;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.net.command.MoveEntity;
import bubolo.util.AStar;
import bubolo.util.Coordinates;
import bubolo.world.World;
import bubolo.world.Tile;
import bubolo.world.entity.concrete.DeepWater;
import bubolo.world.entity.concrete.Engineer;

/**
 * A controller for Engineers. This controller checks if the engineer is at a
 * desired destination. If not, it computes the shortest path to the destination
 * and proceeds towards it.
 * 
 * @author BU CS673 - Clone Productions
 */
public class AIEngineerController implements Controller
{
	private Engineer engineer;

	// current destination
	private boolean haveDestination = false;
	private List<Tile> pathToDestination;
	private int waypointIndex;
	private Tile nextWaypoint;
	
	/**
	 * constructs an AI Engineer controller
	 * 
	 * @param engineer
	 *            the engineer this controller will correspond to.
	 */
	public AIEngineerController(Engineer engineer)
	{
		this.engineer = engineer;
	}

	@Override
	public void update(World world)
	{
		processDestinationUpdate(world);
		processMovement();
	}
	
	private void processMovement()
	{
		// Has the engineer reached the next tile (waypoint)?
		// Note: at the beginning of time the engineer is already
		// at a "waypoint".
		if (engineer.isAtWaypoint())
		{
			if (haveDestination)
			{
				nextWaypoint = pathToDestination.get(waypointIndex);
				waypointIndex++;
				
				engineer.setWaypoint(nextWaypoint.getX(), nextWaypoint.getY());

				// Was this the final waypoint (== destination)?
				if (waypointIndex == pathToDestination.size())
				{
					haveDestination = false;
				}
			}
		}
		else
		{
			// Engineer is not at waypoint, let it handle the movement
			// to the next waypoint. Just send a network move command.
			Network net = NetworkSystem.getInstance();
			net.send(new MoveEntity(engineer));
		}
	}
	
	private void processDestinationUpdate(World world)
	{
		if (Gdx.input.justTouched())
		{
			float mouseX = Gdx.input.getX();
			float mouseY = Gdx.input.getY();
			
			// Need to convert screen co-ordinates to game world co-ordinates
			Camera camera = Graphics.getInstance().getCamera();			

			// Y-axis is inverted.
			mouseY = camera.viewportHeight - mouseY;
			Vector2 worldCoordinates = Coordinates.cameraToWorld(camera, new Vector2(mouseX, mouseY));
			
			int screenDestX = (int)(worldCoordinates.x);
			int screenDestY = (int)(worldCoordinates.y);

			int destX = screenDestX / Coordinates.TILE_TO_WORLD_SCALE;
			int destY = screenDestY / Coordinates.TILE_TO_WORLD_SCALE;
			
			Tile destTile = world.getMapTiles()[destX][destY];
			
			// Get current position
			int curX = (int)(engineer.getX() / Coordinates.TILE_TO_WORLD_SCALE);
			int curY = (int)(engineer.getY() / Coordinates.TILE_TO_WORLD_SCALE);
			
			Tile curTile = world.getMapTiles()[curX][curY];

			// If the destination tile is not traversable, don't even bother
			// finding a path. However, it is important to cancel any ongoing
			// pathing activity.
			if (!isTileTraversable(destTile))
			{
				// Destination unreachable, cancel current movement. Note that
				// engineer will complete movement to the last waypoint where
				// he was headed.
				haveDestination = false;				
				return;
			}

			// Compute new path to destination
			pathToDestination = AStar.calculateShortestPath(world, curTile, destTile);
			if (pathToDestination != null)
			{
				// A route exists to the destination
				haveDestination = true;
				waypointIndex = 0;
			}
			else
			{
				// Destination unreachable, cancel current movement. Note that
				// engineer will complete movement to the last waypoint where
				// he was headed.
				haveDestination = false;
			}
		}
	}
	
	// Is a tile traversable?
	private static boolean isTileTraversable(Tile t)
	{
    	// Check the game world to see if this tile has a solid
    	// stationary element. If so, skip this tile since we cannot
    	// have a path through a stationary element.
    	if (t.hasElement())
    	{
    		if (t.getElement().isSolid())
    		{
    			return false;
    		}
    	}
    	
    	// Check to see if the terrain is too dangerous.
    	if (t.getTerrain().getClass() == DeepWater.class)
    	{
    		return false;
    	}
    	
    	// All okay.
    	return true;
	}
}