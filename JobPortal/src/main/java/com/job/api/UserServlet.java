package com.job.api;
import com.job.models.User;
import com.job.service.UserService;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdParam = request.getParameter("userId");
        if (userIdParam != null) {
            try {
                int userId = Integer.parseInt(userIdParam);
                User user = userService.getUserById(userId);

                if (user != null) {
                    response.setContentType("application/json");
                    response.getWriter().write(new JSONObject(user).toString());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{ \"message\": \"User not found\" }");
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{ \"message\": \"Invalid user ID format\" }");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{ \"message\": \"Invalid user ID\" }");
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
            User user = new User();
            user.setUsername(json.getString("username"));
            user.setPassword(json.getString("password"));
            user.setEmail(json.getString("email"));
            user.setUserType(json.getString("userType"));
            user.setName(json.getString("name"));
            user.setContactInfo(json.getString("contactInfo"));

            boolean isCreated = userService.createUser(user);

            if (isCreated) {
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

    public static Connection getConnection() throws SQLException {
        String JDBC_URL = "jdbc:mysql://localhost:3306/job_portal";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "Varsh@12";
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
