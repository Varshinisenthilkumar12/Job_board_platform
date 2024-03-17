package vehicle_inherit;

public class main
{
	public static void main(String[] args)
	{
		car c = new car("Diesel engine",300,"hatchback",8);
		bike b = new bike("Electric",100,"Drum");
		System.out.println("Car details:");
		System.out.println("engine type:"+c.getenginetype());
		System.out.println("horsepower:"+c.gethorsepower());
		System.out.println("bodystyle:"+c.getbody_style());
		System.out.println("seating capacity:"+c.seating_capacity());
		
		System.out.println("\nBike details:");
		System.out.println("engine type:"+b.getenginetype());
		System.out.println("horsepower:"+b.gethorsepower());
		System.out.println("braketype:"+b.getbraketype());
	}
}
