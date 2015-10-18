package Assignment2;

abstract public class Attribute 
{
	public String name;
	public String value;
	public boolean isUnknown;
	private double surrogate;
	
	
	public Attribute(String name, String value) 
	{
		this.name = name;
		this.value = value;
		this.isUnknown = false;			
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setSurrogate(double surrogate) {
		this.surrogate = surrogate;
	}

	public double getSurrogate() {
		return surrogate;
	}

	public void setUnknown(boolean isUnknown) {
		this.isUnknown = isUnknown;
	}

	public boolean isUnknown() {
		return isUnknown;
	}
}
