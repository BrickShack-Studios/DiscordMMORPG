package lua.communication;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

import net.dv8tion.jda.core.entities.impl.UserImpl;
import world.World;

/**
 * Encapsulates the Lua command to send a message to a user's DMs.
 * 
 * @author Frank
 */
public class whisper extends TwoArgFunction
{

	@Override
	/**
	 * Called by the Lua function.
	 * 
	 * @author Frank
	 * @param  arg1 The ID of the recipient
	 * @param  arg2 The message body
	 * @return True/False, depending on whether the whisper was successful
	 */
	public LuaValue call(LuaValue arg1, LuaValue arg2)
	{
		boolean success = false;
		///TODO Check that these are representable as strings
		String id = arg1.tojstring();
		String contents = arg2.tojstring();
		
		if (World.registered(arg1.tojstring()))
		{
			World.getJDA().getUserById(id).openPrivateChannel().queue(
					(channel) -> channel.sendMessage(contents));
			
			success = true;
		}
		
		return LuaValue.valueOf(success);
	}

}
