package vehicle_inherit;

public class engine 
{
	private String enginetype;
	private int horsepower;
	public engine(String enginetype,int horsepower)
	{
		this.enginetype = enginetype;
		this.horsepower = horsepower;
	}
	public String getenginetype()
	{
		return enginetype;
	}
	public void setenginetype(String enginetype)
	{
		this.enginetype=enginetype;
	}
	public int gethorsepower()
	{
		return horsepower;
	}
	public void sethorsepower(int horsepower)
	{
		this.horsepower=horsepower;
	}
}
