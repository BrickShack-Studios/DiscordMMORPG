package world;

/**
 * A `Door` is 
 * 
 * @class
 */
public class Door
{
	private String direction;
	private String description;
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
