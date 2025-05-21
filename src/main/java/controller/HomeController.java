package controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Perfume;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if user is logged in
        if (request.getSession().getAttribute("userID") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

      
        // Forward the request to the home.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/home.jsp");
        dispatcher.forward(request, response);
    }
}
