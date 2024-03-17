package job_portal;
import Services.loginservice;
import job_portal.models.user;

public class main {

	public static void main(String[] args) {
			String name = "test";
			String password = "pass";
			String email = "email";
			loginservice s = new loginservice();
			
			
			//signup
			boolean isCreated = s.createuser(name,email,password);
			if(isCreated) {
				System.out.println("User created");
			}else {
				System.out.println("User not created");
			}
			
			//login
			user u = s.getuserbyemail(email);
			if(u!=null) {
				System.out.println("User details"+u.getfirstname());
			}else {
				System.out.println("User not available");
			}
		}

	}


