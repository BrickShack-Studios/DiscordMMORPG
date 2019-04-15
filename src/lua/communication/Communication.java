package lua.communication;

import org.luaj.vm2.LuaValue;

import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.MessageChannel;
import tools.Username;
import world.Room;
import world.World;

/**
 * Commands used for communicating textually.
 */
public class Communication
{
	public boolean whisper(String id, String message)
	{
		boolean success = false;
		///TODO Check that these are representable as strings
		//String id = arg1.tojstring();
		//String contents = arg2.tojstring();
		
		if (World.registered(id))//arg1.tojstring()))
		{
			World.getJDA().getUserById(id).openPrivateChannel().queue(
					(channel) -> channel.sendMessage(message).queue());//contents));
			
			success = true;
		}
		
		return success;
	}
	
	public void shout(Room location, String message)
	{
		//channel.sendMessage(message).queue();
		return;
	}
	
	public LuaValue stripID(String str)
	{
		/// TODO check if arg is really a string
		return LuaValue.valueOf(Username.stripID(str));//arg.tojstring()));
	}
}
