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

    @GetMapping()
    public Customer getCustomerAccountInfoWhenLoggingIn(@RequestParam String email,
                                           @RequestParam String password) {
        return customerService.getCustomer(email, password);
    }


    @PutMapping
    public void updateAccountDetails(@RequestBody Customer customer){
        customerService.updateAccountDetails(customer);
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