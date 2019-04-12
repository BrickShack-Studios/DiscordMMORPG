package entity;

import world.Room;

public class Entity
{
	protected Room location;
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
