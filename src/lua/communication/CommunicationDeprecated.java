package lua.communication;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;

import tools.Username;
import world.World;

/**
 * Deprecated in favor of {@link lua.communication.Communication}}
 * However, this file will be kept as it outlines an alternative method
 * to accomplish the same goal, even though it never worked.
 * 
 * Commands used for communicating textually.
 * @deprecated
 */
public class CommunicationDeprecated extends TwoArgFunction
{
	public CommunicationDeprecated() {}

	@Override
	public LuaValue call(LuaValue modname, LuaValue env)
	{
		LuaValue library = tableOf();
		library.set("whisper", new Whisper());
		library.set("stripID", new StripID());
		env.set("communication", library);
		return library;
	};
	
	static class Whisper extends TwoArgFunction
	{

		@Override
		/**
		 * Called by the Lua function.
		 * 
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
	
	static class StripID extends OneArgFunction
	{
		@Override
		/**
		 * A wrapper for the {@code Username.stripID()} function.
		 * 
		 * @see       Username.stripID()
		 * @param arg The LuaValue ostensibly containing a string to parse.
		 * @return    A LuaValue containing the results of {@code stripID}
		 */
		public LuaValue call(LuaValue arg)
		{
			/// TODO check if arg is really a string
			return LuaValue.valueOf(Username.stripID(arg.tojstring()));
		}
	}
}
