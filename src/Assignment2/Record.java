package Assignment2;

import java.util.ArrayList;

public class Record 
{

	String conversion;
	String timeStamp;
	
	// Features
	double maxBid; 
	double minBid;
	double averageBid;
	double maxAsk;
	double minAsk;
	double averageAsk;
	double maxDelta;
	double minDelta;
	double averageDelta;
	
	// Labels
	int bidDirection; 
	int askDirection;	
	
	public ArrayList<DiscreteAttribute> attributes;
	
	public Record(String[] line) 
	{
		this.conversion = line[0];
		this.timeStamp = line[1];
		this.maxBid = Double.parseDouble(line[2]);
		this.minBid = Double.parseDouble(line[3]);
		this.averageBid = Double.parseDouble(line[4]);
		
		this.maxAsk = Double.parseDouble(line[5]);
		this.minAsk = Double.parseDouble(line[6]);
		this.averageAsk = Double.parseDouble(line[7]);
		
		this.maxDelta = Double.parseDouble(line[8]);
		this.minDelta = Double.parseDouble(line[9]);
		this.averageDelta = Double.parseDouble(line[10]);
		
		this.bidDirection = Integer.parseInt(line[11]);
		this.askDirection = Integer.parseInt(line[12]);
		
		attributes = new ArrayList<DiscreteAttribute>();
		attributes.add(new DiscreteAttribute("maxBid", maxBid+""));
		attributes.add(new DiscreteAttribute("minBid", minBid+""));
		attributes.add(new DiscreteAttribute("averageBid", averageBid+""));
		
		attributes.add(new DiscreteAttribute("maxAsk", maxAsk+""));
		attributes.add(new DiscreteAttribute("minAsk", minAsk+""));
		attributes.add(new DiscreteAttribute("averageAsk", averageAsk+""));
		
		attributes.add(new DiscreteAttribute("maxDelta", maxDelta+""));
		attributes.add(new DiscreteAttribute("minDelta", minDelta+""));
		attributes.add(new DiscreteAttribute("averageDelta", averageDelta+""));
		
		attributes.add(new DiscreteAttribute("bidDirection", bidDirection+""));
		attributes.add(new DiscreteAttribute("askDirection", askDirection+""));
		
	}
	
	public Record() 
	{
		
	}
	
	

	public ArrayList<DiscreteAttribute> getAttributes() 
	{
		return attributes;
	}
	
	public void setAttributes(ArrayList<DiscreteAttribute> attributes) 
	{
		this.attributes = attributes;
	}
		
}
