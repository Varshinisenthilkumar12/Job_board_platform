<%@ page import="com.job.models.Organization" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Organizations</title>
</head>
<body>
<h1>Organizations</h1>

<c:if test="${sessionScope.userType == 'employer'}">
    <a href="createOrganization.jsp">Create New Organization</a>
</c:if>
<c:if test="${sessionScope.userType == 'jobseeker'}">
    <a href="applyjob.jsp">Apply now</a>
</c:if>

<%
    List<Organization> organizations = (List<Organization>) request.getAttribute("organizations");
    if (organizations != null) {
%>
    <table border="1">
        <thead>
            <tr>
                <th>Organization ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Location</th>
                <th>Industry</th>
                <th>Website</th>
                <th>Contact Email</th>
                <th>Contact Phone</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (Organization organization : organizations) {
        %>
            <tr>
                <td><%= organization.getOrganizationId() %></td>
                <td><%= organization.getName() %></td>
                <td><%= organization.getDescription() %></td>
                <td><%= organization.getLocation() %></td>
                <td><%= organization.getIndustry() %></td>
                <td><%= organization.getWebsite() %></td>
                <td><%= organization.getContactEmail() %></td>
                <td><%= organization.getContactPhone() %></td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
<%
    } else {
%>
    <p>No organizations available.</p>
<%
    }
%>
</body>
</html>
