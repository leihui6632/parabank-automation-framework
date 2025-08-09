package com.parabank.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;


public class AccountsOverviewPage {
    private final Page page;

    public AccountsOverviewPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.navigate("https://parabank.parasoft.com/parabank/overview.htm");
    }

    public boolean isAccountVisible(String accountNumber) {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        return page.locator("a:has-text('" + accountNumber + "')").isVisible();
    }

    public String getAccountBalance(String accountNumber) {
        return page.locator("tr:has(a:has-text('" + accountNumber + "')) td:nth-child(2)").textContent();
    }
}