package com.corebank.services;

import com.corebank.dao.CustomerDao;
import com.corebank.entity.Customer;

import java.util.List;

public class CustomerService {

    private CustomerDao customerDao;

    public CustomerService() {
        this.customerDao = new CustomerDao();
    }

    public void addCustomer(Customer customer) {
        customerDao.saveCustomer(customer);
    }

    public Customer getCustomerById(Long customerId) {
        return customerDao.getCustomerById(customerId);
    }

    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }

    public void deleteCustomer(Long customerId) {
        customerDao.deleteCustomer(customerId);
    }
}
