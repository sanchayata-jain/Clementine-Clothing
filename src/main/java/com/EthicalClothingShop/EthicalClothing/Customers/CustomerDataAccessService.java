package com.EthicalClothingShop.EthicalClothing.Customers;

import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class CustomerDataAccessService {
    private  ArrayList<Customer> cl;

    public CustomerDataAccessService() {
        Customer customer1= new Customer("Annie","Smith","17 Portobello Road",
           "Manchester","M13SQl" ,"AnnieSmith@hotmail.com", "076509834874",1   );
        Customer customer2= new Customer("James","Carson","18 Portobello Road",
                "Manchester","M13SQl" ,"JamesCarson123@hotmail.com", "07659888971",2);
        cl= new ArrayList<>();
        cl.add(customer1);
        cl.add(customer2);
    }
    public List<Customer>allCustomers(){
        return cl;
    }
    public void addNewCustomer(Customer customer){
        cl.add(customer);
    }
    public void deleteCustomer(Customer customer){
        cl.remove(customer);
    }
}
