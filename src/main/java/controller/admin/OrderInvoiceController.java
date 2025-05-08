package controller.admin;

import config.DBConfig;
import model.Order;
import model.OrderItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;


@WebServlet("/orderInvoice")
public class OrderInvoiceController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(req.getParameter("id"));
        Order order = new Order();
        List<OrderItem> items = new ArrayList<>();

        try (Connection conn = DBConfig.getConnection()) {
            String query = "SELECT o.OrderID, o.OrderDate, u.Name as UserName, " +
                           "oi.OrderQuantity, oi.PerfumeID, p.PerfumeName, p.Price " +
                           "FROM ordertable o " +
                           "JOIN user u ON o.UserID = u.UserID " +
                           "JOIN orderitem oi ON o.OrderID = oi.OrderID " +
                           "JOIN perfume p ON oi.PerfumeID = p.PerfumeID " +
                           "WHERE o.OrderID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            boolean initialized = false;
            double totalAmount = 0;

            while (rs.next()) {
                if (!initialized) {
                    order.setOrderID(rs.getInt("OrderID"));
                    order.setUserID(rs.getInt("UserID"));
                    order.setOrderDate(rs.getDate("OrderDate"));
                    initialized = true;
                }

                OrderItem item = new OrderItem();
                item.setPerfumeID(rs.getInt("PerfumeID"));
                item.setPerfumeName(rs.getString("PerfumeName"));
                item.setOrderQuantity(rs.getInt("OrderQuantity"));
                item.setUnitPrice(rs.getDouble("Price"));
                item.setSubtotal(item.getOrderQuantity() * item.getUnitPrice());
                totalAmount += item.getSubtotal();

                items.add(item);
            }

            order.setTotalAmount(totalAmount);
            order.setItems(items);
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("order", order);
        req.getRequestDispatcher("/pages/order-invoice.jsp").forward(req, resp);
    }
}
