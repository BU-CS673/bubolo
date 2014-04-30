package bubolo.mock;

import java.util.UUID;

import bubolo.controllers.ControllerFactory;
import bubolo.util.Coordinates;
import bubolo.util.GameLogicException;
import bubolo.world.Tile;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Engineer;

/**
 * Mock class used for testing components that need a world implementation
 * (Which is not available at the time that this was implemented).
 * @author BU CS673 - Clone Productions
 */
public class MockEngineerCreator extends MockWorld
{
	private Tile mapTiles[][];
	private int mapGridWidth = 1;
	private int mapGridHeight = 1;
	
	/**
	 * Constructor
	 */
	public MockEngineerCreator()
	{
		mapTiles = new Tile[mapGridWidth][mapGridHeight];
		for (int i = 0; i < mapGridWidth; i++)
		{
			for (int j = 0; j < mapGridHeight; j++)
			{
				mapTiles[i][j] = new Tile(i, j, new Grass());				
			}
		}
	}
	
	@Override
	public <T extends Entity> T addEntity(Class<T> c) throws GameLogicException
	{
		return (T) new Engineer();
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c, UUID id) throws GameLogicException
	{
		return (T) new Engineer();
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c, ControllerFactory controllerFactory)
			throws GameLogicException
	{
		return (T) new Engineer();
	}

	@Override
	public <T extends Entity> T addEntity(Class<T> c, UUID id, ControllerFactory controllerFactory)
			throws GameLogicException
	{
		return (T) new Engineer();
	}

	@Override
	public Tile[][] getMapTiles()
	{
		return mapTiles;
	}

	@Override
	public int getMapWidth()
	{
		return mapGridWidth * Coordinates.TILE_TO_WORLD_SCALE;
	}

	@Override
	public int getMapHeight()
	{
		return mapGridHeight * Coordinates.TILE_TO_WORLD_SCALE;
	}
}
