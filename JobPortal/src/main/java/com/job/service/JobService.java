package com.job.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.job.models.*;

public class JobService {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/job_portal";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Varsh@12";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
    
    public List<Jobs> getAllJobs() {
        List<Jobs> jobsList = new ArrayList<>();
        String query = "SELECT * FROM jobs";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Jobs job = new Jobs();
                job.setJobId(resultSet.getInt("job_id"));
                job.setEmployerId(resultSet.getInt("employer_id"));
                job.setTitle(resultSet.getString("title"));
                job.setDescription(resultSet.getString("description"));
                job.setLocation(resultSet.getString("location"));
                job.setSalary(resultSet.getDouble("salary"));
                job.setContactEmail(resultSet.getString("contact_email"));
                
                jobsList.add(job);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobsList;
    }

    public Jobs getJobById(int jobId) {
        Jobs job = null;
        String query = "SELECT * FROM jobs WHERE job_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, jobId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    job = new Jobs();
                    job.setJobId(resultSet.getInt("job_id"));
                    job.setEmployerId(resultSet.getInt("employer_id"));
                    job.setTitle(resultSet.getString("title"));
                    job.setDescription(resultSet.getString("description"));
                    job.setLocation(resultSet.getString("location"));
                    job.setSalary(resultSet.getDouble("salary"));
                    job.setContactEmail(resultSet.getString("contact_email"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return job;
    }

    public boolean createJob(Jobs job) {
        String query = "INSERT INTO jobs (employer_id, title, description, location, salary, contact_email) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, job.getEmployerId());
            statement.setString(2, job.getTitle());
            statement.setString(3, job.getDescription());
            statement.setString(4, job.getLocation());
            statement.setDouble(5, job.getSalary());
            statement.setString(6, job.getContactEmail());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateJob(Jobs job) {
        String query = "UPDATE jobs SET employer_id = ?, title = ?, description = ?, location = ?, salary = ?, contact_email = ? WHERE job_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, job.getEmployerId());
            statement.setString(2, job.getTitle());
            statement.setString(3, job.getDescription());
            statement.setString(4, job.getLocation());
            statement.setDouble(5, job.getSalary());
            statement.setString(6, job.getContactEmail());
            statement.setInt(7, job.getJobId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteJob(int jobId) {
        String query = "DELETE FROM jobs WHERE job_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, jobId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}