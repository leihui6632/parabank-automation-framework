package com.parabank.api;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.*;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.options.LoadState;


public class FindTransactionByAmountTest {

    public void testApiViaPageNavigation(String accountNumber, Page page) {
        String url = "https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/"
                + accountNumber + "/transactions/amount/105";

        Response response = page.waitForResponse(url, () -> {
            page.navigate(url);
        });
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertEquals(200, response.status(), "API response status code should be 200");

        String responseText = response.text();
        System.out.println("ResponseText: " + responseText);

        try {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseText);


            assertTrue(rootNode.isArray(), "The response should be a JSON array");
            assertTrue(rootNode.size() > 0, "The response array should not be empty");

            // Get the first transaction record
            JsonNode transaction = rootNode.get(0);

            // Validate each field value precisely
            assertEquals(accountNumber, transaction.get("accountId").asText(), "Account ID does not match");
            assertEquals("Debit", transaction.get("type").asText(), "Transaction type mismatch");
            assertEquals(105.00, transaction.get("amount").asDouble(), 0.001, "Transaction amount mismatch");
            assertEquals("Bill Payment to Test Payee", transaction.get("description").asText(), "Transaction description mismatch");
            assertNotNull(transaction.get("date").asLong(), "Transaction date should be a timestamp");

        } catch (JsonParseException e) {
            System.err.println("JSON format error:" + e.getMessage());
            fail("JSON format error:" + e.getMessage());
        } catch (JsonMappingException e) {
            System.err.println("JSON mapping error:" + e.getMessage());
            fail("JSON mapping error:" + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error:" + e.getMessage());
            fail("IO error:" + e.getMessage());
        }
    }


}