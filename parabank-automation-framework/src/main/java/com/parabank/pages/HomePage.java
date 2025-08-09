package com.parabank.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class HomePage {
    private final Page page;

    public HomePage(Page page) {
        this.page = page;
    }

    public boolean verifyGlobalNavigationMenu() {
        try {
            //Verify the existence of all primary navigation menu items
            boolean hasSolutions = page.locator("li.Solutions").textContent().equals("Solutions");
            boolean hasAboutUs = page.locator("#headerPanel").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("About Us")).isVisible();
            boolean hasServices = page.locator("#headerPanel").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Services")).isVisible();
            boolean hasProducts = page.locator("#headerPanel").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Products")).isVisible();
            boolean hasLocations = page.locator("#headerPanel").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Locations")).isVisible();
            boolean hasAdminPage = page.locator("#headerPanel").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Admin Page")).isVisible();

            return hasSolutions && hasAboutUs && hasServices && hasProducts && hasLocations && hasAdminPage;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickThirdMenu() {

        Locator targetLink = page.locator("#headerPanel").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Services"));
        targetLink.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
        targetLink.click();
        page.locator("#headerPanel li:has-text('" + "Services" + "')").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));

        // Verify other menu items become links (clickable state)
        Locator otherMenus = page.locator("#headerPanel")
                .getByRole(AriaRole.LINK)
                .filter(new Locator.FilterOptions()
                        .setHasNot(page.getByText("Services")));
        return otherMenus.count() == 7;

    }
}