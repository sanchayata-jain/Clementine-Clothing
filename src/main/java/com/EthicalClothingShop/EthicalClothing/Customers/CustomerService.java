//package com.EthicalClothingShop.EthicalClothing.Customers;
//import com.EthicalClothingShop.EthicalClothing.ClothingLine.ClothingDataAccessServicePsql;
//import jdk.jshell.execution.LoaderDelegate;
//import org.apache.tomcat.jni.Local;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//public class CustomerService {
//    private CustomerDataAccessServicePsql database_access_customer;
//    private Customer customerAccountInfo;
//
//    // constructor
//    public CustomerService(CustomerDataAccessServicePsql customerDataAccessServicePsql) {
//        this.database_access_customer = customerDataAccessServicePsql;
//    }
//
//    public Customer getCustomer(String email, String password) {
//        Customer customerAccountInfo = database_access_customer.getCustomerAccountInfo(email, password);
//        this.customerAccountInfo = customerAccountInfo;
//        return customerAccountInfo;
//    }
//
//    private boolean doesCustomerExist(String customerEmail) {
//        return (database_access_customer.findCustomer(customerEmail));
//    }
//
//    private void customerMakesPurchase() {
//       // need to pass current date & current time probs using LocalDate
//        LocalDate orderDate = LocalDate.now();
//        int orderReference = database_access_customer.createOrderRef(this.customerAccountInfo.getId(), orderDate);
//        // using customer id I want to grab all the clothing ids associated with that customer and quantity
//       database_access_customer.populateOrderContentsTable(this.customerAccountInfo.getId(), orderReference);
//    }
//
//    public void addItemsToBasket() {
//
//        database_access_customer.addItemsToBasket(this.customerAccountInfo.getId(), );
//    }
//
//
//
////    public List<Customer> getAllCustomers() {
////        return customerDataAccessService.getAllCustomers();
////    }
////
////    public Customer getCustomer(int Id) {
////        List<Customer> customers = customerDataAccessService.getAllCustomers();
////        return customers.stream().filter(c -> c.getId() == Id).findFirst().orElseThrow(() -> new IllegalStateException("Sorry this" + Id + "does not exist"));
////    }
//
//
//
//
////
////    public void addCustomer(Customer customer) {
////        if (!doesCustomerExist(customer.getEmail())) {
////            customerDataAccessService.addNewCustomer(customer);
////        }
////    }
////
////    public void removeCustomer(Customer customer) {
////        if (doesCustomerExist(customer.getEmail())) {
////            customerDataAccessService.deleteCustomer(customer);
////        } else {
////            throw new IllegalStateException("this customer does not exist in our database!");
////        }
////    }
////
////    public void updateAccountDetails(Customer customer) {
////        List<Customer> customers = customerDataAccessService.getAllCustomers();
////        boolean foundCustomer = false;
////        for (Customer customer1 : customers) {
////            if (doesCustomerExist(customer1.getEmail())) {
////                customer1.setEmail(customer.getEmail());
////                customer1.setMobileNumber(customer.getMobileNumber());
////                customer1.setFirstLineAddress(customer.getFirstLineAddress());
////                customer1.setCity(customer.getCity());
////                customer1.setPostcode(customer.getPostcode());
////                customer1.setFirstName(customer.getFirstName());
////                customer1.setLastName(customer.getLastName());
////                foundCustomer=true;
////            }
////
////        }if(!foundCustomer){
////            throw new IllegalStateException("sorry this customer is not registered with us.");
////        }
//
//    }
//}