package it.aitho.fabrickonboarding.service.impl;

import it.aitho.fabrickonboarding.client.FabrickClient;
import it.aitho.fabrickonboarding.dto.AccountBalanceDto;
import it.aitho.fabrickonboarding.dto.AccountBalancePayload;
import it.aitho.fabrickonboarding.enums.FabrickResponseStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class AccountServiceImplTest {

    @MockBean
    private FabrickClient fabrickClient;
    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void init() {
        accountService = new AccountServiceImpl(fabrickClient);
    }

    @Test
    void retrieveAccountBalanceTest() {
        var accountBalanceResponse = new AccountBalanceDto();
        accountBalanceResponse.setStatus(FabrickResponseStatus.OK);
        AccountBalancePayload payload = new AccountBalancePayload();
        payload.setBalance(15.0);
        accountBalanceResponse.setPayload(payload);
        Mockito.when(fabrickClient.retrieveAccountBalance(anyString())).thenReturn(accountBalanceResponse);

        var result = accountService.retrieveAccountBalance("accountId");
        Assertions.assertNotNull(result);
        Assertions.assertEquals(15.0, result.getBalance());
    }
}
