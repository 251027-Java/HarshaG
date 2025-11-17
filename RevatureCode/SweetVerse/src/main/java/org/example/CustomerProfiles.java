package org.example;

import java.util.Date;

public class CustomerProfiles {
    // fields
    private int customerId;
    private String name;
    private String email;
    private Date createdDate;

    // constructor
    public CustomerProfiles(int customerId, String name, String email, Date createdAt) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.createdDate = createdAt;
    }

    // methods


    public int getCustomerId() { return this.customerId; }
    public String getName() {return this.name;}
    public String getEmail() {return this.email;}
    public Date getCreatedDate() {return this.createdDate;}

    @Override
    public String toString() {
        return "CustomerProfile [customerId=" + customerId + ", name=" + name + ", email=" + email +  ", createdDate=" + createdDate + "]";
    }
}
