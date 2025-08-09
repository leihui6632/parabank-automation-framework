package com.parabank.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class TransferFundsPage {
    private final Page page;

    public TransferFundsPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://parabank.parasoft.com/parabank/transfer.htm");
    }

    public void transferFunds( String toAccount, String amount) {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.selectOption("#toAccountId", toAccount);
        page.fill("#amount", amount);
        page.click("input[value='Transfer']");
    }

    public boolean isTransferSuccessful() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        String fullText = page.locator("#showResult").textContent();
        return fullText.contains("has been transferred");
    }
}