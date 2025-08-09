package com.parabank.pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.List;

public class LoginPage {
    private final Page page;
    private final BrowserContext context;

    public LoginPage(Page page) {
        this.page = page;
        this.context = page.context();
    }

    public void navigate() {
        page.navigate("https://parabank.parasoft.com/parabank/index.htm");
    }

    public void login(String username, String password) {
        page.fill("input[name='username']", username);
        page.fill("input[name='password']", password);
        page.click("input[value='Log In']");
    }

    public boolean isLoginSuccessful() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.locator("li a:has-text('Log Out')")
                .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED));
        return true; // 通过等待机制隐式验证
    }

    public void logout() {
        page.click("a[href='logout.htm']");
    }

}