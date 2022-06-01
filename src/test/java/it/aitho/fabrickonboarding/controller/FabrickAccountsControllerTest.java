package it.aitho.fabrickonboarding.controller;

import it.aitho.fabrickonboarding.dto.accountbalance.AccountBalancePayload;
import it.aitho.fabrickonboarding.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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

    @Test
    void shouldGetTheBalance() throws Exception {
        var response = new AccountBalancePayload();
        response.setBalance(15.0);
        Mockito.when(accountService.retrieveAccountBalance("123456")).thenReturn(response);
        this.mockMvc.perform(get("/accounts/123456")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("15.0"));
    }
}