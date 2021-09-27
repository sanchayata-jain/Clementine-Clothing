package com.EthicalClothingShop.EthicalClothing.Customers;

import com.EthicalClothingShop.EthicalClothing.ClothingLine.ClothingItem;
import com.EthicalClothingShop.EthicalClothing.ClothingLine.ClothingService;
import org.javatuples.Pair;
import org.javatuples.Quintet;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


@Service
public class CustomerService {
    private CustomerDataAccessServicePsql database_access_customer;
    private Customer customerAccountInfo;
    private ClothingService clothingService;


    // constructor
    public CustomerService(CustomerDataAccessServicePsql customerDataAccessServicePsql,
                           ClothingService clothingService) {
        this.database_access_customer = customerDataAccessServicePsql;
        this.customerAccountInfo = null;
        this.clothingService = clothingService;
    }


    public Customer getCustomer() throws Exception {
        if (this.customerAccountInfo == null) {
            throw new Exception("You need to log in, to see your customer account details!");
        }
        return this.customerAccountInfo;
    }




    public void setCustomer(Customer customer) {

        this.customerAccountInfo = customer;
    }

    public Customer findCustomer(String email, String password) {
        return database_access_customer.getCustomerAccountInfo(email, password);
    }


    private boolean doesCustomerExist(String customerEmail) {
        return (database_access_customer.findCustomer(customerEmail));
    }


    public int customerMakesPurchase() throws Exception {
        LocalDate orderDate = LocalDate.now();
        LocalTime orderTime = LocalTime.now();
        int orderReference = 0;
        try {
            orderReference = database_access_customer.createOrderRef(this.customerAccountInfo.getId(), orderDate, orderTime);
        } catch(Exception e) {
            throw e;
        }

        // using customer id I want to grab all the clothing ids associated with that customer and quantity
        database_access_customer.populateOrderContentsTable(this.customerAccountInfo.getId(), orderReference);

        return orderReference;
    }



    public void addItemsToBasket(String type, String subtype,
                                 String material, String color,
                                 String size, int quantity) throws Exception {

        if (this.customerAccountInfo == null) {
            throw new Exception("Please log in to add an item to your basket");
        } else {

            database_access_customer.addItemsToBasket(type, subtype, material, color, size, quantity,
                    this.customerAccountInfo.getId());
        }
    }


    public void removeItemFromBasket(int clothingId) {
        database_access_customer.removeItemFromBasket(clothingId);
    }

    public void editItemQuantityInBasket(int clothingId, boolean isIncreasingQuantity) {
        database_access_customer.editItemQuantityInBasket(this.customerAccountInfo.getId(),
                                                          clothingId, isIncreasingQuantity);
    }


    public ArrayList<Pair<ClothingItem, Integer>> getCustomerBasketContent() throws Exception {
        if (this.customerAccountInfo == null) {
            throw new Exception("Please log into your account to view your basket");
        }
        ArrayList<Pair<Integer, Integer>> basketItemsIdQuantity = database_access_customer.getBasketItems(this.customerAccountInfo.getId());
        ArrayList<Pair<ClothingItem, Integer>> basketContents = new ArrayList<Pair<ClothingItem, Integer>>();
        for (Pair<Integer, Integer> idAndQuantity : basketItemsIdQuantity) {
            int clothingId = idAndQuantity.getValue0();
            int quantityOfClothingItem = idAndQuantity.getValue1();
            ClothingItem clothingItem = clothingService.getClothingItemUsingId(clothingId); // not finished
            Pair<ClothingItem, Integer> basketItem = new Pair<ClothingItem, Integer>(clothingItem, quantityOfClothingItem);
            basketContents.add(basketItem);
        }
        return basketContents;
    }




    public void addNewCustomerAccount(String firstName, String lastName, String email,
                                      String mobile, String firstLineBillingAddress, String secondLineBillingAddress,
                                      String billingCityOrTown, String billingCountyOrState, String billingPostcode,
                                      String firstLineDeliveryAddress, String secondLineDeliveryAddress,
                                      String deliveryCityOrTown, String deliveryCountyOrState,
                                      String deliveryPostcode, String password) throws Exception {

        boolean customerExists = this.doesCustomerExist(email);
        if (!customerExists) {
            int newCustomerId = database_access_customer.addCustomerInformation(firstName, lastName, email,
                                                                                mobile, password);

            System.out.println("In addNewCustomerAccount service");
            System.out.println(newCustomerId);
            System.out.println();
            // add both billing and delivery address
            this.addNewCustomerAddresses(newCustomerId, firstLineBillingAddress, secondLineBillingAddress,
                                        billingCityOrTown, billingCountyOrState, billingPostcode,
                                        firstLineDeliveryAddress, secondLineDeliveryAddress,
                                        deliveryCityOrTown, deliveryCountyOrState, deliveryPostcode);
        } else {

            throw new Exception("We already have an account with this email address, please try logging in");
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


