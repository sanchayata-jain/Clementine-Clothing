package com.EthicalClothingShop.EthicalClothing.Customers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class CustomerDataAccessServicePsql implements CustomerDAO{

    private JdbcTemplate jdbcTemplate;

    // constructor
    public CustomerDataAccessServicePsql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public getAllCustomersAccountInfo() {

    }

    public Customer getCustomerAccountInfo(String email, String password) {
        String getCustomerFirstName = "SELECT first_name FROM customer_information" +
                " WHERE email LIKE " + "'email'";
        String getCustomerLastName = "SELECT last_name FROM customer_information" +
                " WHERE email LIKE " + "'email'";
        String getCustomerMobile = "SELECT mobile FROM customer_information" +
                " WHERE email LIKE " + "'email'";
        String getCustomerEmail = "SELECT email FROM customer_information" +
                " WHERE email LIKE " + "'email'";

        String firstName = jdbcTemplate.queryForObject(getCustomerFirstName, String.class);
        String lastName = jdbcTemplate.queryForObject(getCustomerLastName, String.class);
        BigInteger mobile = jdbcTemplate.queryForObject(getCustomerMobile, BigInteger.class);
        String emailFromDB = jdbcTemplate.queryForObject(getCustomerEmail, String.class);

        Customer customer_account_details = new Customer(firstName, lastName, emailFromDB, mobile);

        return customer_account_details;
    }

}
