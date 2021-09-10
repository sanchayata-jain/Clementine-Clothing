package com.EthicalClothingShop.EthicalClothing.Customers;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerDataAccessService customerDataAccessService;

    public CustomerService(CustomerDataAccessService customerDataAccessService) {
        this.customerDataAccessService = customerDataAccessService;
    }

    public List<Customer> getAllCustomers() {
        return customerDataAccessService.allCustomers();
    }

    public Customer getCustomer(int Id) {
        List<Customer> customers = customerDataAccessService.allCustomers();
        return customers.stream().filter(c -> c.getId() == Id).findFirst().orElseThrow(() -> new IllegalStateException("Sorry this" + Id + "does not exist"));

    }

    private boolean doesCustomerExist(String customerEmail) {
        List<Customer> customers = customerDataAccessService.allCustomers();
        return customers.stream().anyMatch(c -> c.getEmail().equals(customerEmail));


    }

    public void addCustomer(Customer customer) {
        if (!doesCustomerExist(customer.getEmail())) {
            customerDataAccessService.addNewCustomer(customer);
        }

    }

    public void removeCustomer(Customer customer) {
        if (doesCustomerExist(customer.getEmail())) {
            customerDataAccessService.deleteCustomer(customer);
        } else {
            throw new IllegalStateException("this customer does not exist in our database!");
        }
    }

    public void updateNewEmailAddress(Customer customer) {
        List<Customer> customers = customerDataAccessService.allCustomers();
        boolean foundCustomer = false;
        for (Customer customer1 : customers) {
            if (doesCustomerExist(customer1.getEmail())) {
                customer1.setEmail(customer.getEmail());
                foundCustomer=true;
            }

        }if(!foundCustomer){
            throw new IllegalStateException("sorry this customer is not registered with us.");
        }

    }
}
