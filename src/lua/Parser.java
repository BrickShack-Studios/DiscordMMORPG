package lua;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import lua.api.Communication;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * The `Parser` is responsible for running LuaJ code safely, and
 * for preloading the Java libraries from the {@link lua.api} package
 * into the LuaJ virtual machines.
 * 
 * @class
 */
public class Parser
{
	/// A regex which can remove the LuaJ code snipped from a player's message.
	private static Pattern codeExtract = Pattern.compile("(?<=```lua)([\\s\\S]*)(?=```)");
	
	/**
	 * Runs a LuaJ program given as a string at the casting player's location.
	 * 
	 * @param program The string to run as LuaJ code.
	 * 
	 * @see entity.Player
	 * @see world.Room
	 * @see main.MainListener ("cast")
	 */
	public static void run(String program)
	{
		Globals globals = JsePlatform.standardGlobals();
		
		LuaValue communication = CoerceJavaToLua.coerce(new Communication());
		
		globals.set("communication", communication);
		
		LuaValue chunk = globals.load(program);
		chunk.call();
		
		return;
	}
	
	/**
	 * Runs a LuaJ snippey given as a string from an enchanted `Item`.
	 * 
	 * @param program	The LuaJ snippet to run.
	 * @param event		The last message the user sent.
	 * 
	 * @see entity.Item
	 */
	public static void run(String program, MessageReceivedEvent event)
	{
		LuaValue luaGlobals = JsePlatform.standardGlobals();
		LuaValue luaVals    = CoerceJavaToLua.coerce(event);
		
		LuaValue communication = CoerceJavaToLua.coerce(new Communication());
		
		luaGlobals.set("communication", communication);
		
		LuaValue luaGetLine = luaGlobals.get("_onRun");
		
		if (!luaGetLine.isnil())
		{
			luaGetLine.call(luaVals);
		}
		else
		{
			/*
			event.getAuthor().openPrivateChannel().queue((channel) ->
					channel.sendMessage("Your script is lacking an `_onRun()` function!").queue());
			*/
			
			event.getChannel().sendMessage("Your script is lacking an `_onRun()` function!").queue();
		}
		
		return;
	}
	
	/**
	 * Takes in a `> cast` command and returns the code snippet alone.
	 * 
	 * @param raw	The full user text.
	 * @return		The LuaJ code snippet contained within, if any.
	 * 
	 * @see main.MainListener ("cast")
	 */
	public static String extractCode(String raw)
	{
		Matcher match = codeExtract.matcher(raw);
		if (!match.find())
		{
			/// TODO Tell user he goofed
		}
		
		return match.group(0);
	}
}
