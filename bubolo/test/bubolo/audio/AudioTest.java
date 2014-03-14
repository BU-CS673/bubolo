package bubolo.audio;

import static org.junit.Assert.*;

import org.junit.Test;

public class AudioTest
{

	@Test
	public void playSoundEffectCannonFired()
	{
		Audio.play(Sfx.CANNON_FIRED);
	}
	
	@Test
	public void playSoundEffectEngineerKilled()
	{
		Audio.play(Sfx.ENGINEER_KILLED);
	}
	
	@Test
	public void playSoundEffectExplosion()
	{
		Audio.play(Sfx.EXPLOSION);
	}
	
	@Test
	public void playSoundEffectPillboxBuilt()
	{
		Audio.play(Sfx.PILLBOX_BUILT);
	}
	
	@Test
	public void playSoundEffectPillboxHit()
	{
		Audio.play(Sfx.PILLBOX_HIT);
	}
	
	@Test
	public void playSoundEffectRoadBuilt()
	{
		Audio.play(Sfx.ROAD_BUILT);
	}
	
	@Test
	public void playSoundEffectTankDrowned()
	{
		Audio.play(Sfx.TANK_DROWNED);
	}
	
	@Test
	public void playSoundEffectTankHit()
	{
		Audio.play(Sfx.TANK_HIT);
	}
	
	@Test
	public void playSoundEffectTankInShallowWater()
	{
		Audio.play(Sfx.TANK_IN_SHALLOW_WATER);
	}
	
	@Test
	public void playSoundEffectTreeGathered()
	{
		Audio.play(Sfx.TREE_GATHERED);
	}
	
	@Test
	public void playSoundEffectWallBuilt()
	{
		Audio.play(Sfx.WALL_BUILT);
	}
	
	@Test
	public void playSoundEffectWallHit()
	{
		Audio.play(Sfx.WALL_HIT);
	}

	@Test
	public void startStopMusic()
	{
		Audio.startMusic();
		Audio.stopMusic();
	}
	
	@Test
	public void stopMusicMultiple()
	{
		// Ensure that Audio.stopMusic() can be called multiple times without issues.
		Audio.stopMusic();
		Audio.stopMusic();
	}
	
	@Test
	public void setSoundEffectVolume()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void setMusicVolume()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void disposeAudioSystem()
	{
		fail("Not yet implemented");
	}
}
