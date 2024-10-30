package com.corebank.entity;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String customerName;
    private String customerEmail;
    
    @Embedded
   private Address address;
    private String phoneNo;
    
    private String customerDOB;
    private Nominee nominee;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    public Customer() {
    }

    public Customer(String customerName, String customerEmail, Address address, String phoneNo, String customerDOB,
            Nominee nominee, Account account) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.address = address;
        this.phoneNo = phoneNo;
        this.customerDOB = customerDOB;
        this.nominee = nominee;
        this.account = account;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCustomerDOB() {
        return customerDOB;
    }

    public void setCustomerDOB(String customerDOB) {
        this.customerDOB = customerDOB;
    }

    public Nominee getNominee() {
        return nominee;
    }

    public void setNominee(Nominee nominee) {
        this.nominee = nominee;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    
    
    
}
