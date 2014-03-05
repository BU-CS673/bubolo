package bubolo.world;

import bubolo.util.GameLogicException;
import bubolo.world.entity.Modifier;
import bubolo.world.entity.StationaryElement;
import bubolo.world.entity.Terrain;

public class Tile
{
	public static final float WORLD_SCALE = 32;
	private int gridX;
	private int gridY;
	private Terrain myTerrain;
	private Modifier myMod = null;
	private StationaryElement myElement = null;

	public Tile(int x, int y, Terrain t)
	{
		myTerrain = t;
		t.setTile(this);
		gridX = x;
		gridY = y;
	}

	public float getX()
	{
		return gridX * WORLD_SCALE;
	}

	public float getY()
	{
		return gridY * WORLD_SCALE;
	}

	public int getGridX()
	{
		return gridX;
	}

	public int getGridY()
	{
		return gridY;
	}

	public Terrain getTerrain()
	{
		if (myTerrain == null)
		{
			throw new GameLogicException("Tile has no terrain to return!");
		}
		else
		{
			return myTerrain;
		}
	}

	public Modifier getModifier()
	{
		if (myMod == null)
		{
			throw new GameLogicException("Tile has no modifier to return!");
		}
		else
			return myMod;
	}

	public StationaryElement getElement()
	{
		if (myElement == null)
		{
			throw new GameLogicException("Tile has no element to return!");
		}
		else
			return myElement;
	}

	public boolean hasModifier()
	{
		if (myMod != null)
		{
			return true;
		}
		else
			return false;
	}

	public boolean hasElement()
	{
		if (myElement != null)
		{
			return true;
		}
		else
			return false;
	}
	
	public Tile clearElement(){
		myElement = null;
		return this;
	}
	
	public Tile clearModifier(){
		myMod = null;
		return this;
	}

	public Tile setElement(StationaryElement e)
	{
		myElement = e;
		e.setTile(this);
		return this;
	}

	public Tile setModifier(Modifier m)
	{
		myMod = m;
		m.setTile(this);
		return this;
	}

	public Tile setTerrain(Terrain t)
	{
		if (t != null){
		myTerrain = t;
		t.setTile(this);
		return this;
		}
		else
			throw new GameLogicException("Cannot set a Tile's Terrain object to null!");
	}

}
