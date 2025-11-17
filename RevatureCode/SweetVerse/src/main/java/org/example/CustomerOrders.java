package org.example;

import java.util.Date;

public class CustomerOrders {
    private int orderId;
    private int customerId;
    private Date orderDate;
    private double totalCost;
    private String status;

    // Constructor
    public CustomerOrders(int orderId, int customerId, Date orderDate, double totalCost, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalCost = totalCost;
        this.status = status;
    }



    // Constructor
//    public CustomerOrders(int orderId, int customerId, double totalCost, String status) {
//        this(orderId, customerId, new Date(), totalCost, status);
//    }


    public int getOrderId() { return this.orderId; }
    public int getCustomerId() { return this.customerId; }
    public Date getOrderDate() { return this.orderDate; }
    public double getTotalCost() { return this.totalCost; }
    public String getStatus() { return this.status; }


    // Methods
    @Override
    public String toString() {
        return "CustomerOrder [orderId=" + orderId + ", customerId=" + customerId + ", orderDate=" + orderDate + ", totalCost=" + totalCost + ", status=" + status + "]";
    }

}
