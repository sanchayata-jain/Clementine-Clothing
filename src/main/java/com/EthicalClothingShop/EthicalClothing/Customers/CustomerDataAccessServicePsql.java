package com.EthicalClothingShop.EthicalClothing.Customers;


import com.EthicalClothingShop.EthicalClothing.ClothingLine.ClothingDataAccessServicePsql;
import com.EthicalClothingShop.EthicalClothing.ClothingLine.ClothingItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDataAccessServicePsql implements CustomerDAO{
    ClothingDataAccessServicePsql clothingDatabaseAccess; //= new ClothingDataAccessServicePsql();
    private JdbcTemplate jdbcTemplate;

    // constructor
    public CustomerDataAccessServicePsql(JdbcTemplate jdbcTemplate,
                                         ClothingDataAccessServicePsql clothingDataAccessServicePsql) {
        this.jdbcTemplate = jdbcTemplate;
        this.clothingDatabaseAccess = clothingDataAccessServicePsql;
    }

    public boolean findCustomer(String customerEmail) {
        // checks if customer exists, method returns true if customer exists, if customer doesn't exist method returns false
        boolean customerFound = true;
        try {
            String findCustomerEmailQuery = "SELECT email, customer_id FROM customer_information " +
                    "WHERE email LIKE " + "'" + customerEmail + "'";
            String email = jdbcTemplate.queryForObject(findCustomerEmailQuery, String.class);
        } catch (Exception e) {
            customerFound = false;
            return customerFound;
        }

        return customerFound;
    }

    public Customer getCustomerAccountInfo(String customerEmail) {
        String getCustomerId = "SELECT customer_id FROM customer_information" +
                " WHERE email LIKE " + "'" + customerEmail + "'";
        String getCustomerFirstName = "SELECT first_name FROM customer_information" +
                " WHERE email LIKE " + "'" + customerEmail + "'";
        String getCustomerLastName = "SELECT last_name FROM customer_information" +
                " WHERE email LIKE " + "'" + customerEmail + "'";
        String getCustomerMobile = "SELECT phone_number FROM customer_information" +
                " WHERE email LIKE " + "'" + customerEmail + "'";
        String getCustomerEmail = "SELECT email FROM customer_information" +
                " WHERE email LIKE " + "'" + customerEmail + "'";

        String firstName = jdbcTemplate.queryForObject(getCustomerFirstName, String.class);
        System.out.println("first name: " + firstName);
        String lastName = jdbcTemplate.queryForObject(getCustomerLastName, String.class);
        System.out.println("last name: " + lastName);
        String mobile = jdbcTemplate.queryForObject(getCustomerMobile, String.class);
        System.out.println("mobile: " + mobile);
        String emailFromDB = jdbcTemplate.queryForObject(getCustomerEmail, String.class);
        System.out.println("email from database: " + emailFromDB);

        int customerId = 0;
        try {
            customerId = jdbcTemplate.queryForObject(getCustomerId, int.class);
            System.out.println(customerId);
        } catch (Exception e) {
            System.out.println("uh oh");
        }

        return (new Customer(customerId, firstName, lastName, emailFromDB, mobile));
    }

    public int createOrderRef(int customerId, LocalDate orderDate) {
        String addNewOrderRecord = """
        INSERT INTO orders_information(customer_id, order_date)
                                              VALUES(?, ?)
        """;
        jdbcTemplate.update(addNewOrderRecord, customerId, orderDate);

        String getOrderRefQuery = "SELECT order_id FROM orders_information WHERE customer_id = " + "'" +  customerId + "'";
        int orderRef = jdbcTemplate.queryForObject(getOrderRefQuery, int.class);

        return orderRef;
    }

//    public void populateOrderContentsTable(int customerId, int orderRef) {
//        // creating a table with only  =>>>>>> clothing_id | quantity
//        String getBasketContentQuery = "SELECT clothing_id, quantity " +
//                                       "FROM basket_content WHERE customer_id = " + "'" + customerId + "'";
//
//        //jdbcTemplate.queryForRowSet();
//
//        String addNewOrderContent = """
//        INSERT INTO orders_information(orderRef, clothes_id, quantity)
//                                              VALUES(?, ?, ?)
//        """;
//        jdbcTemplate.update(addNewOrderContent, orderRef, clothesId, quantity);
    // need to remove everything from basket associated with customerId passed to this method
//
//    }




    public void addItemsToBasket(String type, String subtype,
                                 String material, String color,
                                 String size, int quantity, int customerId) {

        clothingDatabaseAccess.createClothingInventoryViewTable();

        String getClothingIdQuery = ("SELECT clothing_id FROM clothing_inventory " +
                "WHERE type_name LIKE " + "'" + type + "'" + " AND " +
                "subtype_name LIKE " + "'" + subtype + "'" + " AND " +
                "size_name LIKE " + "'" + size + "'" + " AND " +
                "color_name LIKE " + "'" + color + "'" + " AND " +
                "material_name LIKE " + "'" + material + "'");


        int selectedClothingID = jdbcTemplate.queryForObject(getClothingIdQuery, int.class);

        String addItemsToBasketQuery = """
                INSERT INTO basket_content(customer_id, clothing_id, quantity)
                VALUES(?,?,?)
                """;
        jdbcTemplate.update(addItemsToBasketQuery, customerId, selectedClothingID, quantity);


        System.out.println("Selected clothing id : " + selectedClothingID);
    }

    public void removeItemFromBasket(int clothingId) {
        jdbcTemplate.update("DELETE FROM basket_content WHERE " +
                "clothing_id = " + "" + clothingId + "");

    }

//    public void editBasketContent(int clothingId){
//        String findQuantityOfItemQuery = "SELECT quantity FROM basket_content WHERE " +
//                "clothing_id = " + "" + clothingId + "";
//
//        int quantityOfClothingItemInBasket = jdbcTemplate.queryForObject(findQuantityOfItemQuery, int.class);
//    }
    public int addCustomerInformation(String firstName, String lastName, String emailAddress, String phoneNumber) {
        String addNewCustomer = """
                    INSERT INTO customer_information(first_name, last_name, email, phone_number)
                    VALUES(?,?,?,?)
                    """;
        jdbcTemplate.update(addNewCustomer, firstName, lastName, emailAddress, phoneNumber);

        String getNewCustomerIDQuery = "SELECT customer_id FROM customer_information WHERE email LIKE " +
                "'" + emailAddress + "'";
        int customerId = 0;
        try {
            customerId = jdbcTemplate.queryForObject(getNewCustomerIDQuery, int.class);
        } catch(Exception e) {
            System.out.println("Sorry we can't make your account currently with this email, please try again later");
            // need to throw an error here
        }

        return customerId;
    }

    public void addCustomerAddressToAddressBook(int customerId, String firstLineAddress, String secondLineAddress,
                                                String cityOrTown, String postcode) {

        String addItemsToBasketQuery = """
                INSERT INTO basket_content(customer_id, first_line, second_line, city_town, county_state, postcode)
                VALUES(?,?,?,?,?,?)
                """;

        jdbcTemplate.update(addItemsToBasketQuery, customerId, firstLineAddress,
                            secondLineAddress, cityOrTown, postcode);

        // use sql query to get address id and make this method return the address id, will be needed for address book,
        // and for default addresses setting

    }





}
