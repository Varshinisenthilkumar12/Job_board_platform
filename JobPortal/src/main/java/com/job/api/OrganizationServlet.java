package com.job.api;
import com.job.models.User;
import com.job.models.Organization;
import com.job.service.OrganizationService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrganizationServlet", urlPatterns = {"/api/org"})
public class OrganizationServlet extends HttpServlet {
    private OrganizationService organizationService;

    @Override
    public void init() throws ServletException {
        try {
            organizationService = new OrganizationService();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize OrganizationService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            if (session != null) {
                User loggedInUser = (User) session.getAttribute("loggedInUser");

                if (loggedInUser != null) {
                    String userType = loggedInUser.getUserType();

                    List<Organization> organizations = organizationService.getAllOrganizations();
                    req.setAttribute("organizations", organizations);

                    if ("employer".equals(userType)) {
                        req.getRequestDispatcher("/api/organizations.jsp").forward(req, resp);
                    } else if ("jobseeker".equals(userType)) {
                        resp.sendRedirect(req.getContextPath() + "/api/applyjob.jsp");
                    } else {
                        resp.sendRedirect(req.getContextPath() + "/api/login.jsp");
                    }
                } else {
                    resp.sendRedirect(req.getContextPath() + "/api/login.jsp");
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/api/login.jsp");
            }
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching organizations: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("create".equals(action)) {
            createOrganization(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unsupported action requested");
        }
    }

    private void createOrganization(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            if (session != null && session.getAttribute("loggedInUser") != null) {
                User loggedInUser = (User) session.getAttribute("loggedInUser");
                if (!"employer".equals(loggedInUser.getUserType())) {
                    resp.sendRedirect(req.getContextPath() + "/api/login.jsp");
                    return;
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/api/login.jsp");
                return;
            }

            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String location = req.getParameter("location");
            String industry = req.getParameter("industry");
            String website = req.getParameter("website");
            String contactEmail = req.getParameter("contactEmail");
            String contactPhone = req.getParameter("contactPhone");

            Organization organization = new Organization();
            organization.setName(name);
            organization.setDescription(description);
            organization.setLocation(location);
            organization.setIndustry(industry);
            organization.setWebsite(website);
            organization.setContactEmail(contactEmail);
            organization.setContactPhone(contactPhone);

            boolean success = organizationService.createOrganization(organization);
            if (success) {
                resp.sendRedirect(req.getContextPath() + "/api/org");
            } else {
                req.setAttribute("error", "Failed to create organization");
                req.getRequestDispatcher("/createOrganization.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error creating organization: " + e.getMessage());
        }
    }
}
