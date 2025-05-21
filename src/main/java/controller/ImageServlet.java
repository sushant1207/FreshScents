package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.DBConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user-image")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int userID = Integer.parseInt(req.getParameter("id"));

        try (Connection conn = DBConfig.getConnection()) {
            String sql = "SELECT ProfileImage FROM user WHERE UserID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                byte[] imageBytes = rs.getBytes("ProfileImage");
                if (imageBytes != null) {
                    resp.setContentType("image/png");
                    OutputStream os = resp.getOutputStream();
                    os.write(imageBytes);
                    os.flush();
                } else {
                    resp.sendRedirect("images/default-avatar.png"); // fallback image
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
