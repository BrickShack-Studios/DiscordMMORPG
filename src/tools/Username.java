package tools;

/**
 * A collection of functions for working with usernames in text.
 * 
 * \class
 */
public class Username
{
	/**
	 * Parses a string, searching for user IDs.
	 * 
	 * @param str 	The message to parse.
	 * @return    	The found ID, or an empty string if none.
	 * @warning		Grabs the *last* ID found!
	 */
	public static String stripID(String str)
	{
		String[] strs = str.split("<!@>");
		String result = "";
		
		for (String s : strs)
			if ("0123456789".contains(s.substring(0, 1)))
				result = s;
		
		return result;
	}
}
