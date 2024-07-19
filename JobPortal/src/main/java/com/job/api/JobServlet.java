package com.job.api;
import com.job.models.Application;
import com.job.service.ApplicationService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

@WebServlet(name = "JobServlet", urlPatterns = {"/api/job"})
public class JobServlet extends HttpServlet {
    private ApplicationService applicationService;

    @Override
    public void init() throws ServletException {
        applicationService = new ApplicationService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("apply".equals(action)) {
            applyForJob(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void applyForJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/api/login.jsp");
            return;
        }

        String userIdStr = req.getParameter("userId");
        String organizationIdStr = req.getParameter("organizationId");
        String coverLetter = req.getParameter("coverLetter");

        if (userIdStr == null || userIdStr.isEmpty() || organizationIdStr == null || organizationIdStr.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid userId or organizationId");
            return;
        }

        int userId;
        int organizationId;

        try {
            userId = Integer.parseInt(userIdStr);
            organizationId = Integer.parseInt(organizationIdStr);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid userId or organizationId format");
            return;
        }

        Timestamp applicationDate = Timestamp.from(Instant.now());
        Application application = new Application(userId, organizationId, coverLetter, applicationDate);

        if (applicationService.createApplication(application)) {
            req.setAttribute("successMessage", "Application submitted successfully!");
            req.getRequestDispatcher("/success.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/api/applyjob.jsp?error=true");
        }
    }
}
