package services;
import java.util.ArrayList;
import java.util.List;
import job_portal_application.models.User;
public class AuthenticationService 
{
	private List<User> users;
		public AuthenticationService() 
	    {
	        this.users = new ArrayList<>();
	    }
		public boolean registerUser(String username, String password, String role) 
	    {
	        for (User user : users) 
	        {
	            if (user.getUsername().equals(username)) 
	            {
	                return false; 
	            }
	        }
	        users.add(new User(username, password, role));
	        return true;
	    }
		public User login(String username, String password)
	    {
	        for (User user : users) 
	        {
	            if (user.getUsername().equals(username) && user.getPassword().equals(password)) 
	            {
	                return user; 
	            }
	        }
	        return null; 
	    }
}
