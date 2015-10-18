package Assignment2;

import java.util.*;

public class TreeNode 
{
	public TreeNode parent;
	public TreeNode[] children;
	
	// This is the variable which holds all the data in the tree
	public ArrayList<Record> data;
	
	public double entropy;
	public boolean isUsed;
	public DiscreteAttribute testAttribute;
	

	public TreeNode() 
	{
		this.data = new ArrayList<Record>();
		this.entropy = 0.0;
		this.parent = null;
		this.children = null;
		this.isUsed = false;
	}
	
	public void setData(ArrayList<Record> data) 
	{
		this.data = data;
	}

	public ArrayList<Record> getData() 
	{
		return data;
	}
	
	public void setEntropy(double entropy) 
	{
		this.entropy = entropy;
	}

	public double getEntropy() 
	{
		return entropy;
	}
}
