package com.EthicalClothingShop.EthicalClothing.Customers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

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

//    void setCustomerAccountInfo() {
//        //given
//        String email = "KatieKeane@gmail.com";
//        Customer customer1 = new Customer(1, "Louise", "Tonkin", "Hello@hotmail.com", "09857329");
//        given(database_access.getCustomerAccountInfo(email)).willReturn(customer1);
//
//        //When
//
//        underTest.setCustomer(email);

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


//}