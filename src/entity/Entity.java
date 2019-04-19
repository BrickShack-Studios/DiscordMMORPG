package entity;

import world.room.Room;

/**
 * An `Entity` is any "thing" (object, creature, etc) which can exist in a
 * room. They exist at a location and are named.
 * 
 * @see world.room.Room
 * \class
 */
public class Entity
{
	/// The `Room` this `Entity` resides in.
	protected Room location;
	
	/// The name of this `Entity`.
	protected String name;
	
	public Entity(String name)
	{
		this.name = name;
		
		return;
	}
	
	public Entity(String name, Room Location)
	{
		this.name = name;
		this.location = location;
		
		return;
	}
	
	public Room getLocation()
	{
		return location;
	}
	public Entity setLocation(Room location)
	{
		this.location = location;
		return this;
	}
	public String getName()
	{
		return name;
	}
	public Entity setName(String name)
	{
		this.name = name;
		return this;
	}
}
