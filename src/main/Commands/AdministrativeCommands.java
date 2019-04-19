package main.Commands;

import entity.Player;
import net.dv8tion.jda.core.entities.MessageChannel;
import world.World;

public class AdministrativeCommands
{
	/**
	 * The 'register' method allows a user to register with a username of its
	 * choosing as long as it is considered valid and doesn't exist already.
	 * 
	 * @param id is the Player's ID.
	 * @param words is the input from the Player.
	 * @param channel is the basic output for the bot.
	 */
	public static void register(String id, String[] words, MessageChannel channel)
	{
		if (words.length < 2)
		{
			channel.sendMessage("Correct usage: `> register myUsername`").queue();
			return;
		}
		else if (words.length > 2)
		{
			channel.sendMessage("Usernames must not contain spaces").queue();
			return;
		}
		
		Player p = World.findPlayerByID(id);
		if (p == null)
		{
			p = new Player(words[1], id);
			if (World.addPlayer(p))
				channel.sendMessage("Welcome to the world, " + p.getName() + 
						"!\nTry looking around with `> look`").queue();
			else
				channel.sendMessage("You're already registered!").queue();
		}
		else
			channel.sendMessage("You're already registered as " + p.getName()).queue();
		
		return;
	}
}
