// DeletePerfumeController.java
package controller.admin;

import config.DBConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/deletePerfume")
public class DeletePerfumeController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int perfumeID = Integer.parseInt(req.getParameter("id"));

        try (Connection conn = DBConfig.getConnection()) {
            String query = "DELETE FROM perfume WHERE PerfumeID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, perfumeID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("adminDashboard");  // Redirect to the dashboard after deletion
    }
}
