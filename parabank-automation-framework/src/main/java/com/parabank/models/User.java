package com.parabank.models;

import com.github.javafaker.Faker;

import java.util.UUID;

public class User {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phone;
    private String ssn;
    private String username;
    private String password;

    public User() {
        Faker faker = new Faker();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.address = faker.address().streetAddress();
        this.city = faker.address().city();
        this.state = faker.address().state();
        this.zipCode = faker.address().zipCode();
        this.phone = faker.phoneNumber().phoneNumber();
        this.ssn = faker.idNumber().ssnValid();
        this.username = faker.name().username().substring(0, 8) + UUID.randomUUID().toString().substring(0, 8);
        this.password = faker.internet().password(8, 16, true, true, true);
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getSsn() {
        return ssn;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}