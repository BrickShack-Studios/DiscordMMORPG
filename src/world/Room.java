package world;

import java.util.ArrayList;

import entity.Entity;
import entity.Item;

/**
 * A `Room` is a space in which `Entity`s can exist in. They have exits
 * which take the form of `Door`s and lead to more `Room`s.
 * 
 * @see entity.Entity
 * @see world.Door
 */
public class Room
{
	private String name;
	private RoomType type;
	private ArrayList<Door> exits;
	private ArrayList<Item> items;
	private ArrayList<Entity> entities;
	
	public Room(String name, RoomType type)
	{
		this.name = name;
		this.type = type;
		
		this.exits = new ArrayList();
		this.items = new ArrayList();
		this.entities = new ArrayList();
	}
	
	/**
	 * Gives a view of the `Room`, listing all `Door`s, `Entity`s, and `Item`s.
	 * 
	 * @return A string containing the view from inside the room.
	 * 
	 * @todo Add more grammar support, such as `an egg` vs `a toaster`.
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("You are in " + this.name + ".\n\n");
		if (items.size() > 0)
		{
			sb.append("You can see");
			char header = 'a';
			
			if (items.size() > 1) 
			{
				sb.append(":\n");
				header = 'A';
			}
			else
				sb.append(" ");
			
			for (Item i : items)
			{
				sb.append(header);
				if ("aeiou".contains(i.getName().toLowerCase().subSequence(0, 1)))
					sb.append("n");
				
				sb.append(" " + i.getName() + "\n");
			}
			
			sb.append("\n");
		}
		
		if (entities.size() > 0)
		{
			sb.append("There is");
			char header = 'a';
			
			if (entities.size() > 1)
			{
				sb.append(":\n");
				header = 'A';
			}
			else
				sb.append(" ");
				
			
			for (Entity e : entities)
			{
				sb.append(header);
				if ("aeiou".contains(e.getName().toLowerCase().subSequence(0, 1)))
					sb.append("n");
				
				sb.append(" " + e.getName() + "\n");
			}
			
			sb.append(" ");
		}
		
		sb.append("\n");
		
		for (Door d : exits)
		{
			if (d.getDestination().getType() == RoomType.ROOM)
			{
				//if ("aeiou".contains(d.getDescription()))
				sb.append("To " + d.getDirection() + " lies " + d.getDescription() + "\n");
			}
				
			else if (d.getDestination().getType() == RoomType.BUILDING)
				sb.append("There is " + d.getDestination().getName() + "\n");
			else
			{
				System.out.println("Critical Error: Unknown room type: " + d.getDestination().getType());
				return "";
			}
		}
		
		return sb.toString();
	}
	
	public Room addExit(Door d)
	{
		exits.add(d);
		return this;
	}
	
	public Room addItem(Item i)
	{
		items.add(i);
		return this;
	}
	
	public Room addEntity(Entity e)
	{
		entities.add(e);
		return this;
	}
	
	public Room removeEntity(Entity e)
	{
		entities.remove(e);
		return this;
	}

	public String getName()
	{
		return name;
	}

	public Room setName(String name)
	{
		this.name = name;
		return this;
	}

	public RoomType getType()
	{
		return type;
	}

	public Room setType(RoomType type)
	{
		this.type = type;
		return this;
	}

	public ArrayList<Door> getExits()
	{
		return exits;
	}

	public Room setExits(ArrayList<Door> exits)
	{
		this.exits = exits;
		return this;
	}

	public ArrayList<Item> getItems()
	{
		return items;
	}

	public Room setItems(ArrayList<Item> items)
	{
		this.items = items;
		return this;
	}

	public ArrayList<Entity> getEntities()
	{
		return entities;
	}

	public Room setEntities(ArrayList<Entity> entities)
	{
		this.entities = entities;
		return this;
	}
	
	public Item searchItem(ArrayList<Item> items, String name)
	{
		for (Item i : items)
			if (i.getName().equals(name))
				return i;
		
		return null;		
	}
}
