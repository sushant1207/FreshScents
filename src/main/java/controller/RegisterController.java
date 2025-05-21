package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import config.DBConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/register")
@MultipartConfig
public class RegisterController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        Date dob = Date.valueOf(req.getParameter("dob"));
        String role = "customer";

        Part filePart = req.getPart("profileImage");
        InputStream imageStream = (filePart != null && filePart.getSize() > 0) ? filePart.getInputStream() : null;

        try (Connection conn = DBConfig.getConnection()) {
            String sql = "INSERT INTO user (Name, Email, Password, Role, DOB, Gender, ProfileImage) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.setDate(5, dob);
            ps.setString(6, gender);
            ps.setBinaryStream(7, imageStream);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                resp.sendRedirect("pages/login.jsp");
            } else {
                resp.getWriter().println("<script>alert('Registration failed!'); window.history.back();</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Error: " + e.getMessage());
        }
    }
}
