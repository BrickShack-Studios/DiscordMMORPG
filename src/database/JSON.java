package database;

/**
 * Objects which implement this will be savable as JSON files.
 */
public interface JSON
{
	/**
	 * Saves the object to a JSON file.
	 */
	public void saveJSON();
	
	/**
	 * Loads the object from a JSON file. The object should be
	 * @return True if the load was successful. False otherwise.
	 */
	public boolean loadJSON();
}
