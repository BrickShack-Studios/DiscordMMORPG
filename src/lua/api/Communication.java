package lua.api;

import tools.Username;
import world.Room;
import world.World;

/**
 * Commands used for communicating textually. Meant to be accessed via the
 * LuaJ interface.
 * 
 * @class
 */
public class Communication
{
	/**
	 * Sends a message as a DM to the specified player.
	 * 
	 * @param id		The ID of the player to DM.
	 * @param message	The message to send.
	 * @return			True if the ID is found in the world, false otherwise.
	 * 
	 * @see entity.Player
	 * @see world.World
	 */
	public boolean whisper(String id, String message)
	{
		boolean success = false;

		if (World.registered(id))
		{
			World.getJDA().getUserById(id).openPrivateChannel().queue(
					(channel) -> channel.sendMessage(message).queue());
			
			success = true;
		}
		
		return success;
	}
	
	/**
	 * DMs all `Player`s in a `Room`.
	 * @param location	The `Room` to propagate the message through.
	 * @param message	The message to send.
	 * 
	 * @see	world.Room
	 * @see	entity.Player
	 * 
	 * @todo Implement this
	 */
	public void say(Room location, String message)
	{
		//channel.sendMessage(message).queue();
		return;
	}
	
	/**
	 * Sends a message to every `Player` in a `Room` via the last
	 * channel they were active in. This allows others also in the
	 * channel to see the message.
	 * 
	 * @param location	The `Room` to source the `Player`s from. 
	 * @param message	The message to send to the `Player`s.
	 * 
	 * @see entity.Player
	 * @see world.Room
	 * 
	 * @todo Implement this
	 */
	public void shout(Room location, String message)
	{
		return;
	}
	
	/**
	 * A wrapper function for {@link tools.Username.stripID()}.
	 * 
	 * @param str	The string to parse.
	 * @return		The ID contained in the string, if any.
	 * 
	 * @see tools.Username.stripID()
	 */
	public String stripID(String str)
	{
		return Username.stripID(str);
	}
}
