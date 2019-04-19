package entity;

import java.util.ArrayList;

import world.room.Door;

/**
 * A `Player` represents an `Entity` controlled by a human via
 * text commands. `Player`s can influence the world around them in
 * nuanced ways via gameplay.
 * 
 * \class
 */
public class Player extends Entity
{
	/// The unique Discord ID associated with every account.
	private String id;
	
	/// The experience points the player has. 
	private long xp;
	
	/// The current level of the player.
	private int level;
	
	/// The list of `Item`s owned by the player.
	/// @see item.Item
	private ArrayList<Item> inventory;
	
	public Player(String name, String id)
	{
		super(name);
		
		this.id = id;
		this.inventory = new ArrayList<>();
		this.xp = 0;
	}
	
	/**
	 * Returns a textual picture of everything going on in the
	 * current `Room`, given by the player's `location`.
	 * 
	 * @return The string containing the textual description of the `location`.
	 * 
	 * @see entity.Player.location 
	 */
	public String look()
	{
		return location.toString();
	}
	
	/**
	 * Tries to move from the current `location` to a new one. The destination
	 * is given by the `goal` parameter. A match is performed by searching
	 * if the `goal` is anywhere in the potential exits from the player's
	 * current `location`.
	 * 
	 * @param goal	A string containing a search sequence to search the
	 * 				exits for.
	 * @return		True if the move was successful, false otherwise.
	 */
	public boolean move(String goal)
	{
		for (Door d : location.getExits())
		{
			if (d.getDirection().toLowerCase().contains(goal.toLowerCase()) ||
					d.getDestination().getName().toLowerCase().contains(goal.toLowerCase()) ||
					d.getDescription().toLowerCase().contains(goal.toLowerCase()))
			{
				this.location.removeEntity(this);
				this.location = d.getDestination();
				this.location.addEntity(this);
				return true;
			}
		}
		
		return false;
	}
	
	public void addItem(Item item)
	{
		inventory.add(item);
		return;
	}
	
	public String getID()
	{
		return id;
	}
}
