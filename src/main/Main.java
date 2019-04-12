package main;

import javax.security.auth.login.LoginException;

import lua.Parser;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import world.World;

public class Main 
{
	public static void main(String[] args)
	{
		JDA api = null;
		
		try 
		{
			api = new JDABuilder("token here")
					.addEventListener(new MainListener())
					.build();
		} 
		catch (LoginException e) 
		{
			e.printStackTrace();
		}
		
		World.init(api);
		//Parser.run("print 'Yo'");
	}
}
