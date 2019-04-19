package main.Commands;

import entity.Player;
import lua.api.Communication;
import net.dv8tion.jda.core.entities.MessageChannel;
import world.World;

public class CommunicationCommands
{
	/**
	 * The 'whisper' method allows the Player to communicate with another Player
	 * as long as 
	 * @param id
	 * @param words
	 * @param channel
	 */
	public static void whisper(String id, String[] words, MessageChannel channel)
	{
		if (words.length < 3)
		{
			channel.sendMessage("Correct usage: `>whisper <username> <message>`").queue();
			return;
		}
		
		String sendee = words[1];
		String msg = words[2];
		for (int i = 3; i < words.length; i++)
			msg += " " + words[i];
		
		Player recipient = World.findPlayerByName(sendee);
		if (recipient == null)
		{
			channel.sendMessage("Player " + sendee + " not found.").queue();
			return;
		}
		
		String sender = World.findPlayerByID(id).getName();
		new Communication().whisper(recipient.getID(), sender + ": " + msg);
		
		return;
	}
}
