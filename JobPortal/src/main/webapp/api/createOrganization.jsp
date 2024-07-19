<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Organization</title>
</head>
<body>
<h1>Create New Organization</h1>
<form action="${pageContext.request.contextPath}/api/org" method="post">
    <input type="hidden" name="action" value="create">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description"></textarea><br>

    <label for="location">Location:</label>
    <input type="text" id="location" name="location"><br>

    <label for="industry">Industry:</label>
    <input type="text" id="industry" name="industry"><br>

    <label for="website">Website:</label>
    <input type="text" id="website" name="website"><br>

    <label for="contactEmail">Contact Email:</label>
    <input type="email" id="contactEmail" name="contactEmail"><br>

    <label for="contactPhone">Contact Phone:</label>
    <input type="text" id="contactPhone" name="contactPhone"><br>

    <button type="submit">Create</button>
</form>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
<a href="${pageContext.request.contextPath}/api/org">Back to Organizations</a>
</body>
</html>
