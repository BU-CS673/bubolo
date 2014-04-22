/**
 *
 */

package bubolo.ui.Preferences;

import static org.junit.Assert.*;
import org.junit.Test;
import bubolo.ui.UserInterface;


/**
 * @author BU CS673 - Clone Productions
 */
public class PreferencesModelTest 
{
	
	/**
	 * Generates a PreferencesModel object
	 */
	@Test
	public void testPreferencesModel() 
	{
		PreferencesModel pm = new PreferencesModel();
		assertNotNull("The created object is null!", pm);
	}
	
	/**
	 * Tests the PreferencesModel Get function by creating a PreferencesModel then using a Get to retrieve it
	 */
	@Test
	public void testPreferencesModelget()
	{
		PreferencesModel pm = new PreferencesModel();
		PreferencesModel newPM;
		newPM = pm.get();
		
		assertEquals("Get does not return the same object", pm, newPM);		
	}
	
	/**
	 * Creates two PreferencesModels
	 * Sets pm2 to different internal values from pm
	 * Checks that pm2 and pm have different values
	 * Uses PreferencesModel.set to set pm and pm2 to same values
	 * Checks that pm2 and pm have same values
	 */
	@Test
	public void testPreferencesModelset()
	{
		PreferencesModel pm = new PreferencesModel();
		PreferencesModel pm2 = new PreferencesModel();
		
		// set pm2 to some specific values which are different from defaults
		pm2.setMfxVol(99);
		pm2.setSfxVol(98);
		pm2.setScreenSize(1);
		
		// check that pm and pm2 are equal--this is a sanity test to be sure that the two PreferencesModels have different internal values
		assertFalse("Two test PreferencesModels have the same MfxVol", (pm.getMfxVol() == pm2.getMfxVol()));
		assertFalse("Two test PreferencesModels have the same SfxVol", (pm.getSfxVol() == pm2.getSfxVol()));
		assertFalse("Two test PreferencesModels have the same ScreenSize", (pm.getScreenSize() == pm2.getScreenSize()));
		
		
		// set pm2's internals to the same as pm
		pm2.set(pm);
		
		// test that pm2 and pm are now equal
		assertTrue("Two test PreferencesModels do not have the same MfxVol", (pm.getMfxVol() == pm2.getMfxVol()));
		assertTrue("Two test PreferencesModels do not have the same SfxVol", (pm.getSfxVol() == pm2.getSfxVol()));
		assertTrue("Two test PreferencesModels do not have the same ScreenSize", (pm.getScreenSize() == pm2.getScreenSize()));
	}
	
	/**
	 * Creates a PreferencesModel
	 * Use get to check that two things
	 *   1. Get is retrieving a value
	 *   2. The value retrieved is the expected default value
	 */
	@Test
	public void testPreferencesModelindividualgets()
	{
		PreferencesModel pm = new PreferencesModel();
				
		assertEquals("Value does not match expected", UserInterface.SFXVOL_DEFAULT, pm.getSfxVol());
		assertEquals("Value does not match expected", UserInterface.MFXVOL_DEFAULT, pm.getMfxVol());
		assertEquals("Value does not match expected", UserInterface.SCREENSIZE_DEFAULT, pm.getScreenSize());
	}
	
	/**
	 * Creates a PreferencesModel
	 * Uses set methods to change the values
	 * Checks that the values retrieved are the same as what was set
	 */
	@Test
	public void testPreferencesModelindividualsets()
	{
		PreferencesModel pm = new PreferencesModel();
		
		pm.setMfxVol(42);
		pm.setSfxVol(84);
		pm.setScreenSize(3);
		
		assertEquals("Value does not match expected", 84, pm.getSfxVol());
		assertEquals("Value does not match expected", 42, pm.getMfxVol());
		assertEquals("Value does not match expected", 3, pm.getScreenSize());
	}
	
	/**
	 * Tries to use setMfxVol for invalid inputs
	 */
	@Test
	public void testInvalidMfxVol()
	{
		PreferencesModel pm = new PreferencesModel();
		try
		{
			pm.setMfxVol(-1);
			fail("setMfxVol did not fail on negative value");
		}
		catch (Exception e)
		{
		}
		
		try
		{
			pm.setMfxVol(101);
			fail("setMfxVol did not fail value over 100");
		}
		catch (Exception e)
		{
		}		
	}

	/**
	 * Tries to use setSfxVol for invalid inputs
	 */
	@Test
	public void testInvalidSfxVol()
	{
		PreferencesModel pm = new PreferencesModel();
		try
		{
			pm.setSfxVol(-1);
			fail("setSfxVol did not fail on negative value");
		}
		catch (Exception e)
		{
		}
		
		try
		{
			pm.setSfxVol(101);
			fail("setSfxVol did not fail value over 100");
		}
		catch (Exception e)
		{
		}		
	}
	
	/**
	 * Tries to use setScreenSize for invalid inputs
	 */
	@Test
	public void testInvalidScreenSize()
	{
		PreferencesModel pm = new PreferencesModel();
		try
		{
			pm.setScreenSize(0);
			fail("setMfxVol did not fail on zero value");
		}
		catch (Exception e)
		{
		}
		
		try
		{
			pm.setScreenSize(4);
			fail("setMfxVol did not fail value over 3");
		}
		catch (Exception e)
		{
		}		
	}
	
}
