package tools;

/**
 * A collection of functions for working with usernames in text.
 */
public class Username
{
	/**
	 * Searches a string for the first ID it can find.
	 * 
	 * @param str The message to parse
	 * @return    The found IDs, or an empty string if none
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
