package controller.admin;

import config.DBConfig;
import model.Perfume;
import model.Order;  // Import Order model
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/adminDashboard")
public class DashboardController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // Lists to store perfumes, users, and orders
        List<Perfume> perfumeList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        List<Order> orderHistory = new ArrayList<>();

        try (Connection conn = DBConfig.getConnection()) {
            // Query to fetch perfumes
            String perfumeQuery = "SELECT * FROM perfume";
            PreparedStatement psPerfume = conn.prepareStatement(perfumeQuery);
            ResultSet rsPerfume = psPerfume.executeQuery();
            while (rsPerfume.next()) {
                Perfume p = new Perfume();
                p.setPerfumeID(rsPerfume.getInt("PerfumeID"));
                p.setPerfumeName(rsPerfume.getString("PerfumeName"));
                p.setBrand(rsPerfume.getString("Brand"));
                p.setPrice(rsPerfume.getDouble("Price"));
                p.setStock(rsPerfume.getInt("Stock"));
                perfumeList.add(p);
            }

            // Query to fetch users
            String userQuery = "SELECT * FROM user";
            PreparedStatement psUser = conn.prepareStatement(userQuery);
            ResultSet rsUser = psUser.executeQuery();
            while (rsUser.next()) {
                User u = new User();
                u.setUserID(rsUser.getInt("UserID"));
                u.setName(rsUser.getString("Name"));
                u.setEmail(rsUser.getString("Email"));
                u.setRole(rsUser.getString("Role"));
                userList.add(u);
            }

            // Query to fetch order history
            String orderQuery = "SELECT o.OrderID, o.OrderDate, u.Name as UserName " +
                                 "FROM ordertable o " +
                                 "JOIN user u ON o.UserID = u.UserID " +
                                 "ORDER BY o.OrderDate DESC";
            PreparedStatement psOrder = conn.prepareStatement(orderQuery);
            ResultSet rsOrder = psOrder.executeQuery();
            while (rsOrder.next()) {
                Order order = new Order();
                order.setOrderID(rsOrder.getInt("OrderID"));
                order.setOrderDate(rsOrder.getDate("OrderDate"));
                order.setName(rsOrder.getString("UserName"));
                orderHistory.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set attributes to be used in the JSP
        req.setAttribute("perfumes", perfumeList);
        req.setAttribute("users", userList);
        req.setAttribute("orderHistory", orderHistory);

        // Forward the data to admin-dashboard.jsp
        req.getRequestDispatcher("/pages/admin-dashboard.jsp").forward(req, resp);
    }
}
