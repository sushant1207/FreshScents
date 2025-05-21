<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register - FreshScents</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
</head>
<body>
<div class="wrapper">
    <form action="../register" method="post" enctype="multipart/form-data" class="form-box">
        <h2>Signup Form</h2>

        <div class="toggle-box">
            <a href="login.jsp"><button type="button">Login</button></a>
            <button type="button" class="active">Signup</button>
        </div>

        <div class="input-box">
            <input type="text" name="name" required>
            <label>Full Name</label>
        </div>

        <div class="input-box">
            <input type="email" name="email" required>
            <label>Email</label>
        </div>

        <div class="input-box">
            <input type="password" name="password" required>
            <label>Password</label>
        </div>

        <div class="input-box">
            <input type="date" name="dob" required>
            <label style="top: -20px;">Date of Birth</label>
        </div>

        <div class="input-box">
            <select name="gender" required>
                <option value="" disabled selected>Select Gender</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
            </select>
        </div>

        <div class="input-box">
            <input type="file" name="profileImage" accept="image/*" required>
            <label style="top: -20px;">Profile Image</label>
        </div>

        <button type="submit" class="login-btn">Register</button>

        <div class="signup-link">
            <a href="${pageContext.request.contextPath}/pages/login.jsp">Already have an account?</a>
        </div>
    </form>
</div>
</body>
</html>
