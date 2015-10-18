package Assignment2;

import java.util.ArrayList;

public class AttributeSet 
{
	public String name;
	public ArrayList<DiscreteAttribute> attributes;
	public double entropy;
	public boolean isUsed;
	
	public AttributeSet() 
	{
		attributes = new ArrayList<DiscreteAttribute>();
		entropy = -1;
		isUsed = false;
	}
	
	
}
