package com.parabank.ui;


import com.parabank.BaseTest;
import com.parabank.api.FindTransactionByAmountTest;
import com.parabank.models.User;
import com.parabank.pages.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ParaBankUITests extends BaseTest {
    private User testUser;
    private String accountNumber;

    @Test
    @DisplayName("When the user completes all required registration information, the registration should be successful.")
    void testUserRegistrationAndAccountCreation() {
        // 1. Navigate to the ParaBank application
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        assertTrue(page.title().contains("ParaBank"));

        // 2. User registration\
        RegisterPage registerPage = new RegisterPage(page);
        registerPage.navigate();
        testUser = new User();
        registerPage.registerUser(testUser);
        assertTrue(registerPage.getSuccessMessage().contains("successfully"));

        // 3. User login
        loginPage.logout();
        loginPage.login(testUser.getUsername(), testUser.getPassword());
        assertTrue(loginPage.isLoginSuccessful());


        //4. Verify the global navigation menu on the homepage functions as expected.
        HomePage homePage = new HomePage(page);
        assertTrue(homePage.verifyGlobalNavigationMenu());
        assertTrue(homePage.clickThirdMenu());

        // 5. Create a savings account
        OpenAccountPage openAccountPage = new OpenAccountPage(page);
        openAccountPage.openNewSavingsAccount();
        accountNumber = openAccountPage.getNewAccountNumber();
        assertNotNull(accountNumber);

        // 6. Verify account overview
        AccountsOverviewPage accountsOverviewPage = new AccountsOverviewPage(page);
        accountsOverviewPage.navigate();
        assertTrue(accountsOverviewPage.isAccountVisible(accountNumber));
        assertNotNull(accountsOverviewPage.getAccountBalance(accountNumber));

        // 7. Transfer
        TransferFundsPage transferFundsPage = new TransferFundsPage(page);
        transferFundsPage.navigate();
        transferFundsPage.transferFunds(accountNumber, "100");
        assertTrue(transferFundsPage.isTransferSuccessful());

        // 8. Pay bills
        BillPayPage billPayPage = new BillPayPage(page);
        billPayPage.navigate();
        billPayPage.payBill("Test Payee", "123 Main St", "Anytown", "CA", "12345",
                "1234567890", accountNumber, "105");
        assertTrue(billPayPage.isPaymentSuccessful());


        // 9. API Testing

        FindTransactionByAmountTest findTransactionByAmountTest = new FindTransactionByAmountTest();
        findTransactionByAmountTest.testApiViaPageNavigation(accountNumber, page);

    }

}
