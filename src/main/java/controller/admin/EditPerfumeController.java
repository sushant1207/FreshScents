// EditPerfumeController.java
package controller.admin;

import config.DBConfig;
import model.Perfume;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/editPerfume")
public class EditPerfumeController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int perfumeID = Integer.parseInt(req.getParameter("id"));
        Perfume perfume = null;

        try (Connection conn = DBConfig.getConnection()) {
            String query = "SELECT * FROM perfume WHERE PerfumeID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, perfumeID);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                perfume = new Perfume();
                perfume.setPerfumeID(rs.getInt("PerfumeID"));
                perfume.setPerfumeName(rs.getString("PerfumeName"));
                perfume.setBrand(rs.getString("Brand"));
                perfume.setPrice(rs.getDouble("Price"));
                perfume.setStock(rs.getInt("Stock"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        req.setAttribute("perfume", perfume);
        req.getRequestDispatcher("/pages/edit-perfume.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int perfumeID = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String brand = req.getParameter("brand");
        double price = Double.parseDouble(req.getParameter("price"));
        int stock = Integer.parseInt(req.getParameter("stock"));

        try (Connection conn = DBConfig.getConnection()) {
            String query = "UPDATE perfume SET PerfumeName = ?, Brand = ?, Price = ?, Stock = ? WHERE PerfumeID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, brand);
            ps.setDouble(3, price);
            ps.setInt(4, stock);
            ps.setInt(5, perfumeID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("adminDashboard");  // Redirect to the dashboard after update
    }
}


