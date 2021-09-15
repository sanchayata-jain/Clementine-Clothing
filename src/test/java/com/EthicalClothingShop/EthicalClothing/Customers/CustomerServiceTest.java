package com.EthicalClothingShop.EthicalClothing.Customers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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


    void getAllCustomers {}


}