package bubolo.audio;

import static org.junit.Assert.*;

import org.junit.Test;

public class AudioTest
{

	@Test
	public void playSoundEffectExplosion()
	{
		Audio.play(Sfx.EXPLOSION);
	}
	
	@Test
	public void playSoundEffectCannonFired()
	{
		Audio.play(Sfx.CANNON_FIRED);
	}
	
	@Test
	public void playSoundEffectTankHit()
	{
		Audio.play(Sfx.TANK_HIT);
	}

	@Test
	public void playMusic()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void stopMusic()
	{
		fail("Not yet implemented");
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
