package main;

import java.net.URL;
import java.net.URLClassLoader;

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
			api = new JDABuilder("Token")
					.addEventListener(new MainListener())
					.build();
		} 
		catch (LoginException e) 
		{
			e.printStackTrace();
		}
		
		World.init(api);
		//Parser.run("");
		

		System.out.println("Classpaths: ");
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
        	System.out.println(url.getFile());
        }
	}
}
