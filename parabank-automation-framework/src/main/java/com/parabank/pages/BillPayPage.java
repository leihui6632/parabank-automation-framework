package com.parabank.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.SelectOption;

public class BillPayPage {
    private final Page page;

    public BillPayPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://parabank.parasoft.com/parabank/billpay.htm");
    }

    public void payBill(String payeeName, String address, String city, String state,
                        String zipCode, String phone, String account, String amount) {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.fill("input[name='payee.name']", payeeName);
        page.fill("input[name='payee.address.street']", address);
        page.fill("input[name='payee.address.city']", city);
        page.fill("input[name='payee.address.state']", state);
        page.fill("input[name='payee.address.zipCode']", zipCode);
        page.fill("input[name='payee.phoneNumber']", phone);
        page.fill("input[name='payee.accountNumber']", "123");
        page.fill("input[name='verifyAccount']", "123");
        page.fill("input[name='amount']", amount);
        page.locator("select[name='fromAccountId']")
                .selectOption(new SelectOption().setLabel(account));
        page.click("input[value='Send Payment']");
    }

    public boolean isPaymentSuccessful() {
        return page.textContent("div#billpayResult h1.title").contains("Bill Payment Complete");
    }
}