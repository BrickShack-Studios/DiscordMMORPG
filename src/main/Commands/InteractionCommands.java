package main.Commands;

import java.util.ArrayList;

import entity.Item;
import net.dv8tion.jda.core.entities.MessageChannel;
import world.World;

public class InteractionCommands
{
	public static void go(String id, String destination, MessageChannel channel)
	{
		if (!World.findPlayerByID(id).move(destination))
			channel.sendMessage("You aren't sure where that is.").queue();
		
		return;
	}
	
	public static void pickup(String id, String[] words, MessageChannel channel)
	{
		ArrayList<Item> lst = World.findPlayerByID(id).getLocation().getItems();
		if (words.length <= 1)
		{
			channel.sendMessage("Warning: Not enough information!").queue();
			return;
		}
		
		String itemName = "";
		for (int i = 1; i < words.length; i++)
		{
			itemName += words[i];
			if (i == words.length - 1)
				break;
			itemName += " ";					
		}
		
		Item item = World.findPlayerByID(id).getLocation().searchItem(lst, itemName);
		if (item!= null)
		{
			World.findPlayerByID(id).addItem(item);
			channel.sendMessage("You have picked up the " + itemName + "!").queue();
			lst.remove(item);
			return;
		}
		else
		{
			channel.sendMessage(itemName + " not found!").queue();
			return;
		}
	}
	
	public static void look(String id, MessageChannel channel)
	{
		channel.sendMessage(World.findPlayerByID(id).look()).queue();
		return;
	}
}
