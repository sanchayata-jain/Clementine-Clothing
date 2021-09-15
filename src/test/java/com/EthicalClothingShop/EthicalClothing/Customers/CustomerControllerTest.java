package com.EthicalClothingShop.EthicalClothing.Customers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerControllerTest {
    @Mock
    private CustomerService customerService;

    private CustomerController underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerController(customerService);
    }

    @ParameterizedTest
    void itCanAddNewCustomer(){


    }



}