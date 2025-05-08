package model;

import java.util.Date;
import java.util.List;

public class Order {
    private int orderID;
    private int userID;
    private Date orderDate;
    private String Name;

    // Getters and Setters
    public int getOrderID() {
        return orderID;
    }

    public String getName() {
        return Name;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setName(String name) {
        this.Name = name;  // Set the name properly
    }

    public void setTotalAmount(double totalAmount) {
        // Placeholder for future implementation
    }

    public void setItems(List<OrderItem> items) {
        // Placeholder for future implementation
    }
}
