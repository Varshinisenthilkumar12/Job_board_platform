package com.job.api;
import com.job.models.Organization;
import com.job.models.User;
import com.job.service.OrganizationService;
import com.job.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = {"/api/user"})
public class UserServlet extends HttpServlet {
    private static final String LOGIN_JSP = "/api/login.jsp";
    private static final String ORGANIZATIONS_JSP = "/api/organizations.jsp";
    private static final String REGISTER_JSP = "/api/register.jsp";

    private UserService userService;
    private OrganizationService organizationService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        organizationService = new OrganizationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("login".equals(action)) {
            req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
        } else if ("logout".equals(action)) {
            logout(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("login".equals(action)) {
            login(req, resp);
        } else if ("register".equals(action)) {
            register(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String userType = req.getParameter("userType");

        if (username == null || password == null || userType == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing login parameters");
            return;
        }

        User user = userService.getUserByUsernameAndPassword(username, password);

        if (user != null && userType.equals(user.getUserType())) {
            HttpSession session = req.getSession(true);
            session.setAttribute("loggedInUser", user);
            session.setAttribute("userType", user.getUserType());

            try {
                List<Organization> organizations = organizationService.getAllOrganizations();
                req.setAttribute("organizations", organizations);
                req.getRequestDispatcher(ORGANIZATIONS_JSP).forward(req, resp);
            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving organizations");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + LOGIN_JSP + "?error=true");
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + LOGIN_JSP);
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String userType = req.getParameter("userType");
        String name = req.getParameter("name");
        String contactInfo = req.getParameter("contactInfo");

        if (username == null || password == null || email == null || userType == null || name == null || contactInfo == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing registration parameters");
            return;
        }

        User newUser = new User(username, password, email, userType, name, contactInfo);

        if (userService.createUser(newUser)) {
            resp.sendRedirect(req.getContextPath() + LOGIN_JSP);
        } else {
            resp.sendRedirect(req.getContextPath() + REGISTER_JSP + "?error=true");
        }
    }
}
