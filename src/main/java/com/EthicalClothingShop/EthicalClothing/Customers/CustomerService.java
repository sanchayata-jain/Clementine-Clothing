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
        return customerDataAccessService.getAllCustomers();
    }

    public Customer getCustomer(int Id) {
        List<Customer> customers = customerDataAccessService.getAllCustomers();
        return customers.stream().filter(c -> c.getId() == Id).findFirst().orElseThrow(() -> new IllegalStateException("Sorry this" + Id + "does not exist"));

    }

    private boolean doesCustomerExist(String customerEmail) {
        List<Customer> customers = customerDataAccessService.getAllCustomers();
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
        List<Customer> customers = customerDataAccessService.getAllCustomers();
        boolean foundCustomer = false;
        for (Customer customer1 : customers) {
            if (doesCustomerExist(customer1.getEmail())) {
                customer1.setEmail(customer.getEmail());
                customer1.setMobileNumber(customer.getMobileNumber());
                customer1.setFirstLineAddress(customer.getFirstLineAddress());
                customer1.setCity(customer.getCity());
                customer1.setPostcode(customer.getPostcode());
                customer1.setFirstName(customer.getFirstName());
                customer1.setSurName(customer.getSurName());
                foundCustomer=true;
            }

        }if(!foundCustomer){
            throw new IllegalStateException("sorry this customer is not registered with us.");
        }

    }
}
