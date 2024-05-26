package com.job.service;
import com.job.models.Organization;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrganizationService {
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

    public List<Organization> getAllOrganizations() {
        List<Organization> organizations = new ArrayList<>();
        String query = "SELECT * FROM organizations";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Organization organization = new Organization();
                organization.setOrganizationId(resultSet.getInt("organization_id"));
                organization.setName(resultSet.getString("name"));
                organization.setDescription(resultSet.getString("description"));
                organization.setLocation(resultSet.getString("location"));
                organization.setIndustry(resultSet.getString("industry"));
                organization.setWebsite(resultSet.getString("website"));
                organization.setContactEmail(resultSet.getString("contact_email"));
                organization.setContactPhone(resultSet.getString("contact_phone"));

                organizations.add(organization);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return organizations;
    }

    public Organization getOrganizationById(int organizationId) {
        Organization organization = null;
        String query = "SELECT * FROM organizations WHERE organizationId = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, organizationId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    organization = new Organization();
                    organization.setOrganizationId(resultSet.getInt("organizationId"));
                    organization.setName(resultSet.getString("name"));
                    organization.setDescription(resultSet.getString("description"));
                    organization.setLocation(resultSet.getString("location"));
                    organization.setIndustry(resultSet.getString("industry"));
                    organization.setWebsite(resultSet.getString("website"));
                    organization.setContactEmail(resultSet.getString("contact_email"));
                    organization.setContactPhone(resultSet.getString("contact_phone"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return organization;
    }

    public boolean createOrganization(Organization organization) {
        String query = "INSERT INTO organizations (name, description, location, industry, website, ContactEmail, contactPhone) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, organization.getName());
            statement.setString(2, organization.getDescription());
            statement.setString(3, organization.getLocation());
            statement.setString(4, organization.getIndustry());
            statement.setString(5, organization.getWebsite());
            statement.setString(6, organization.getContactEmail());
            statement.setString(7, organization.getContactPhone());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateOrganization(Organization organization) {
        String query = "UPDATE organizations SET Name = ?, Description = ?, Location = ?, Industry = ?, Website = ?, ContactEmail = ?, ContactPhone = ? WHERE OrganizationID = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, organization.getName());
            statement.setString(2, organization.getDescription());
            statement.setString(3, organization.getLocation());
            statement.setString(4, organization.getIndustry());
            statement.setString(5, organization.getWebsite());
            statement.setString(6, organization.getContactEmail());
            statement.setString(7, organization.getContactPhone());
            statement.setInt(8, organization.getOrganizationId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public boolean deleteOrganization(int organizationId) {
        String query = "DELETE FROM organizations WHERE organizationId = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, organizationId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}