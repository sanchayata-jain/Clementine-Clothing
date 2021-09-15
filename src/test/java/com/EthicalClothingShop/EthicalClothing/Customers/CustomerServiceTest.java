package com.EthicalClothingShop.EthicalClothing.Customers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    @Mock
    private CustomerDataAccessServicePsql database_access;

    private CustomerService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(database_access);
    }


    @Test
    void getCustomerShouldBeNull() {
        Customer actual = underTest.getCustomer(); //given
        Customer expected = null; //when

        assertEquals(expected, actual); //then
    }

//    @ParameterizedTest()
//    @ValueSource(strings = {"sanchayatajain@gmail.com"})
//    void setCustomerSoCustomerAccountInfoShouldNotBeNull(String email) {
//        CustomerDataAccessServicePsql customerData= Mockito.mock(CustomerDataAccessServicePsql.class);
//
//
//      Mockito.when(customerData.getCustomerAccountInfo(email).thenReturn(
//                                                                          new Customer(1,
//                                                                          "Louise",
//                                                                          "Tonkin",
//                                                                           email,
//                                                                           "07943134865"
//                                                                          )));
//
//
//
//
//    }


}