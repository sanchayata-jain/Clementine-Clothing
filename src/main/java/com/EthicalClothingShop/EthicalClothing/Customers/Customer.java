package com.EthicalClothingShop.EthicalClothing.Customers;

import java.util.Objects;

public class Customer {
    private String firstName;
    private String surName;
    private String firstLineAddress;
    private String city;
    private String postcode;
    private String email;
    private String mobileNumber;
    private int id;

    public Customer(String firstName, String surName, String firstLineAddress, String city, String postcode, String email, String mobileNumber,int id) {
        this.firstName = firstName;
        this.surName = surName;
        this.firstLineAddress = firstLineAddress;
        this.city = city;
        this.postcode = postcode;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
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
                ", surName='" + surName + '\'' +
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
        return mobileNumber == customer.mobileNumber && Objects.equals(firstName, customer.firstName) && Objects.equals(surName, customer.surName) && Objects.equals(firstLineAddress, customer.firstLineAddress) && Objects.equals(city, customer.city) && Objects.equals(postcode, customer.postcode) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surName, firstLineAddress, city, postcode, email, mobileNumber);
    }
}
