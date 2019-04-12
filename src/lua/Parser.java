package lua;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Parser
{
	public static void run(String program)
	{
		Globals globals = JsePlatform.standardGlobals();
		LuaValue chunk = globals.load(program);
		chunk.call();
		
		return;
	}
	
	public static void run(String program, MessageReceivedEvent event)
	{
		LuaValue luaGlobals = JsePlatform.standardGlobals();
		LuaValue luaVals    = CoerceJavaToLua.coerce(event);
		
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
}
