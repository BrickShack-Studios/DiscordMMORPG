package world;

import entity.Entity;
import entity.Item;
import world.room.Dir;
import world.room.Door;
import world.room.Room;
import world.room.RoomType;

/**
 * The `Initializer` is responsible for building a brand new, clean world.
 * 
 * \class
 */
public class Initializer
{
	/**
	 * Creates a clean world.
	 * 
	 * @see world.World
	 */
	public static void init()
	{
		Room townCenter = new Room("The Town Center", RoomType.ROOM)
							.addEntity(new Entity("Dummy"))
							.addItem(new Item("Thing", 0, 1));
		
		Room aHouse = new Room("A House", RoomType.BUILDING)
						.addExit(new Door(Dir.YS, "a door", townCenter))
						.addItem(new Item("Lamp", 5, 5));
		
		townCenter.addExit(new Door(Dir.YN, "a house", aHouse));
		
		World.addRoom(townCenter);
		
		return;
	}
}
