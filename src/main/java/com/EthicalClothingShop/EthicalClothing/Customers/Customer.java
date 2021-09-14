package com.EthicalClothingShop.EthicalClothing.Customers;

import java.math.BigInteger;
import java.util.Objects;

public class Customer {
    private String firstName;
    private String lastName;
    private String firstLineAddress;
    private String city;
    private String postcode;
    private String email;
    private BigInteger mobileNumber;
    private String password;
    private int id;

    public Customer(String firstName, String lastName, String firstLineAddress,
                    String city, String postcode, String email,
                    BigInteger mobileNumber,int id, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.firstLineAddress = firstLineAddress;
        this.city = city;
        this.postcode = postcode;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.id = id;
    }

    public Customer(int id, String firstName, String lastName, String email, BigInteger mobileNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstLineAddress() {
        return firstLineAddress;
    }

    public void setFirstLineAddress(String firstLineAddress) {
        this.firstLineAddress = firstLineAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(BigInteger mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstLineAddress='" + firstLineAddress + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber=" + mobileNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return mobileNumber == customer.mobileNumber && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(firstLineAddress, customer.firstLineAddress) && Objects.equals(city, customer.city) && Objects.equals(postcode, customer.postcode) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, firstLineAddress, city, postcode, email, mobileNumber);
    }
}
