package world;

import entity.Entity;
import item.Item;

public class Initializer
{
	public static void init()
	{
		World.addRoom(new Room("The Town Center", RoomType.ROOM)
				.addEntity(new Entity("Dummy"))
				.addItem(new Item("Thing", 0, 1))
				.addExit(new Door(Dir.YN, "a cave", new Room("A Cave", RoomType.ROOM)))
				.addExit(new Door(Dir.YS, "a house", new Room("A House", RoomType.BUILDING))));
		
		return;
	}
}
