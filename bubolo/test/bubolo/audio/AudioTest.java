package bubolo.audio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;

import bubolo.graphics.LibGdxAppTester;

public class AudioTest
{
	static boolean isComplete = false;
	static boolean passed = false;
	
	@BeforeClass
	public static void setUp()
	{	
		LibGdxAppTester.createApp();
	}
	

	@Test
	public void checkDefaultSoundEffectVolume()
	{
		assertEquals(50, Audio.getSoundEffectVolume());
	}
	

	@Test
	public void checkDefaultMusicVolume()
	{
		assertEquals(50, Audio.getMusicVolume());
	}

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
	public void playSoundEffectTreeHit()
	{
		Audio.play(Sfx.TREE_HIT);
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
	
	// This works when run by itself, but not always with all other tests. I will look into this
	// in the future, as I did with the graphics tests, but for now I am going to leave it, 
	// since it does work. The issue is similar to the graphics issue we experienced, where
	// either OpenAL isn't initialized in time for the test, or multiple OpenAL contexts are
	// attempted to be created since multiple threads are running concurrently. 
	// (Christopher D. Canfield: 3/14/2014)
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
	public void testMusicOnCompletionListener()
	{
		Audio.testMusicOnCompletionListener();
	}
	
	@Test
	public void setSoundEffectVolume()
	{
		Audio.setSoundEffectVolume(75);
		assertEquals(75, Audio.getSoundEffectVolume());
		
		Audio.setSoundEffectVolume(50);
		assertEquals(50, Audio.getSoundEffectVolume());
	}
	
	@Test
	public void setSoundEffectVolumeInvalidPrecondition1()
	{
		try {
			Audio.setSoundEffectVolume(101);
			fail("Precondition not enforced.");
		} catch (Exception e) {}
	}
	
	@Test
	public void setSoundEffectVolumeInvalidPrecondition2()
	{
		try {
			Audio.setSoundEffectVolume(-1);
			fail("Precondition not enforced.");
		} catch (Exception e) {}
	}
	
	@Test
	public void setMusicVolume()
	{
		Audio.setMusicVolume(80);
		assertEquals(80, Audio.getMusicVolume());
		
		Audio.setMusicVolume(50);
		assertEquals(50, Audio.getMusicVolume());
	}
	
	@Test
	public void setMusicVolumeInvalidPrecondition1()
	{
		try {
			Audio.setMusicVolume(101);
			fail("Precondition not enforced.");
		} catch (Exception e) {}
	}
	
	public void setMusicVolumeInvalidPrecondition2()
	{
		try {
			Audio.setMusicVolume(-1);
			fail("Precondition not enforced.");
		} catch (Exception e) {}
	}
	
	@Test
	public void disposeAudioSystem()
	{
		Audio.dispose();
	}
}
