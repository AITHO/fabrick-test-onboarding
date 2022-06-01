package it.aitho.fabrickonboarding.controller;

import it.aitho.fabrickonboarding.dto.accountbalance.AccountBalancePayload;
import it.aitho.fabrickonboarding.dto.transactions.GetTransactionsPayload;
import it.aitho.fabrickonboarding.dto.transactions.Transaction;
import it.aitho.fabrickonboarding.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FabrickAccountsController.class)
class FabrickAccountsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private static final String PATH = "/accounts";
    private static final String CURRENCY_EUR = "EUR";

    @Test
    public void shouldGetTheBalance() throws Exception {
        Mockito.when(accountService.retrieveAccountBalance(anyString()))
                .thenReturn(mockAccountBalancePayload());

        this.mockMvc.perform(get(PATH + "/123456")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("15.0"));
    }

    @Test
    public void shouldGetAccountTransactions() throws Exception {
        Mockito.when(accountService.retrieveAccountTransactions(anyString(), anyString(), anyString()))
                .thenReturn(mockGetTransactionsPayload());

        this.mockMvc.perform(get(PATH + "/123456/transactions")
                .param("fromAccountingDate", "")
                .param("toAccountingDate", ""))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.list").isNotEmpty());
    }

    private AccountBalancePayload mockAccountBalancePayload() {
        return AccountBalancePayload.builder()
                .date(LocalDate.now())
                .balance(15.0)
                .availableBalance(10.0)
                .currency(CURRENCY_EUR)
                .build();
    }

    private GetTransactionsPayload mockGetTransactionsPayload() {
        Transaction transaction = Transaction.builder()
                .amount(1200)
                .currency(CURRENCY_EUR)
                .build();

        return GetTransactionsPayload.builder()
                .list(asList(transaction))
                .build();
    }
}