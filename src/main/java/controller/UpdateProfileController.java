package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import config.DBConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/updateProfile")
@MultipartConfig
public class UpdateProfileController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int userID = Integer.parseInt(req.getParameter("userID"));
        String name = req.getParameter("name");
        Part filePart = req.getPart("profilePic");

        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps;
            if (filePart != null && filePart.getSize() > 0) {
                InputStream imageStream = filePart.getInputStream();
                String sql = "UPDATE user SET Name = ?, ProfileImage = ? WHERE UserID = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                ps.setBinaryStream(2, imageStream);
                ps.setInt(3, userID);
            } else {
                String sql = "UPDATE user SET Name = ? WHERE UserID = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                ps.setInt(2, userID);
            }

            ps.executeUpdate();

            HttpSession session = req.getSession();
            session.setAttribute("userName", name);

            resp.sendRedirect("pages/profile.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Error: " + e.getMessage());
        }
    }
}
