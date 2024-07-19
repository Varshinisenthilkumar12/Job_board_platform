<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Apply for Job</title>
    <style>
        .form-container {
            max-width: 400px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
        }
        .form-container input, .form-container textarea {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-container button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .form-container button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Apply for Job</h2>
        <form action="job" method="post">
            <input type="hidden" name="action" value="apply">
            <label for="userId">User ID:</label>
            <input type="text" id="userId" name="userId" required>

            <label for="organizationId">Organization ID:</label>
            <input type="text" id="organizationId" name="organizationId" required>

            <label for="coverLetter">Cover Letter:</label>
            <textarea id="coverLetter" name="coverLetter" rows="5" required></textarea>

            <button type="submit">Apply</button>
        </form>
    </div>
</body>
</html>
