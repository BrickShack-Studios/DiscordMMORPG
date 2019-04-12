package item;

import java.util.Map;

public class Item
{
	private String name;
	private long value;
	private double weight;
	
	private Map<String, Integer> recipe;
	
	public Item(String name, long value, double weight)
	{
		this.name = name;
		this.value = value;
		this.weight = weight;
		return;
	}
	
	public Item(String name, long value, double weight, Map<String, Integer> recipe)
	{
		this.name = name;
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
