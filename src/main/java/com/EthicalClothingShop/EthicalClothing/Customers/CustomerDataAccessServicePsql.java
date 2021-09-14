//package com.EthicalClothingShop.EthicalClothing.Customers;
//
//import com.EthicalClothingShop.EthicalClothing.ClothingLine.ClothingDataAccessServicePsql;
//import com.EthicalClothingShop.EthicalClothing.ClothingLine.ClothingItem;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.math.BigInteger;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.List;
//
//@Repository
//public class CustomerDataAccessServicePsql implements CustomerDAO{
//
//    private JdbcTemplate jdbcTemplate;
//
//    // constructor
//    public CustomerDataAccessServicePsql(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public boolean findCustomer(String customerEmail) {
//        boolean customerFound = true;
//        try {
//            String findCustomerEmailQuery = "SELECT email, customer_id FROM customer_information " +
//                    "WHERE email LIKE " + "'customerEmail'";
//            String email = jdbcTemplate.queryForObject(findCustomerEmailQuery, String.class);
//        } catch (Exception e) {
//            customerFound = false;
//            return customerFound;
//        }
//
//        return customerFound;
//    }
//
//    public Customer getCustomerAccountInfo(String email, String password) {
//        String getCustomerId= "SELECT customer_id FROM customer_information" +
//                " WHERE email LIKE " + "'email'";
//        String getCustomerFirstName = "SELECT first_name FROM customer_information" +
//                " WHERE email LIKE " + "'email'";
//        String getCustomerLastName = "SELECT last_name FROM customer_information" +
//                " WHERE email LIKE " + "'email'";
//        String getCustomerMobile = "SELECT mobile FROM customer_information" +
//                " WHERE email LIKE " + "'email'";
//        String getCustomerEmail = "SELECT email FROM customer_information" +
//                " WHERE email LIKE " + "'email'";
//
//        int customerId = jdbcTemplate.queryForObject(getCustomerId, int.class);
//        String firstName = jdbcTemplate.queryForObject(getCustomerFirstName, String.class);
//        String lastName = jdbcTemplate.queryForObject(getCustomerLastName, String.class);
//        BigInteger mobile = jdbcTemplate.queryForObject(getCustomerMobile, BigInteger.class);
//        String emailFromDB = jdbcTemplate.queryForObject(getCustomerEmail, String.class);
//
//        Customer customer_account_details = new Customer(customerId, firstName, lastName, emailFromDB, mobile);
//
//        return customer_account_details;
//    }
//
//    public int createOrderRef(int customerId, LocalDate orderDate) {
//        String addNewOrderRecord = """
//        INSERT INTO orders_information(customer_id, order_date)
//                                              VALUES(?, ?)
//        """;
//        jdbcTemplate.update(addNewOrderRecord, customerId, orderDate);
//
//        String getOrderRefQuery = "SELECT order_id FROM orders_information WHERE customer_id = " + "'" +  customerId + "'";
//        int orderRef = jdbcTemplate.queryForObject(getOrderRefQuery, int.class);
//
//        return orderRef;
//    }
//
//    public void populateOrderContentsTable(int customerId, int orderRef) {
//        String getBasketContentQuery = "SELECT clothing_id, quantity " +
//                                       "FROM basket_content WHERE customer_id = " + "'" + customerId + "'";
//
//        jdbcTemplate.queryForRowSet();
//
//
//        String addNewOrderContent = """
//        INSERT INTO orders_information(orderRef, clothes_id, quantity)
//                                              VALUES(?, ?, ?)
//        """;
//        jdbcTemplate.update(addNewOrderContent, orderRef, clothesId, quantity);
//
//    }
//
//}
