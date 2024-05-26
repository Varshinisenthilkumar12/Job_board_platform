package com.job.api;
import com.job.models.User;
import com.job.service.UserService;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private UserService userService = new UserService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 int userId = Integer.parseInt(request.getParameter("userId"));
         User user = userService.getUserById(userId);

         if (user != null) {
             response.setContentType("application/json");
             
             String json = "{ \"id\": " + user.getId() +
                           ", \"username\": \"" + user.getUsername() + "\"" +
                           ", \"password\": \"" + user.getPassword() + "\"" +
                           ", \"email\": \"" + user.getEmail() + "\"" +
                           ", \"userType\": \"" + user.getUserType() + "\"" +
                           ", \"name\": \"" + user.getName() + "\"" +
                           ", \"contactInfo\": \"" + user.getContactInfo() + "\" }";

             response.getWriter().write(json);
         } else {
             response.setStatus(HttpServletResponse.SC_NOT_FOUND);
             response.getWriter().write("{ \"message\": \"User not found\" }");
         }
    } 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }

        try {
            JSONObject json = new JSONObject(jsonBuffer.toString());
            User u = new User();
            u.setUsername(json.getString("username"));
            u.setPassword(json.getString("password"));
            u.setEmail(json.getString("email"));
            u.setUserType(json.getString("userType"));
            u.setName(json.getString("name"));
            u.setContactInfo(json.getString("contactInfo"));

           /* System.out.println("username: " + u.getUsername());
            System.out.println("\npassword: " + u.getPassword());
            System.out.println("\nemail: " + u.getEmail());
            System.out.println("\nuserType: " + u.getUserType());
            System.out.println("\nname: " + u.getName());
            System.out.println("\ncontactInfoParam: " + u.getContactInfo());*/

            boolean result = userService.createUser(u);
            System.out.println(result);

            if (result) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.getWriter().write("{ \"message\": \"User created successfully\" }");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{ \"message\": \"Failed to create user\" }");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{ \"message\": \"Invalid JSON data\" }");
        }
    }

}