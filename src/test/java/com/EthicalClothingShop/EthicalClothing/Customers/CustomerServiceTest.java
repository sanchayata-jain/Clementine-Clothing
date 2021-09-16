package com.EthicalClothingShop.EthicalClothing.Customers;

import com.EthicalClothingShop.EthicalClothing.ClothingLine.ClothingService;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    private CustomerService underTest;

    @Mock
    private CustomerDataAccessServicePsql databaseAccess;
    private AutoCloseable autoCloseable;
    private ClothingService clothingService;
    private Customer customerAccountInfo;

    @BeforeEach
    void setUp() {
        autoCloseable =  MockitoAnnotations.openMocks(this);
        underTest = new CustomerService(databaseAccess, clothingService);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void customerMakesPurchase() {

        // given
        int customerId = 5;
        LocalDate orderDate = LocalDate.now();
        LocalTime orderTime = LocalTime.now();
        int orderReference = 1;

        when(databaseAccess.createOrderRef(customerId, orderDate, orderTime)).thenReturn(orderReference);

        // when
        int expected = underTest.customerMakesPurchase();

        // then
        assertEquals(expected >= 1, true);
    }

    @Test
    @Disabled
    void addItemsToBasket() {
    }

    @Test
    @Disabled
    void removeItemFromBasket() {
    }

    @Test
    @Disabled
    void editItemQuantityInBasket() {
    }

    @Test
    @Disabled
    void getCustomerBasketContent() {
    }

    @Test
    @Disabled
    void addNewCustomerAccount() {
    }
}