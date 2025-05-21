<%@ page session="true" %>
<%
    if (session.getAttribute("userID") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String name = (String) session.getAttribute("userName");
%>

<%@ include file="navbar.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Perfume Paradise - Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Stylesheets -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;700&display=swap" rel="stylesheet">

    <!-- Inline CSS for Hero Background -->
    <style>
        .hero {
            background: url('${pageContext.request.contextPath}/images/hero.gif') no-repeat center center/cover;
            height: 100vh;
            position: relative;
            color: #fff;
            text-align: center;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            padding: 0 20px;
            z-index: 1;
        }

        .hero::before {
            content: "";
            position: absolute;
            top: 0; left: 0; right: 0; bottom: 0;
            background: rgba(0, 0, 0, 0.5);
            z-index: 0;
        }

        .hero * {
            z-index: 1;
            position: relative;
        }
    </style>
</head>
<body>

    <!-- Hero Section -->
    <section class="hero">
        <div class="hero-content">
            <h1>Hello, <%= name %></h1>
            <h1>Discover Your Signature Scent</h1>
          	
            <a href="shop.jsp" class="cta">Shop Now</a>
        </div>
    </section>

    <!-- Featured Products -->
    <section class="products">
        <h2>Featured Perfumes</h2>
        <div class="product-grid">

            <div class="product-card">
                <img src="${pageContext.request.contextPath}/images/perfume1.jpeg" alt="Chanel Coco Mademoiselle">
                <h3>Chanel Coco Mademoiselle</h3>
                <p>Elegant. Feminine. Iconic.</p>
                <button onclick="location.href='shop.jsp'">View</button>
            </div>

            <div class="product-card">
                <img src="${pageContext.request.contextPath}/images/perfume2.jpeg" alt="Dior Sauvage">
                <h3>Dior Sauvage</h3>
                <p>Fresh. Powerful. Earthy.</p>
                <button onclick="location.href='shop.jsp'">View</button>
            </div>

            <div class="product-card">
                <img src="${pageContext.request.contextPath}/images/perfume%203.jpeg" alt="YSL Black Opium">
                <h3>YSL Black Opium</h3>
                <p>Sensual. Addictive. Bold.</p>
                <button onclick="location.href='shop.jsp'">View</button>
            </div>

        </div>
    </section>

    <!-- Footer -->
    <footer class="footer">
        <p>&copy; 2025 Perfume Paradise. All rights reserved.</p>
    </footer>

</body>
</html>
