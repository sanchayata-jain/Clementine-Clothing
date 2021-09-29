package com.EthicalClothingShop.EthicalClothing.Customers;


import com.EthicalClothingShop.EthicalClothing.ClothingLine.ClothingDataAccessServicePsql;
import org.javatuples.Pair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


@Repository
public class CustomerDataAccessServicePsql implements CustomerDAO{
    ClothingDataAccessServicePsql clothingDatabaseAccess; //= new ClothingDataAccessServicePsql();
    private JdbcTemplate jdbcTemplate;
//    private final CustomerDAO customerDAO;

    // constructor
    public CustomerDataAccessServicePsql(JdbcTemplate jdbcTemplate,
                                         ClothingDataAccessServicePsql clothingDataAccessServicePsql) {
        this.jdbcTemplate = jdbcTemplate;
        this.clothingDatabaseAccess = clothingDataAccessServicePsql;
//        this.customerDAO = customerDAO;
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


    public Customer getCustomerAccountInfo(String customerEmail, String password) {
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
        String getCustomerPassword = "SELECT password FROM customer_information" +
                " WHERE email LIKE " + "'" + customerEmail + "'";

        String firstName = jdbcTemplate.queryForObject(getCustomerFirstName, String.class);
        String lastName = jdbcTemplate.queryForObject(getCustomerLastName, String.class);
        String mobile = jdbcTemplate.queryForObject(getCustomerMobile, String.class);
        String emailFromDB = jdbcTemplate.queryForObject(getCustomerEmail, String.class);
        String passwordFromDB = jdbcTemplate.queryForObject(getCustomerPassword, String.class);


        int customerId = 0;
        try {
            customerId = jdbcTemplate.queryForObject(getCustomerId, int.class);
            System.out.println(customerId);
        } catch (Exception e) {
            System.out.println("uh oh");
        }
        Customer customerInfo = new Customer(customerId, firstName, lastName, emailFromDB, mobile, passwordFromDB);
        return customerInfo;
        //return customerDAO.getCustomerAccountInfo(customerEmail);
    }


    public int createOrderRef(int customerId, LocalDate orderDate, LocalTime orderTime) throws Exception {
        String ensureCustomerHasItemsInBasket = "SELECT customer_id FROM basket_content WHERE " +
                "customer_id = " + customerId;
        try {
            int customerIdInBasket = jdbcTemplate.queryForObject(ensureCustomerHasItemsInBasket, int.class);
        } catch(Exception e) {
            // throw an exception
            throw new Exception("No items in basket to purchase");
        }


        String addNewOrderRecord = """
    INSERT INTO orders_information(customer_id, order_date, order_time)
                                          VALUES(?, ?, ?)
    """;
        jdbcTemplate.update(addNewOrderRecord, customerId, orderDate, orderTime);

//        String getOrderRefQuery = "SELECT order_id FROM orders_information WHERE customer_id = " + "'" +  customerId + "'" +
//                " AND order_date = " + "'" + orderDate + "'" + " AND order_time = " + "'" + orderTime + "'";

        String getOrderRefQuery = "SELECT order_id FROM orders_information WHERE customer_id = " + customerId + " AND order_date = '" + orderDate + "' AND order_time = '" + orderTime + "'";
        int orderRef = jdbcTemplate.queryForObject(getOrderRefQuery, int.class);


        System.out.println("order ID" + orderRef);

        return orderRef;
    }


    public void populateOrderContentsTable(int customerId, int orderRef) {
        //String getBasketContentClothing = "FOR clothing_id IN " + getBasketContentQuery + " LOOP";
        String getNumberOfItems = "SELECT COUNT(customer_id) FROM basket_content WHERE customer_id = " + "'" + customerId + "'";
        int numberOfBasketItems = jdbcTemplate.queryForObject(getNumberOfItems, int.class);

        String dropViewTableQuery = """
                DROP VIEW IF EXISTS basket_content_view;
                """;
        jdbcTemplate.execute(dropViewTableQuery);

        // creating a table with only  =>>>>>> clothing_id | quantity
        String getBasketContentQuery = "CREATE TEMP VIEW basket_content_view " +
                "AS " +
                "SELECT ROW_NUMBER() OVER() AS num_row, clothing_id, quantity " +
                "FROM basket_content WHERE customer_id = " + "'" + customerId + "'";

        jdbcTemplate.execute(getBasketContentQuery);

        for (int i = 1; i <= numberOfBasketItems; i++) {
            String getBasketItemIdQuery = "SELECT clothing_id " +
                    "FROM basket_content_view WHERE num_row = " + i ;

            String getBasketItemQuantityQuery = "SELECT quantity " +
                    "FROM basket_content_view WHERE num_row = " + i ;

            int clothingId = jdbcTemplate.queryForObject(getBasketItemIdQuery, int.class);
            int quantity = jdbcTemplate.queryForObject(getBasketItemQuantityQuery, int.class);

            String addNewOrderContent = """
            INSERT INTO order_contents(order_id, clothing_id, quantity)
                                                  VALUES(?, ?, ?)
            """;
            jdbcTemplate.update(addNewOrderContent, orderRef, clothingId, quantity);

            String getInventoryStock = "SELECT quantity FROM clothing_items_inventory WHERE clothing_id = " + "" + clothingId + "";
            int currentClothingItemStock = jdbcTemplate.queryForObject(getInventoryStock, int.class);
            int newStock = currentClothingItemStock - quantity;

            String updateInventoryStock = "UPDATE clothing_items_inventory SET quantity = " + "" + newStock + "" + " WHERE " +
                    "clothing_id = " + "" + clothingId + "";
            jdbcTemplate.update(updateInventoryStock);
        }

//     need to remove everything from basket associated with customerId passed to this method
        jdbcTemplate.update("DELETE FROM basket_content WHERE " +
                "customer_id = " + "" + customerId + "");

    }



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

        // using sql check if selectedClothingID is already in basket_content table
        String checkIfClothingItemExistsInBasket = "SELECT clothing_id FROM basket_content " +
                "WHERE clothing_id = " + "" + selectedClothingID + "";

        boolean itemFoundInBasket = true;
        try {
            int clothingIdInBasket = jdbcTemplate.queryForObject(checkIfClothingItemExistsInBasket, int.class);
        } catch (Exception e) {
            itemFoundInBasket = false;
            String addItemsToBasketQuery = """
                INSERT INTO basket_content(customer_id, clothing_id, quantity)
                VALUES(?,?,?)
                """;
            jdbcTemplate.update(addItemsToBasketQuery, customerId, selectedClothingID, quantity);
        }
        if (itemFoundInBasket) {
            // record in table found so we will increase quantity of clothing item for that customer
            String increaseQuantity = "SELECT quantity FROM basket_content " + "WHERE clothing_id = " + "" + selectedClothingID + "" + " AND customer_id = " + "" + customerId + "";
            int currentQuantity = jdbcTemplate.queryForObject(increaseQuantity, int.class);
            int newQuantity = currentQuantity + quantity;

            String updateItemInBasketQuantity = "UPDATE basket_content SET quantity = " + "" + newQuantity + "" + " WHERE " +
                    "clothing_id = " + "" + selectedClothingID + "" + "" + " AND customer_id = " + "" + customerId + "";
            jdbcTemplate.update(updateItemInBasketQuantity);
        }
    }


    public void removeItemFromBasket(int clothingId) {
        jdbcTemplate.update("DELETE FROM basket_content WHERE " +
                "clothing_id = " + "" + clothingId + "");

    }


    public void editItemQuantityInBasket(int customerId, int clothingId, boolean isQuantityIncreasing){
        String findQuantityOfItemQuery = "SELECT quantity FROM basket_content WHERE " +
                "clothing_id = " + "" + clothingId + "" + " AND customer_id = " + "" + customerId + "";

        int quantityOfClothingItemInBasket = jdbcTemplate.queryForObject(findQuantityOfItemQuery, int.class);

        int updated_quantity = 0;
        if (isQuantityIncreasing) {
            updated_quantity = quantityOfClothingItemInBasket + 1;
        } else {
            if (quantityOfClothingItemInBasket > 1) {
                updated_quantity = quantityOfClothingItemInBasket - 1;
            }
        }
        //query to update basket table
        String updateBasketQuery = "UPDATE basket_content SET quantity = " + "" + updated_quantity + "" + " WHERE " +
                "clothing_id = " + "" + clothingId + "" + " AND customer_id = " + "" + customerId + "";
        jdbcTemplate.update(updateBasketQuery);
    }



    public ArrayList getBasketItems(int customerId) {
        String getNumberOfItems = "SELECT COUNT(customer_id) FROM basket_content WHERE customer_id = " + "'" + customerId + "'";
        int numberOfBasketItems = jdbcTemplate.queryForObject(getNumberOfItems, int.class);

        String dropViewTableQuery = """
                DROP VIEW IF EXISTS basket_items_view;
                """;
        jdbcTemplate.execute(dropViewTableQuery);

        String getBasketItemsQuery = "CREATE TEMP VIEW basket_items_view " +
                "AS " +
                "SELECT ROW_NUMBER() OVER() AS num_row, clothing_id, quantity FROM basket_content" +
                " WHERE customer_id = " + customerId;
         jdbcTemplate.execute(getBasketItemsQuery);

         ArrayList<Pair<Integer, Integer>> basketItems= new ArrayList<Pair<Integer, Integer>>();
         for (int i = 1; i <= numberOfBasketItems; i++) {
             String getBasketItemIdQuery = "SELECT clothing_id " +
                     "FROM basket_items_view WHERE num_row = " + i ;

             String getBasketItemQuantityQuery = "SELECT quantity " +
                     "FROM basket_items_view WHERE num_row = " + i ;

             int clothingId = jdbcTemplate.queryForObject(getBasketItemIdQuery, int.class);
             int quantity = jdbcTemplate.queryForObject(getBasketItemQuantityQuery, int.class);
             Pair<Integer, Integer> basketIdAndQuantity = new Pair<Integer, Integer>(clothingId, quantity);
             basketItems.add(basketIdAndQuantity);
         }
         return basketItems;
    }


    public int addCustomerInformation(String firstName, String lastName, String emailAddress,
                                      String phoneNumber, String password) {

        String addNewCustomer = """
                    INSERT INTO customer_information(first_name, last_name, email, phone_number, password)
                    VALUES(?,?,?,?,?)
                    """;
        jdbcTemplate.update(addNewCustomer, firstName, lastName, emailAddress, phoneNumber, password);

        String getNewCustomerIDQuery = "SELECT customer_id FROM customer_information WHERE email LIKE " +
                "'" + emailAddress + "'";

        int customerId = jdbcTemplate.queryForObject(getNewCustomerIDQuery, int.class);

        return customerId;
    }


    public void deleteCustomer(String email, String password) {
        String getCustomerIDQuery = "SELECT customer_id FROM customer_information WHERE email LIKE " +
                "'" + email + "'";
        int customerId =  jdbcTemplate.queryForObject(getCustomerIDQuery, int.class);
        String getOrderIdQuery = "SELECT order_id FROM orders_information WHERE customer_id= " +
                customerId;
        try {
            int orderId = jdbcTemplate.queryForObject(getOrderIdQuery, int.class);

            jdbcTemplate.update("DELETE FROM order_contents WHERE " +
                    "order_id = " + "" + orderId + "");
            jdbcTemplate.update("DELETE FROM orders_information WHERE " +
                    "customer_id = " + "" + customerId + "");
        } catch (Exception e) {
            System.out.println("Nothing in here to delete");
        }
        try {
            jdbcTemplate.update("DELETE FROM basket_content WHERE " +
                    "customer_id = " + "" + customerId + "");
        } catch (Exception e) {
            System.out.println("This customer has currently not got any items in their basket");
        }
        jdbcTemplate.update("DELETE FROM default_delivery_address WHERE " +
                "customer_id = " + "" + customerId + "");
        jdbcTemplate.update("DELETE FROM default_billing_address WHERE " +
                "customer_id = " + "" + customerId + "");
        jdbcTemplate.update("DELETE FROM customer_address_book WHERE " +
                "customer_id = " + "" + customerId + "");
        jdbcTemplate.update("DELETE FROM addresses WHERE " +
                "customer_id = " + "" + customerId + "");
        jdbcTemplate.update("DELETE FROM customer_information WHERE " +
                "customer_id = " + "" + customerId + "");
    }

//    public int getCustomerId(String email, String password)  {
//        String getCustomerIDQuery = "SELECT customer_id FROM customer_information WHERE email LIKE " +
//                "'" + email + "'" + " AND password LIKE " + "'" + password + "'";
//        int customerId =  jdbcTemplate.queryForObject(getCustomerIDQuery, int.class);
//
//        return customerId;
//    }

    public void editCustomer(int customerId, String firstName, String lastName, String mobileNumber) {
        String editFirstNameQuery = "UPDATE customer_information SET first_name= " + "'"+ firstName +"'" +
                " WHERE customer_id = " + customerId;
        String editLastNameQuery = "UPDATE customer_information SET last_name= " + "'"+ lastName +"'" +
                " WHERE customer_id = " + customerId;
        String editMobileNumberQuery = "UPDATE customer_information SET phone_number= " + "'"+ mobileNumber +"'" +
                " WHERE customer_id = " + customerId;
        if (firstName != null) {
            jdbcTemplate.update(editFirstNameQuery);
        }
        if (lastName != null) {
            jdbcTemplate.update(editLastNameQuery);
        }
        if (mobileNumber != null) {
            jdbcTemplate.update(editMobileNumberQuery);
        }
    }


    public int addCustomerAddressToAddressBook(int customerId, String firstLineAddress, String secondLineAddress,
                                                String cityOrTown, String countyOrState, String postcode) {

        String addAddress = """
                INSERT INTO addresses(customer_id, first_line, second_line, city_town, county_state, postcode)
                VALUES(?,?,?,?,?,?)
                """;

        jdbcTemplate.update(addAddress, customerId, firstLineAddress,
                            secondLineAddress, cityOrTown, countyOrState, postcode);

        String getCustomersAddressIdQuery = ("SELECT address_id FROM addresses " +
                "WHERE customer_id = " + "" + customerId + "" + " AND " +
                "first_line LIKE " + "'" + firstLineAddress + "'" + " AND " +
                "city_town LIKE " + "'" + cityOrTown + "'" + " AND " +
                "county_state LIKE " + "'" + countyOrState + "'" + " AND " +
                "postcode LIKE " + "'" + postcode + "'");

        int addressId = jdbcTemplate.queryForObject(getCustomersAddressIdQuery, int.class);

        return addressId;
    }



    public void addToCustomerAddressBook(int customerId, int addressId) {
        String insertIntoCustomerAddressBookQuery = """
                INSERT INTO customer_address_book(customer_id, address_id)
                VALUES(?,?)
                """;
        jdbcTemplate.update(insertIntoCustomerAddressBookQuery, customerId, addressId);
    }

//    public void getCustomerAddressBook(int customerId) {
//       String getCustomerAddresses = "SELECT first_line, second_line, city_town, county_state, postcode FROM addresses WHERE " +
//                "customer_id = " + "" + customerId + "";
//
//       //jdbcTemplate.query(getCustomerAddresses);
//    }


    public void addDefaultDeliveryAddress(int customerId, int addressId) {
        String insertDefaultDeliveryQuery = """
                INSERT INTO default_delivery_address(customer_id, address_id)
                VALUES(?,?)
                """;
        jdbcTemplate.update(insertDefaultDeliveryQuery, customerId, addressId);
    }


    public void addDefaultBillingAddress(int customerId, int addressId) {
        String insertDefaultBillingQuery = """
                INSERT INTO default_billing_address(customer_id, address_id)
                VALUES(?,?)
                """;
        jdbcTemplate.update(insertDefaultBillingQuery, customerId, addressId);
    }
}
