<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login or Register</title>
    <style>
        .form-container {
            max-width: 400px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f2f2f2;
        }
        .form-container input, .form-container select {
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
        .link {
            display: block;
            text-align: center;
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <div class="form-container" id="loginForm">
        <h2>Login</h2>
        <form action="${pageContext.request.contextPath}/api/user" method="post">
            <input type="hidden" name="action" value="login">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="userType">User Type:</label>
            <select id="userType" name="userType" required>
                <option value="employer">Employer</option>
                <option value="jobseeker">Jobseeker</option>
            </select>

            <button type="submit">Login</button>
        </form>
        <a href="#" class="link" onclick="showRegisterForm()">Register</a>
    </div>

    <div class="form-container" id="registerForm" style="display:none;">
        <h2>Register</h2>
        <form action="${pageContext.request.contextPath}/api/user" method="post">
            <input type="hidden" name="action" value="register">
            <label for="regUsername">Username:</label>
            <input type="text" id="regUsername" name="username" required>

            <label for="regPassword">Password:</label>
            <input type="password" id="regPassword" name="password" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="regUserType">User Type:</label>
            <select id="regUserType" name="userType" required>
                <option value="employer">Employer</option>
                <option value="jobseeker">Jobseeker</option>
            </select>

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="contactInfo">Contact Info:</label>
            <input type="text" id="contactInfo" name="contactInfo" required>

            <button type="submit">Register</button>
        </form>
        <a href="#" class="link" onclick="showLoginForm()">Back to Login</a>
    </div>

    <script>
        function showRegisterForm() {
            document.getElementById('loginForm').style.display = 'none';
            document.getElementById('registerForm').style.display = 'block';
        }
        function showLoginForm() {
            document.getElementById('loginForm').style.display = 'block';
            document.getElementById('registerForm').style.display = 'none';
        }
    </script>
</body>
</html>
