package com.EthicalClothingShop.EthicalClothing.Customers;

import org.javatuples.Quintet;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;


@Service
public class CustomerService {
    private CustomerDataAccessServicePsql database_access_customer;
    private Customer customerAccountInfo;

    // constructor
    public CustomerService(CustomerDataAccessServicePsql customerDataAccessServicePsql) {
        this.database_access_customer = customerDataAccessServicePsql;
        this.customerAccountInfo = null;
    }

    public Customer getCustomer() {

        return this.customerAccountInfo;
    }

    public void setCustomer(String email) {
        Customer customerAccountInfo = database_access_customer.getCustomerAccountInfo(email);
        this.customerAccountInfo = customerAccountInfo;
    }

    private boolean doesCustomerExist(String customerEmail) {
        return (database_access_customer.findCustomer(customerEmail));
    }

    public int customerMakesPurchase() {
       // need to pass current date & current time probs using LocalDate
        LocalDate orderDate = LocalDate.now();
        LocalTime orderTime = LocalTime.now();
//        if (this.customerAccountInfo == null) {
//            // throw an error
//        }

        int orderReference = database_access_customer.createOrderRef(this.customerAccountInfo.getId(), orderDate, orderTime);
        // using customer id I want to grab all the clothing ids associated with that customer and quantity
       //database_access_customer.populateOrderContentsTable(this.customerAccountInfo.getId(), orderReference);

        return orderReference;
    }


    public void addItemsToBasket(String type, String subtype,
                                 String material, String color,
                                 String size, int quantity) {

        database_access_customer.addItemsToBasket(type, subtype, material, color, size, quantity,
                                                 this.customerAccountInfo.getId());
    }

    public void removeItemFromBasket(int clothingId) {
        database_access_customer.removeItemFromBasket(clothingId);
    }

    public void editItemQuantityInBasket(int clothingId, boolean isIncreasingQuantity) {
        database_access_customer.editItemQuantityInBasket(this.customerAccountInfo.getId(),
                                                          clothingId, isIncreasingQuantity);
    }

    public void addNewCustomerAccount(String firstName, String lastName, String email,
                                      String mobile, String firstLineBillingAddress, String secondLineBillingAddress,
                                      String billingCityOrTown, String billingCountyOrState, String billingPostcode,
                                      String firstLineDeliveryAddress, String secondLineDeliveryAddress,
                                      String deliveryCityOrTown, String deliveryCountyOrState,
                                      String deliveryPostcode) {

        boolean customerExists = this.doesCustomerExist(email);
        if (!customerExists) {
            int newCustomerId = database_access_customer.addCustomerInformation(firstName, lastName, email, mobile);
            // add both billing and delivery address
            this.addNewCustomerAddresses(newCustomerId, firstLineBillingAddress, secondLineBillingAddress,
                                        billingCityOrTown, billingCountyOrState, billingPostcode,
                                        firstLineDeliveryAddress, secondLineDeliveryAddress,
                                        deliveryCityOrTown, deliveryCountyOrState, deliveryPostcode);
        } else {
            // need to throw an error
            System.out.println("We already have an account with this email address, please try logging in");
            }
        }


    // use this for when a new customer is being added
        private void addNewCustomerAddresses(int newCustomerId, String firstLineBillingAddress, String secondLineBillingAddress,
                                             String billingCityOrTown, String billingCountyOrState, String billingPostcode,
                                             String firstLineDeliveryAddress, String secondLineDeliveryAddress,
                                             String deliveryCityOrTown, String deliveryCountyOrState, String deliveryPostcode) {

            int deliveryAddressId = database_access_customer.addCustomerAddressToAddressBook(
                    newCustomerId, firstLineDeliveryAddress, secondLineDeliveryAddress,
                    deliveryCityOrTown, deliveryCountyOrState, deliveryPostcode
            );

            int billingAddressId = database_access_customer.addCustomerAddressToAddressBook(
                    newCustomerId, firstLineBillingAddress, secondLineBillingAddress,
                    billingCityOrTown, billingCountyOrState, billingPostcode
            );

            database_access_customer.addToCustomerAddressBook(newCustomerId, deliveryAddressId);
            database_access_customer.addToCustomerAddressBook(newCustomerId, billingAddressId);

            database_access_customer.addDefaultDeliveryAddress(newCustomerId, deliveryAddressId);
            database_access_customer.addDefaultBillingAddress(newCustomerId, billingAddressId);

        }


        // use this for when a returning customer has logged in and wants to add another address
        public void addCustomerAddress(String firstLineAddress, String secondLineAddress,
                                          String cityOrTown, String countyOrState, String postcode) {

          int addressId = database_access_customer.addCustomerAddressToAddressBook(this.customerAccountInfo.getId(), firstLineAddress,
                                                                                   secondLineAddress, cityOrTown,
                                                                                   countyOrState, postcode);

          database_access_customer.addToCustomerAddressBook(this.customerAccountInfo.getId(), addressId);
        }


    }











//    public List<Customer> getAllCustomers() {
//        return customerDataAccessService.getAllCustomers();
//    }
//
//    public Customer getCustomer(int Id) {
//        List<Customer> customers = customerDataAccessService.getAllCustomers();
//        return customers.stream().filter(c -> c.getId() == Id).findFirst().orElseThrow(() -> new IllegalStateException("Sorry this" + Id + "does not exist"));
//    }




//
//    public void addCustomer(Customer customer) {
//        if (!doesCustomerExist(customer.getEmail())) {
//            customerDataAccessService.addNewCustomer(customer);
//        }
//    }
//
//    public void removeCustomer(Customer customer) {
//        if (doesCustomerExist(customer.getEmail())) {
//            customerDataAccessService.deleteCustomer(customer);
//        } else {
//            throw new IllegalStateException("this customer does not exist in our database!");
//        }
//    }
//
//    public void updateAccountDetails(Customer customer) {
//        List<Customer> customers = customerDataAccessService.getAllCustomers();
//        boolean foundCustomer = false;
//        for (Customer customer1 : customers) {
//            if (doesCustomerExist(customer1.getEmail())) {
//                customer1.setEmail(customer.getEmail());
//                customer1.setMobileNumber(customer.getMobileNumber());
//                customer1.setFirstLineAddress(customer.getFirstLineAddress());
//                customer1.setCity(customer.getCity());
//                customer1.setPostcode(customer.getPostcode());
//                customer1.setFirstName(customer.getFirstName());
//                customer1.setLastName(customer.getLastName());
//                foundCustomer=true;
//            }
//
//        }if(!foundCustomer){
//            throw new IllegalStateException("sorry this customer is not registered with us.");
//        }


