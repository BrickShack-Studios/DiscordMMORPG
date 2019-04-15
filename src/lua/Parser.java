package lua;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import lua.communication.Communication;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Parser
{
	private static Pattern codeExtract = Pattern.compile("(?<=```lua)([\\s\\S]*)(?=```)");
	
	public static void run(String program)
	{
		Globals globals = JsePlatform.standardGlobals();
		
		LuaValue communication = CoerceJavaToLua.coerce(new Communication());
		
		globals.set("communication", communication);
		
		LuaValue chunk = globals.load(program);
		chunk.call();
		
		return;
	}
	
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
