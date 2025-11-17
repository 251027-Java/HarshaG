package org.example.Service;

import org.example.CustomerOrders;
import org.example.Repository.IRepository;

import java.util.Date;
import java.util.List;

public class CustomerOrders_Service {
    //Fields
    private IRepository<CustomerOrders, Integer> repo;


    // Constructor
    public CustomerOrders_Service(IRepository<CustomerOrders, Integer> repository) {
        this.repo = repository;
    }

    // Methods
    public CustomerOrders createNewOrder(int orderId, int customerId, double totalCost, String status) {
        if (repo.get(orderId) != null){
            return null;
        }
        CustomerOrders newOrder = new CustomerOrders(orderId, customerId, new Date(), totalCost, status);
        repo.create(newOrder);
        return newOrder;
    }


    public CustomerOrders getOrder(int orderId){
        return repo.get(orderId);
    }

    public void updateOrder(CustomerOrders order) {
        repo.update(order);
    }

    public void deleteOrder(int orderId) {
        repo.delete(orderId);
    }

    public List<CustomerOrders> getAllOrders() {
        return repo.findAll();
    }

    public void printAllOrders() {
        List<CustomerOrders> orders = getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (CustomerOrders order : orders) {
                System.out.println(order);
            }
        }
    }



}
