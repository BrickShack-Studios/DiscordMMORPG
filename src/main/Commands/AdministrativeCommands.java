package main.Commands;

import entity.Player;
import net.dv8tion.jda.core.entities.MessageChannel;
import world.World;

public class AdministrativeCommands
{
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
