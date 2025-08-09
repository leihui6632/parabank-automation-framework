package com.parabank.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;


public class OpenAccountPage {
    private final Page page;

    public OpenAccountPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://parabank.parasoft.com/parabank/openaccount.htm");
    }

    public void openNewSavingsAccount() {
        page.locator("a[href='openaccount.htm']").click();
        page.selectOption("#type", new SelectOption().setLabel("SAVINGS"));
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.locator("#openAccountForm input.button[value='Open New Account']").click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        PlaywrightAssertions.assertThat(page.locator("#openAccountResult h1.title")).hasText("Account Opened!");

    }

    public String getNewAccountNumber() {
        page.waitForSelector("#newAccountId", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        return page.locator("#newAccountId").textContent();
    }
}