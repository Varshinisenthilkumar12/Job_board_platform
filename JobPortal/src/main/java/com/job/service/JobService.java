package com.job.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.job.models.Job;

public class JobService {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/job_portal";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Varsh@12";

    private static final String SELECT_ALL_JOBS = "SELECT * FROM jobs";
    private static final String SELECT_JOB_BY_ID = "SELECT * FROM jobs WHERE job_id = ?";
    private static final String INSERT_JOB = "INSERT INTO jobs (user_id, title, description, location, salary, post_date, expiry_date, other_details) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_JOBS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Job job = createJobFromResultSet(resultSet);
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    public Job getJobById(int jobId) {
        Job job = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_JOB_BY_ID)) {
            statement.setInt(1, jobId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    job = createJobFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return job;
    }

    public void addJob(Job job) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT_JOB)) {
            statement.setInt(1, job.getUserId());
            statement.setString(2, job.getTitle());
            statement.setString(3, job.getDescription());
            statement.setString(4, job.getLocation());
            statement.setDouble(5, job.getSalary());
            statement.setTimestamp(6, job.getPostDate());
            statement.setDate(7, job.getExpiryDate());
            statement.setString(8, job.getOtherDetails());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Job createJobFromResultSet(ResultSet resultSet) throws SQLException {
        Job job = new Job();
        job.setJobId(resultSet.getInt("job_id"));
        job.setUserId(resultSet.getInt("user_id"));
        job.setTitle(resultSet.getString("title"));
        job.setDescription(resultSet.getString("description"));
        job.setLocation(resultSet.getString("location"));
        job.setSalary(resultSet.getDouble("salary"));
        job.setPostDate(resultSet.getTimestamp("post_date"));
        job.setExpiryDate(resultSet.getDate("expiry_date"));
        job.setOtherDetails(resultSet.getString("other_details"));
        return job;
    }
}
