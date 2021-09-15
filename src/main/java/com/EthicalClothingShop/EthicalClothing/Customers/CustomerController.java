package com.EthicalClothingShop.EthicalClothing.Customers;

import org.javatuples.Quartet;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/customers")
public class CustomerController {
    private CustomerService customerService;

    // constructor
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //method for adding a new customer @PostMapping and require Params from customer
    @PostMapping("/create_account")
    public void addNewCustomer(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String email,
                               @RequestParam String mobile,
                               @RequestParam String firstLineBillingAddress,
                               @RequestParam (required = false) String secondLineBillingAddress,
                               @RequestParam String billingCityOrTown,
                               @RequestParam String billingPostcode,
                               @RequestParam String firstLineDeliveryAddress,
                               @RequestParam (required = false) String secondLineDeliveryAddress,
                               @RequestParam String deliveryCityOrTown,
                               @RequestParam String deliveryPostcode) {

        customerService.addNewCustomerAccount(firstName, lastName, email,
                       mobile, firstLineBillingAddress, secondLineBillingAddress,
                       billingCityOrTown, billingPostcode, firstLineDeliveryAddress,
                       secondLineDeliveryAddress, deliveryCityOrTown, deliveryPostcode);
    }

    @PostMapping("/logged_in/add_new_address")
    public void addNewCustomerAddress(@RequestParam String firstLineAddress,
                                      @RequestParam (required = false) String secondLineAddress,
                                      @RequestParam String cityOrTown,
                                      @RequestParam String postcode) {
        customerService.addNewCustomerAddress(firstLineAddress, secondLineAddress, cityOrTown, postcode);
    }

    // @PostMapping customer logs in method @RequestParam email, password (return bool if all okay or not)
    @PostMapping("/logged_in")
    public void customerWantsToLogIn(@RequestParam String email) {
        customerService.setCustomer(email);
    }

    @GetMapping("/account_details")
    public Quartet<String, String, String, String> getCustomerAccountDetails() {
        if (customerService.getCustomer() == null) {
            /* possibly throw exception so front end knows user must log in
            before seeing account details. */
            System.out.println("customer needs to log in first");
            return null;
        }
        Customer customerAccountDetails = customerService.getCustomer();

        Quartet<String, String, String, String> accountDetails = new Quartet<String, String, String, String>(customerAccountDetails.getFirstName(),
                                                                               customerAccountDetails.getLastName(),
                                                                               customerAccountDetails.getEmail(),
                                                                               customerAccountDetails.getMobileNumber());
        return accountDetails;
    }

    @GetMapping("/makePurchase")
    public int customerWantsOrderReference() {
        return (customerService.customerMakesPurchase());
    }

    @PostMapping("/{type}-{subtype}-{material}")
    public void customerAddsItemToBasket(@PathVariable String type,
                                         @PathVariable String subtype,
                                         @PathVariable String material,
                                         @RequestParam String color,
                                         @RequestParam String size,
                                         @RequestParam int quantity) {

        customerService.addItemsToBasket(type, subtype, material, color, size, quantity);
    }

    @DeleteMapping("/{clothingId}")
    public void customerRemovesItemFromBasket(@PathVariable int clothingId) {
        customerService.removeItemFromBasket(clothingId);
    }

    // method for editing basket items is needed @PutMapping

    //method for customer viewing their basket using a @GetMapping


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
