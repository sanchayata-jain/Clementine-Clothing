package com.EthicalClothingShop.EthicalClothing.Customers;

import org.javatuples.Quartet;
import org.javatuples.Quintet;
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
                               @RequestParam String billingCountyOrState,
                               @RequestParam String billingPostcode,
                               @RequestParam String firstLineDeliveryAddress,
                               @RequestParam (required = false) String secondLineDeliveryAddress,
                               @RequestParam String deliveryCityOrTown,
                               @RequestParam String deliveryCountyOrState,
                               @RequestParam String deliveryPostcode) {

        customerService.addNewCustomerAccount(firstName, lastName, email,
                       mobile, firstLineBillingAddress, secondLineBillingAddress,
                       billingCityOrTown, billingCountyOrState, billingPostcode, firstLineDeliveryAddress,
                       secondLineDeliveryAddress, deliveryCityOrTown, deliveryCountyOrState, deliveryPostcode);
    }

    @PostMapping("/logged_in/add_new_address")
    public void addCustomerAddress(@RequestParam String firstLineAddress,
                                      @RequestParam (required = false) String secondLineAddress,
                                      @RequestParam String cityOrTown,
                                      @RequestParam String countyOrState,
                                      @RequestParam String postcode) {
        customerService.addCustomerAddress(firstLineAddress, secondLineAddress, cityOrTown, countyOrState, postcode);
    }

//    @GetMapping("/logged_in/address_book")
//    public Quintet<String, String, String, String, String> getCustomerAddressBook() {
//        customerService.getCustomerAddressBook();
//    }

//    @PutMapping("/logged_in/address_book/edit_def_deliv_address")
//
//    @PutMapping("/logged_in/address_book/edit_def_bill_address")




    // @PostMapping customer logs in method @RequestParam email, password (return bool if all okay or not)
    @PostMapping("/logged_in")
    public void customerWantsToLogIn(@RequestParam String email) {
        customerService.setCustomer(email);
    }

    @GetMapping("/logged_in/account_details")
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
    public void customerRemovesItemFromBasket(@RequestParam int clothingId) {
        //this will remove item from basket, regardless of quantity
        customerService.removeItemFromBasket(clothingId);
    }

    // method for editing basket items is needed @PutMapping will involve increasing and decreasing quantity in basket
    @PutMapping("/logged_in/basket")
    public void customerEditsBasketContents(@RequestParam boolean isIncreasingQuantity,
                                            @RequestParam int clothingId) {
        // only edits quantity of items
        customerService.editItemQuantityInBasket(clothingId, isIncreasingQuantity);
    }

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
