package com.parabank.pages;

import com.microsoft.playwright.Page;
import com.parabank.models.User;

public class RegisterPage {
    private final Page page;

    public RegisterPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://parabank.parasoft.com/parabank/register.htm");
    }

    public void registerUser(User user) {
        //page.waitForLoadState(LoadState.valueOf("networkidle"));
        page.fill("input[id='customer.firstName']", user.getFirstName());
        page.fill("input[id='customer.lastName']", user.getLastName());
        page.fill("input[id='customer.address.street']", user.getAddress());
        page.fill("input[id='customer.address.city']", user.getCity());
        page.fill("input[id='customer.address.state']", user.getState());
        page.fill("input[id='customer.address.zipCode']", user.getZipCode());
        page.fill("input[id='customer.phoneNumber']", user.getPhone());
        page.fill("input[id='customer.ssn']", user.getSsn());
        page.fill("input[id='customer.username']", user.getUsername());
        page.fill("input[id='customer.password']", user.getPassword());
        page.fill("input[id='repeatedPassword']", user.getPassword());
        page.click("input[value='Register']");
    }

    public String getSuccessMessage() {
        return page.textContent("#rightPanel p");
    }
}