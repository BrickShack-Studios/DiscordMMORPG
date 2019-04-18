package world;

import java.util.ArrayList;

import entity.Player;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 * The `World` is the universe, as far as the game is concerned. All
 * that exists in the game is kept track of here.
 *
 * @see entity.Entity
 * @see world.Room
 */
public class World
{
	private static JDA jda;
	
	private static ArrayList<Player> players;
	private static ArrayList<Room> rooms;
	
	/**
	 * Sets up the world for play.
	 * @param jda_ The Discord connection to keep track of.
	 */
	public static void init(JDA jda_)
	{
		jda = jda_;
		
		players = new ArrayList<Player>();
		rooms = new ArrayList<Room>();
		
		Initializer.init();
		
		return;
	}

	/**
	 * Adds a new `Player` to the world.
	 * @param p			The `Player` to add.
	 * @param channel	The channel he joined from.
	 * 
	 * @see entity.Player
	 */
	public static void addPlayer(Player p, MessageChannel channel)
	{
		if (registered(p.getID()))
		{
			channel.sendMessage("You're already registered!").queue();
			System.out.println("Warning: Preregistered user tried registering");
			return;
		}
		
		players.add(p);
		p.setLocation(rooms.get(0));
		rooms.get(0).addEntity(p);
		
		System.out.println("Added player " + p.getName() + " with id " + p.getID());
		channel.sendMessage("Welcome to the world, " + p.getName() + 
				"!\nTry looking around with `> look`").queue();
		
		return;
	}
	
	/**
	 * Scans the `Player`s currently in the game by their `id` in order
	 * to return a specific one.
	 * 
	 * @param id The ID to search for.
	 * @return	 The found `Player`, if any.
	 * 
	 * @see entity.Player
	 */
	public static Player findPlayerByID(String id)
	{
		for (Player p : players)
			if (p.getID().equals(id))
				return p;
		
		return null;
	}
	
	public static Player findPlayerByName(String username)
	{
		for (Player p : players)
			if (p.getName().equals(username))
				return p;
		
		return null;
	}
	
	public static void addRoom(Room r)
	{
		rooms.add(r);
		return;
	}
	
	public static boolean registered(String id)
	{
		for (Player p : players)
			if (p.getID().equals(id))
				return true;
		
		return false;
	}

	public static ArrayList<Player> getPlayers()
	{
		return players;
	}
	
	public static JDA getJDA()
	{
		return jda;
	}
	
}
