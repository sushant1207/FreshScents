package model;

public class OrderItem {
    private int perfumeID;
    private String perfumeName;
    private int orderQuantity;
    private double unitPrice;
    private double subtotal;

    // Getters and Setters
    public int getPerfumeID() {
        return perfumeID;
    }
    public void setPerfumeID(int perfumeID) {
        this.perfumeID = perfumeID;
    }

    public String getPerfumeName() {
        return perfumeName;
    }
    public void setPerfumeName(String perfumeName) {
        this.perfumeName = perfumeName;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }
    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
