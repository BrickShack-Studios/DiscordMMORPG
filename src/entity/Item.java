package entity;

import java.util.Map;

/**
 * An `Item` represents a special `Entity` in the game which is 
 * enchantable and holdable by some other `Entity`.
 * 
 * @class
 */
public class Item extends Entity
{
	/// Value is an important concept which will be used for
	/// balancing. In general, higher-valued items are harder to
	/// acquire, but can be used to craft more powerful gear.
	private long value;
	
	/// Weight influences attack potential and carrying capacity
	/// of the `Item`.
	private double weight;
	
	/// The recipe has a list of which items are required to craft this one.
	private Map<String, Integer> recipe;
	
	public Item(String name, long value, double weight)
	{
		super(name);
		this.value = value;
		this.weight = weight;
		return;
	}
	
	public Item(String name, long value, double weight, Map<String, Integer> recipe)
	{
		super(name);
		this.value = value;
		this.weight = weight;
		this.recipe = recipe;
		return;
	}

	public String getName()
	{
		return name;
	}

	public Item setName(String name)
	{
		this.name = name;
		return this;
	}

	public long getValue()
	{
		return value;
	}

	public Item setValue(long value)
	{
		this.value = value;
		return this;
	}

	public double getWeight()
	{
		return weight;
	}

	public Item setWeight(double weight)
	{
		this.weight = weight;
		return this;
	}

	public Map<String, Integer> getRecipe()
	{
		return recipe;
	}

	public Item setRecipe(Map<String, Integer> recipe)
	{
		this.recipe = recipe;
		return this;
	}
	
	
}
