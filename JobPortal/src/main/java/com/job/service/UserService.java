package com.job.service;
import com.job.api.UserServlet;
import com.job.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT * FROM users WHERE user_id = ?";

        try (Connection connection = UserServlet.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setUserId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setUserType(resultSet.getString("user_type"));
                    user.setName(resultSet.getString("name"));
                    user.setContactInfo(resultSet.getString("contact_info"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean createUser(User user) {
        String query = "INSERT INTO users (username, password, email, user_type, name, contact_info) VALUES (?, ?, ?, ?, ?, ?)";
        boolean isCreated = false;

        try (Connection connection = UserServlet.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUserType());
            statement.setString(5, user.getName());
            statement.setString(6, user.getContactInfo());

            int rowsAffected = statement.executeUpdate();
            isCreated = rowsAffected > 0;

            if (isCreated) {
                System.out.println("User created successfully.");
            } else {
                System.out.println("Failed to create user.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
        }

        return isCreated;
    }
}

