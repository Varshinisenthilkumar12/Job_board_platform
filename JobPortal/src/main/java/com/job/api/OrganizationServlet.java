package com.job.api;
import com.job.models.Organization;
import com.job.service.OrganizationService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class OrganizationServlet extends HttpServlet {
    private OrganizationService organizationService;
    public void init() throws ServletException {
        organizationService = new OrganizationService();
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String organizationIdParam = req.getParameter("organizationId");
        try {
        if (organizationIdParam == null) {
            List<Organization> organizations = organizationService.getAllOrganizations();
            JSONArray organizationsJsonArray = new JSONArray();

            for (Organization organization : organizations) {
                JSONObject organizationJson = new JSONObject();
                organizationJson.put("organizationId", organization.getOrganizationId());
                organizationJson.put("name", organization.getName());
                organizationJson.put("description", organization.getDescription());
                organizationJson.put("location", organization.getLocation());
                organizationJson.put("industry", organization.getIndustry());
                organizationJson.put("website", organization.getWebsite());
                organizationJson.put("contactEmail", organization.getContactEmail());
				organizationJson.put("contactPhone", organization.getContactPhone());
				
                organizationsJsonArray.put(organizationJson);
            }

            resp.setContentType("application/json");
            resp.getWriter().write(organizationsJsonArray.toString());
        } else {
            try {
                int organizationId = Integer.parseInt(organizationIdParam);
                Organization organization = organizationService.getOrganizationById(organizationId);

                if (organization != null) {
                    JSONObject organizationJson = new JSONObject();
                    organizationJson.put("organizationId", organization.getOrganizationId());
                    organizationJson.put("name", organization.getName());
                    organizationJson.put("description", organization.getDescription());
                    organizationJson.put("location", organization.getLocation());
                    organizationJson.put("industry", organization.getIndustry());
                    organizationJson.put("website", organization.getWebsite());
                    organizationJson.put("contactEmail", organization.getContactEmail());
                    organizationJson.put("contactPhone", organization.getContactPhone());

                    resp.setContentType("application/json");
                    resp.getWriter().write(organizationJson.toString());
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"message\": \"Organization not found\"}");
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"message\": \"Invalid organization ID format\"}");
            }
        }
        } catch (JSONException e) {
			e.printStackTrace();
		}
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        try {
        JSONObject organizationJson = new JSONObject(sb.toString());
        Organization organization = new Organization();
        organization.setName(organizationJson.getString("name"));
        organization.setDescription(organizationJson.getString("description"));
        organization.setLocation(organizationJson.getString("location"));
        organization.setIndustry(organizationJson.getString("industry"));
        organization.setWebsite(organizationJson.getString("website"));
        organization.setContactEmail(organizationJson.getString("contactEmail"));
        organization.setContactPhone(organizationJson.getString("contactPhone"));

        boolean isCreated = organizationService.createOrganization(organization);
        if (isCreated) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"message\": \"Organization created successfully\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"message\": \"Organization creation failed\"}");
        }
        } catch (JSONException e) {
			e.printStackTrace();
		}
    }
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        try {
        JSONObject organizationJson = new JSONObject(sb.toString());
        Organization organization = new Organization();
        organization.setOrganizationId(organizationJson.getInt("organizationId"));
        organization.setName(organizationJson.getString("name"));
        organization.setDescription(organizationJson.getString("description"));
        organization.setLocation(organizationJson.getString("location"));
        organization.setIndustry(organizationJson.getString("industry"));
        organization.setWebsite(organizationJson.getString("website"));
        organization.setContactEmail(organizationJson.getString("contactEmail"));
        organization.setContactPhone(organizationJson.getString("contactPhone"));

        boolean isUpdated = organizationService.updateOrganization(organization);
        if (isUpdated) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"message\": \"Organization updated successfully\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"message\": \"Organization update failed\"}");
        }
        } catch (JSONException e) {
			e.printStackTrace();
		}
    }
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String organizationIdParam = req.getParameter("organizationId");

        if (organizationIdParam == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\": \"Organization ID is required\"}");
        } else {
            try {
                int organizationId = Integer.parseInt(organizationIdParam);
                boolean isDeleted = organizationService.deleteOrganization(organizationId);
                if (isDeleted) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write("{\"message\": \"Organization deleted successfully\"}");
                } else {
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    resp.getWriter().write("{\"message\": \"Organization deletion failed\"}");
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"message\": \"Invalid organization ID format\"}");
            }
        }
    }
}
