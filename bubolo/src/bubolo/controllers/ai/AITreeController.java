package bubolo.controllers.ai;

import java.util.Random;

import bubolo.controllers.Controller;
import bubolo.world.World;
import bubolo.world.entity.concrete.Tree;

public class AITreeController implements Controller
{
	
	private float createAtX = 0;
	private float createAtY = 0 ;
	private float tempX = 0;
	private float tempY = 0;
	
	private int tileScore = 0;
	private int tempScore = 0;
	
	private int ticksSinceReset = 0;
	private int ticksPerGrowth = 300;
	Random randomGenerator = new Random();
	
	public AITreeController()
	{
		
	}

	@Override
	public void update(World world) 
	{
		pickATile(world);
		getTileScore();
		if (tempScore > tileScore)
		{
			createAtX = tempX;
			createAtY = tempY;
			tileScore = tempScore;
			ticksSinceReset = 0;
		}
		else
		{
			if (ticksSinceReset >= ticksPerGrowth)
			{
				world.addEntity(Tree.class).setParams(createAtX, createAtY, 0);
				ticksSinceReset = 0;
				tileScore = 0;				
			}
			else
			{
				ticksSinceReset++;
			}
		}	
	}
	private void pickATile(World world)
	{
		tempX = randomGenerator.nextInt(world.getMapWidth());
		tempY = randomGenerator.nextInt(world.getMapHeight());
	}
	private void getTileScore()
	{
		
	}
}
