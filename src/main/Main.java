package main;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.security.auth.login.LoginException;

import lua.Parser;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import world.World;

/**
 * The `Main` class handles the pre-initialization of the bot, such
 * as connecting to Discord itself.
 * 
 * @class
 */
public class Main 
{
	/**
	 * Loads in the bot token from the `key` file. Prevents the token
	 * from being anywhere in the code.
	 * 
	 * @return The token, if found. Otherwise, an empty string.
	 */
	private static String getKey()
	{
		String key  = "";
		
		try
		{
			key = Files.readAllLines(Paths.get("key")).get(0);
		} 
		catch (IOException e) {}
		
		return key;
	}
	
	/**
	 * Connects the bot to the Discord servers and then initializes the world.
	 * @param args	Unusued launch arguments
	 * 
	 * @see world.World
	 * @see world.Initializer
	 */
	public static void main(String[] args)
	{
		JDA api = null;
		String key = getKey();
		
		if (key.isEmpty())
		{
			System.out.println("Keyfile not found!");
			return;
		}
		
		try 
		{
			api = new JDABuilder(key)
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
