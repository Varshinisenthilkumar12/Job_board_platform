package vehicle_inherit;

public class car extends engine
{
	private String body_style;
    private int seating_capacity;
    public car(String enginetype,int horsepower,String body_style,int seating_capacity)
    {
    	super(enginetype,horsepower);
    	this.body_style=body_style;
    	this.seating_capacity=seating_capacity;
    }
    public String getbody_style()
	{
		return body_style;
	}
	public void setbody_style(String body_style)
	{
		this.body_style=body_style;
	}
	public int seating_capacity()
	{
		return seating_capacity;
	}
	public void setseating_capacity(int seating_capacity)
	{
		this.seating_capacity=seating_capacity;
	}
}