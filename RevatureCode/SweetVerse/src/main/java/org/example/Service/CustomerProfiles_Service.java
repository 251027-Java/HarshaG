package org.example.Service;


import org.example.CustomerProfiles;
import org.example.Repository.IRepository;

import java.util.Date;
import java.util.List;

public class CustomerProfiles_Service {
    //Fields
    private IRepository<CustomerProfiles, Integer> repo;


    // Constructor
    public CustomerProfiles_Service(IRepository<CustomerProfiles, Integer> repository) {
        this.repo = repository;
    }

    // Methods
    public CustomerProfiles createNewProfile(int customerId, String name, String email) {
        if (repo.get(customerId) != null){
            return null;
        }
        CustomerProfiles newProfile = new CustomerProfiles(customerId, name, email, new Date());
        repo.create(newProfile);
        return newProfile;
    }


    public CustomerProfiles getProfile(int customerId){
        return repo.get(customerId);
    }

    public void updateProfile(CustomerProfiles profile) {
        repo.update(profile);
    }

    public void deleteProfile(int customerId) {
        repo.delete(customerId);
    }

    public List<CustomerProfiles> getAllProfiles() {
        return repo.findAll();
    }

    public void printAllProfiles() {
        List<CustomerProfiles> orders = getAllProfiles();
        if (orders.isEmpty()) {
            System.out.println("No profiles found.");
        } else {
            for (CustomerProfiles order : orders) {
                System.out.println(order);
            }
        }
    }
}
