package main.Commands;

import java.util.ArrayList;

import entity.Item;
import net.dv8tion.jda.core.entities.MessageChannel;
import world.World;

public class InteractionCommands
{
	/**
	 * The 'go' method will allow the player to move to a certain location if
	 * the 'location' is considered to be legal.
	 * 
	 * @param id is the ID of the Player object.
	 * @param destination is where the Player wishes to move.
	 * @param channel is the basic output for the bot.
	 */
	public static void go(String id, String destination, MessageChannel channel)
	{
		if (!World.findPlayerByID(id).move(destination))
			channel.sendMessage("You aren't sure where that is.").queue();
		
		return;
	}
	
	/**
	 * The 'pickup' method allows the Player to pick up an item as long as it
	 * exists in the current Room.
	 * 
	 * @param id is the ID of the Player object.
	 * @param words is the input from the Player.
	 * @param channel is the basic output for the bot.
	 */
	public static void pickup(String id, String[] words, MessageChannel channel)
	{
		if (words.length <= 1)
		{
			channel.sendMessage("Warning: Not enough information!").queue();
			return;
		}
		
		ArrayList<Item> lst = World.findPlayerByID(id).getLocation().getItems();
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
	
	/**
	 * The 'drop' method allows the Player to drop an item as long as it is
	 * in the Player's inventory and adds it to the current Room.
	 * 
	 * @param id is the Player's ID.
	 * @param words is the input from the Player.
	 * @param channel is the basic output for the bot.
	 */
	public static void drop(String id, String[] words, MessageChannel channel)
	{
		if (words.length <= 1)
		{
			channel.sendMessage("Warning: Not enough information!").queue();
			return;
		}
		
		ArrayList<Item> lst = World.findPlayerByID(id).getInventory();
		ArrayList<Item> room = World.findPlayerByID(id).getLocation().getItems();
		
		String itemName = "";
		
		for (int i = 1; i < words.length; i++)
		{
			itemName += words[i];
			if (i == words.length - 1)
				break;
			itemName += " ";	
		}
		
		Item i = World.findPlayerByID(id).searchInventory(itemName);
		if (i != null)
		{
			channel.sendMessage("You dropped the " + itemName + "!").queue();
			lst.remove(i);
			room.add(i);
		}
		else
			channel.sendMessage(itemName + " not found!").queue();
		return;
	}
	
	/**
	 * The 'viewInventory' method allows the Player to view its inventory and
	 * displays messages based on its size.
	 * 
	 * @param id is the Player's ID.
	 * @param channel is the basic output for the bot.
	 */
	public static void viewInventory(String id, MessageChannel channel)
	{
		if (World.findPlayerByID(id).getInventory().size() == 0)
		{
			channel.sendMessage("There are no items in your inventory!").queue();
			return;
		}
		else if (World.findPlayerByID(id).getInventory().size() == 1)
		{
			channel.sendMessage("You have this item in your inventory:\n" + World.findPlayerByID(id).getInventory().get(0).getName()).queue();			
			return;
		}
		else
		{
			channel.sendMessage("You have these items in your inventory:").queue();
			ArrayList<Item> lst = World.findPlayerByID(id).getInventory();
			for (int i = 0; i < lst.size(); i++)
				channel.sendMessage(lst.get(i).getName()).queue();
			return;
		}
	}
	
	/**
	 * The 'look' method allows the Player to view its surroundings and displays
	 * messages accordingly.
	 * 
	 * @param id is the Player's ID.
	 * @param channel is the basic output for the bot.
	 */
	public static void look(String id, MessageChannel channel)
	{
		channel.sendMessage(World.findPlayerByID(id).look()).queue();
		return;
	}
}
