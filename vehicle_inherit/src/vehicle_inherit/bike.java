package vehicle_inherit;

public class bike extends engine
{
   private String braketype;
   public bike(String enginetype,int horsepower,String braketype)
   {
	   super(enginetype,horsepower);
	   this.braketype=braketype;
   }
   public String getbraketype()
	{
		return braketype;
	}
	public void setbraketype(String braketype)
	{
		this.braketype=braketype;
	}
}