/**
 *
 */

package bubolo.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import bubolo.ui.UserInterface;
import bubolo.ui.Preferences.PreferencesModel;

/**
 * @author BU CS673 - Clone Productions
 */
public class PreferencesManager implements Serializable
{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -9175957300891593921L;

	/** Preference File Manager Class
	 */
	public void PreferenceManager()
	{
		
	}
	
	/**
	 * Loads and returns the Preferences Model from disk
	 * @return the PreferencesModel loaded from disk
	 */
	public PreferencesModel LoadPreference()
	{
		PreferencesModel pm = new PreferencesModel();
		try(
			InputStream file = new FileInputStream(UserInterface.PREFERENCES_FILENAME);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);)
		{
			pm = (PreferencesModel)input.readObject();
		}
		catch(IOException ex)
		{
			System.out.println("Error Saving File. " + ex);
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			System.out.println("Load Error.  Call not found. " + e);
		}
		return pm;
	}
	
	/**
	 * Serializes and saves the Preferences model to disk
	 * @param pm the Preferences model to save to disk
	 */
	public void SavePreference(PreferencesModel pm)
	{
		try(
			OutputStream file = new FileOutputStream(UserInterface.PREFERENCES_FILENAME);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);)
		{
			output.writeObject(pm);
		}
		catch(IOException ex)
		{
			System.out.println("Error Saving File. " + ex);
		}
	}
}

