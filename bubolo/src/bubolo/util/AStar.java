package bubolo.util;

import java.lang.Math;

import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.IdentityHashMap;
import java.util.Collections;

import bubolo.world.World;
import bubolo.world.Tile;

/**
 * Implementation of the A-star pathfinding algorithm.
 * @author BU CS673 - Clone Productions
 */
public abstract class AStar
{
	/**
	 * Heuristic unit distances between adjacent tiles.
	 */
	private static final int straightCost = 1;
	private static final int diagonalCost = 1;
	
	/**
	 * Heuristic to calculate approximate distance between two tiles on the map.
	 * @param start
     *               starting point.
	 * @param current
     *               current point.
	 * @param goal
     *               end point.
	 * @return
     *               heuristic distance from current to goal.
	 */
	public static float calculateH(AStarNode start, AStarNode current, AStarNode goal)
	{
		// Chebyshev distance, with diagonal movement allowed but with a
		// different cost.
		int dx = Math.abs(current.getX() - goal.getX());
		int dy = Math.abs(current.getY() - goal.getY());
		float heuristic = straightCost * (dx + dy) + (diagonalCost - 2 * straightCost) * Math.min(dx, dy);

		// To avoid ties, perturb the heuristic by a little amount proportional
		// to the cross product of the (current->goal) vector and the
		// (start->goal) vector.
		int dx1 = current.getX() - goal.getX();
		int dy1 = current.getY() - goal.getY();
		int dx2 = start.getX() - goal.getX();
		int dy2 = start.getY() - goal.getY();
		int cross = Math.abs(dx1*dy2 - dx2*dy1);
		heuristic += cross*0.001;
		
		return heuristic;
	}
		
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
		// Pseudocode from this algorithm will be annotated in comments using
		// -- ... --

        // Extract some useful variables from the world.
        int mapWidth = world.getMapWidth();
        int mapHeight = world.getMapHeight();
        Tile[][] mapTiles = world.getMapTiles();
        
        // We will maintain all per-tile meta-data (f, g, parent etc.) in a
        // separate data structure called AStarNode. This is to avoid adding
        // any ugly A*-specific data into the Tile class. In addition to the
        // meta-data, each AStarNode object will contain a reference to its
        // associated Tile object.
        
        // Occasionally, we will need the reverse association, i.e., given a
        // Tile object, we will need to find out its associated AStarNode
        // object. But since we are not allowing ourselves to edit the Tile
        // object itself, how do we capture this association? By using a
        // separate Map data structure. Note that we could have pre-computed
        // an entire 2-dimensional AStarNode[][] array that is exactly parallel
        // to the Tile[][] array, but this is too expensive and wasteful. The
        // Map is much more efficient, because we will only add entries to the
        // map as and when we encounter new Tiles for which an AStarNode
        // does not exist yet.
        Map<Tile, AStarNode> ownerNodes = new IdentityHashMap<Tile, AStarNode>();
        
        // Start out populating the map with only a Start and Goal node.
        AStarNode start_node = new AStarNode(start);
        AStarNode goal_node = new AStarNode(goal);
        
        ownerNodes.put(start, start_node);
        ownerNodes.put(goal, goal_node);
        
        // Every time we create a new AStarNode, we will also take the
        // opportunity to pre-calculate the H() function for that node. Because
        // the H function is a simple calculation based only on the co-ordinates
        // of the node and the goal, we don't *have* to do this pre-calculation
        // and can repeatedly invoke the calculateH() function every time we
        // see a node, but that would be wasteful since each node can be visited
        // more than once.
        start_node.setH(calculateH(start_node, start_node, goal_node));
        goal_node.setH(0);
        
        // -- OPEN = priority queue containing START --
        PriorityQueue<AStarNode> open = new PriorityQueue<AStarNode>(0);
        open.add(new AStarNode(start));

        // -- CLOSED = empty set --
        Set<AStarNode> closed = new HashSet<AStarNode>();

        // -- while lowest rank in OPEN is not the GOAL: --
        while (open.peek() != goal_node)
        {
            // -- current = remove lowest rank item from OPEN --
            AStarNode current = open.poll();

            // -- add current to CLOSED --
            closed.add(current);

            // The algorithm now says:
            // -- for neighbors of current: --
            // We need to repeat some code for each neighbor. The easiest way
            // to do this is to find each neighbor to the left, top, etc. of
            // the current node, making sure we don't look past the borders of
            // the map, and add them all to a list. Once the list is ready, we
            // can iterate over it and do what the algorithm asks us to do.
            List<Tile> neighbors = new LinkedList<Tile>();

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

            // The neighbor list is ready. We are now ready to run the loop:
            // -- for neighbors of current: --
            for (Tile n: neighbors)
            {
                // We now need to search each neighbor node in the OPEN and
                // CLOSED sets. For this we need the AStarNode owner of this tile.
                // It's possible that an AStartNode for this tile hasn't been
                // created yet, in which case the following function will return
                // NULL. If so, create the owner node now and update the owner
            	// map. Also remember to pre-calculate the H function.
                AStarNode n_node = ownerNodes.get(n);
                if (n_node == null)
                {
                	n_node = new AStarNode(n);
                	ownerNodes.put(n, n_node);
                	n_node.setH(calculateH(start_node, n_node, goal_node));
                }
                
                // -- cost = g(current) + movementcost(current, neighbor) --
                float cost = current.getG() + 1; // ignore terrain for now FIXME

                // -- if neighbor in OPEN and cost less than g(neighbor): --
                if (open.contains(n_node) && cost < n_node.getG())
                {
                    // -- remove neighbor from OPEN, because new path is better --
                    open.remove(n_node);
                }

                // -- if neighbor in CLOSED and cost less than g(neighbor): --
                if (closed.contains(n_node) && cost < n_node.getG())
                {
                    // -- remove neighbor from CLOSED --
                    closed.remove(n);
                }

                // -- if neighbor not in OPEN and neighbor not in CLOSED: --
                if (!open.contains(n_node) && !closed.contains(n_node))
                {
                    // -- set g(neighbor) to cost --
                    n_node.setG(cost);

                    // -- add neighbor to OPEN --
                    open.add(n_node);

                    // -- set priority queue rank to g(neighbor) + h(neighbor) --
                    // Note that if we hadn't pre-calculated the H function on
                    // creation of the node, we would have had to invoke the
                    // calculateH() funtion now instead of simply looking it up.
                    n_node.setF(n_node.getG() + n_node.getH());

                    // -- set neighbor's parent to current --
                    n_node.setParent(current);
                }
            }
        }
        
        // -- reconstruct reverse path from goal to start --
        // -- by following parent pointers --
        // Algorithm over, we have reached the Goal. Now, create a list
        // describing the path by back-tracking parent pointers from the goal.
        List<Tile> path = new LinkedList<Tile>();
        AStarNode node = goal_node;
        while (node != start_node)
        {
        	path.add(node.getTile());
        	node = node.getParent();
        }

        // Since the user of this algorithm expects a *forward* path, we need
        // to reverse the list at the end.
        Collections.reverse(path);
        
        // All done!
        return(path);
	}
}
