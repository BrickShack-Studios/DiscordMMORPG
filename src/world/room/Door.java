package world.room;

/**
 * A `Door` is a one-way portal from one `Room` to another.
 * 
 * \class
 */
public class Door
{
	/// The direction the door is in from the player's location in the `Room`.
	private String direction;
	
	/// A description of what this `Door` looks like.
	private String description;
	
	/// A reference to the `Room` this `Door` should lead to.
	private Room destination;
	
	public Door(String direction, String description, Room destination)
	{
		this.direction = direction;
		this.description = description;
		this.destination = destination;
	}
	
	public String getDirection()
	{
		return direction;
	}

	public Door setDirection(String direction)
	{
		this.direction = direction;
		return this;
	}

	public String getDescription()
	{
		return description;
	}

	public Door setDescription(String description)
	{
		this.description = description;
		return this;
	}

	public Room getDestination()
	{
		return destination;
	}

	public Door setDestination(Room destination)
	{
		this.destination = destination;
		return this;
	}
}
