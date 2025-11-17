package org.example;

import org.example.Repository.*;
import org.example.Service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("SweetVerse Starting...");

        Scanner sc = new Scanner(System.in);

//        IRepository<CustomerOrders, Integer> repo = new CustomerOrders_Repository();
//        CustomerOrders_Service service = new CustomerOrders_Service(repo);
//
//        System.out.println("Creating a CustomerOrder:");
//
//        //service.createNewOrder(1, 12, 12.00, "Pending" );
//        CustomerOrders order = service.createNewOrder(1, 12, 12.00, "Pending");
//        System.out.println(order);

        System.out.println("Place Customer Order...");
        System.out.print("Enter customer name: ");
        String name = sc.nextLine(); // Scanner used here
        System.out.print("Enter customer email: ");
        String email = sc.nextLine(); // Scanner used here

        // Pass the raw data (name, email) to the service
        MainService.placeOrder(name, email);


        System.out.println("SweetVerse Closing...");
    }


}
