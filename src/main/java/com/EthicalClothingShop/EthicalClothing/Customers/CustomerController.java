package com.EthicalClothingShop.EthicalClothing.Customers;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="api/customers")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers (){


        return customerService.getAllCustomers();
    }

    @GetMapping("{customerID}")
    public Customer getCustomerById(@PathVariable("customerID") int customerID) {
     return customerService.getCustomer(customerID);

    }
    @PutMapping
    public void updateEmailAddress(@RequestBody Customer customer){
        customerService.updateNewEmailAddress(customer);
    }
    @DeleteMapping("{customerName}")
    public void deleteCustomer(@PathVariable("customerName") Customer customerName){
        customerService.removeCustomer(customerName);


    }
    @PostMapping
    public void addNewCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
    }



}
