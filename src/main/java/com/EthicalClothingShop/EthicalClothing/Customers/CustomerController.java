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
    public Customer getCustomerAccountInfoWhenLoggingIn(@RequestParam String email,
                                                        @RequestParam String password) {
        Customer customerAccountInfo = customerService.getCustomer(email, password);

        return customerAccountInfo;
    }

    @PostMapping
    public void customerAddsItemToBasket(@RequestParam int clothing_id,
                                         @RequestParam int quantity) {
        customerService.addItemsToBasket(clothing_id, quantity);
    }















//    @GetMapping
//    public List<Customer> getAllCustomers (){
//        return customerService.getAllCustomers();
//    }

//    @GetMapping("{customerID}")
//    public Customer getCustomerById(@PathVariable("customerID") int customerID) {
//        return customerService.getCustomer(customerID);
//    }




//    @PutMapping
//    public void updateAccountDetails(@RequestBody Customer customer){
//        customerService.updateAccountDetails(customer);
//    }
//    @DeleteMapping("{customerName}")
//    public void deleteCustomer(@PathVariable("customerName") Customer customerName){
//        customerService.removeCustomer(customerName);
//
//
//    }
//    @PostMapping
//    public void addNewCustomer(@RequestBody Customer customer){
//        customerService.addCustomer(customer);
//    }
}