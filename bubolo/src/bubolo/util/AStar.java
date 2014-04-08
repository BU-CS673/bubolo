package bubolo.util;

import java.util.List;
import java.util.PriorityQueue;
import java.util.HashSet;

import bubolo.util.Coordinates;
import bubolo.util.Tiles;

/**
 * Implementation of the A-star pathfinding algorithm.
 * @author BU CS673 - Clone Productions
 */
public abstract class AStar
{
	/**
	 * Calculates a "shortest" path between any two tiles the map. Inputs and
     * outputs are in terms of abstract Tile references. Since each tile object
     * knows its own co-ordinates, this results in a cleanerm abstract
     * interface (instead of passing co-ordinate values around). Computation is
     * performed on Tile units, not on World units.
	 * @param world
     *                      is a reference to the world map.
	 * @param start
     *                      is the tile representing the starting point.
	 * @param goal
     *                      is the tile representing the destination point.
	 * @return
     *                      a list of tiles that form the "shortest" path.
	 */
	public static List<Tile> calculateShortestPath(World world, Tile start, Tile goal)
	{
        // The algorithm is based on the pseudocode in
        // theory.stanford.edu/~amitp/GameProgramming/ImplementationNotes.html

        // Extract some useful variables from the world.
        int mapWidth = world.getMapWidth();
        int mapHeight = world.getMapHeight();
        Tile[][] mapTiles = world.getMapTiles();
        
        // Create 2-dimensional arrays for f, g and h calculation results. We
        // could also have placed these inside the Tile object, but that would
        // pollute the class with fields specific to this algorithm.
        // Alternatively, we could have created a wrapper class with these
        // additional field.
        int f[mapWidth][mapHeight];
        int g[mapWidth][mapHeight];
        int h[mapWidth][mapHeight];

        // Create a priority queue for the Open set. Set the f value as the
        // comparison function for the priority.
        PriorityQueue<Tile> open = new PriorityQueue<Tile>(0, new Comparator<Tile>() {
                public int compare(Tile t1, Tile t2) {
                    return Integer.compare(f[t1.getX()][t1.getY()], f[t2.getX()][t2.getY()]);
                }
            });

        // OPEN = priority queue containing START
        open.add(start);

        // CLOSED = empty set
        Set<Tile> closed = new HashSet<Tile>();

        // while lowest rank in OPEN is not the GOAL:
        while (open.peek() != goal)
        {
            // current = remove lowest rank item from OPEN
            // add current to CLOSED
            Tile current = open.poll();
            closed.add(current);

            // for neighbors of current:
            List<Tile> neighbors = new List<Tile>();

            int x = current.getX();
            int y = current.getY();

            if (x != 0 && y != 0)
                neighbors.add(mapTiles[x-1][y-1]);
            if (x != 0)
                neighbors.add(mapTiles[x-1][y]);
            if (x != 0 && y != mapHeight-1)
                neighbors.add(mapTiles[x-1][y+1]);
            if (y != mapHeight-1)
                neighbors.add(mapTiles[x][y+1]);
            if (x != mapWidth-1 && y != mapHeight-1)
                neighbors.add(mapTiles[x+1][y+1]);
            if (x != mapWidth-1)
                neighbors.add(mapTiles[x+1][y]);
            if (x != mapWidth-1 && y != 0)
                neighbors.add(mapTiles[x+1][y-1]);
            if (y != 0)
                neighbors.add(mapTiles[x][y-1]);

            for (Tile n: neighbors)
            {
                // cost = g(current) + movementcost(current, neighbor)
                int cost = g[x][y] + movementCost(current, n);

                // if neighbor in OPEN and cost less than g(neighbor):
                if (open.contains(n) && cost < g[n.getX()][n.getY()])
                {
                    // remove neighbor from OPEN, because new path is better
                    open.remove(n);
                }

                // if neighbor in CLOSED and cost less than g(neighbor):
                if (closed.contains(n) && cost < g[n.getX()][n.getY()])
                {
                    // remove neighbor from CLOSED
                    closed.remove(n);
                }

                // if neighbor not in OPEN and neighbor not in CLOSED:
                if (!open.contains(n) && !closed.contains(n))
                {
                    // set g(neighbor) to cost
                    g[n.getX()][n.getY()] = cost;

                    // add neighbor to OPEN
                    open.add(n);

                    // set priority queue rank to g(neighbor) + h(neighbor)
                    f[n.getX()][n.getY()] = g[n.getX()][n.getY()] + h[n.getX()][n.getY()];

                    // set neighbor's parent to current
                }
            }
        }
	}
}
