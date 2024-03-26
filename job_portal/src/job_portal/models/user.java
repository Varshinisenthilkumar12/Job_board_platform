package job_portal.models;

public class user {
	private String firstname;
	private String lastname;
	private String email;
	private int age;
	private String password;
	
	public user()
	{
	}
	user(String firstname,String lastname,String email)
	{
		this.firstname=firstname;
		this.lastname=lastname;
		this.email=email;
	}
	
	public void setAge(int age){
		this.age=age;
	}
	public int getAge(){
		return this.age;
	}
	
	public String getfirstname(){
		return firstname;
	}
	public void setfirstname(String firstname){
		this.firstname=firstname;
	}
	
	public String getlastname(){
		return lastname;
	}
	public void setlastname(String lastname){
		this.lastname=lastname;
	}
	
	public String getemail(){
		return email;
	}
	public void setemail(String email){
		this.email=email;
	}
	
	public String getpassword(){
		return password;
	}
	public void setpassword(String password){
		this.password=password;
	}

}
