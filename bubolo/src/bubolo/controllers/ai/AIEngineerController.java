package bubolo.controllers.ai;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

import bubolo.controllers.Controller;
import bubolo.graphics.Graphics;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.net.command.CreateEntity;
import bubolo.util.AStar;
import bubolo.util.Coordinates;
import bubolo.world.World;
import bubolo.world.Tile;
import bubolo.world.entity.concrete.Engineer;
import bubolo.world.entity.concrete.Tank;

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
		
		if (haveDestination)
		{
			// Have I reached the next tile (waypoint)?
			// Note: at the beginning of time the engineer is already
			// at a "waypoint".
			if (engineer.isAtWaypoint())
			{
				nextWaypoint = pathToDestination.get(waypointIndex);
				waypointIndex++;
				
				System.out.println("Setting waypoint: " + nextWaypoint.getX() + ", " + nextWaypoint.getY());
				engineer.setWaypoint(nextWaypoint.getX(), nextWaypoint.getY());

				// Have I reached the final destination?
				if (waypointIndex == pathToDestination.size())
				{
					haveDestination = false;
				}
			}
			else
			{
				// Engineer is not at waypoint, let it handle the movement
				// to the next waypoint.
			}
		}
	}
	
	private void processDestinationUpdate(World world)
	{
		if (Gdx.input.justTouched())
		{
			float mouseX = (float)Gdx.input.getX();
			float mouseY = (float)Gdx.input.getY();
			
			// Need to convert screen co-ordinates to game world co-ordinates
			Camera camera = Graphics.getInstance().getCamera();			

			// Y-axis is inverted.
			mouseY = camera.viewportHeight - mouseY;
			Vector2 worldCoordinates = Coordinates.cameraToWorld(camera, new Vector2(mouseX, mouseY));
			
			int screenDestX = (int)(worldCoordinates.x);
			int screenDestY = (int)(worldCoordinates.y);

			int destX = screenDestX / 32;
			int destY = screenDestY / 32;
			
			assert(destX < world.getMapWidth());
			assert(destY < world.getMapHeight());
			
			Tile destTile = world.getMapTiles()[destX][destY];

			// Get current position
			int curX = (int)(engineer.getX() / 32);
			int curY = (int)(engineer.getY() / 32);
			
			assert(curX < world.getMapWidth());
			assert(curY < world.getMapHeight());
			
			Tile curTile = world.getMapTiles()[curX][curY];

			// Compute new path to destination
			pathToDestination = AStar.calculateShortestPath(world, curTile, destTile);
			haveDestination = true;
			waypointIndex = 0;
			
			System.out.println("Start: " + curTile.getGridX() + ", " + curTile.getGridY());
			System.out.println("Goal: " + destTile.getGridX() + ", " + destTile.getGridY());
			System.out.println("Path:");
			for (Tile t: pathToDestination)
			{
				System.out.println("    " + t.getGridX() + " (" + t.getX() + "), " + t.getGridY() + " (" + t.getY() + ")");			
			}
		}
	}

}