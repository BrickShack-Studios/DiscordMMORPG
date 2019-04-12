package entity;

import java.util.ArrayList;

import item.Item;
import net.dv8tion.jda.core.entities.MessageChannel;

public class Player extends Entity
{
	private String id;
	private long xp;
	private int level;
	private ArrayList<Item> inventory;
	
	public Player(String name, String id)
	{
		super(name);
		
		this.id = id;
		this.inventory = new ArrayList<>();
		this.xp = 0;
	}
	
	public Player look(MessageChannel channel)
	{
		channel.sendMessage(location.toString()).queue();
		return this;
	}

	public String getID()
	{
		return id;
	}
}
