package it.aitho.fabrickonboarding.service.impl;

import it.aitho.fabrickonboarding.client.FabrickClient;
import it.aitho.fabrickonboarding.dto.accountbalance.AccountBalanceDto;
import it.aitho.fabrickonboarding.dto.accountbalance.AccountBalancePayload;
import it.aitho.fabrickonboarding.dto.transactions.GetTransactionsResponseDto;
import it.aitho.fabrickonboarding.dto.transactions.Transaction;
import it.aitho.fabrickonboarding.enums.FabrickResponseStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

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

    @Test
    void retrieveTransactionsTest() {
        var getTransactionsResponseDto = new GetTransactionsResponseDto();
        var transaction = new Transaction();
        transaction.setTransactionId("12345");
        List<Transaction> list = List.of(transaction);
        getTransactionsResponseDto.setList(list);

        Mockito.when(fabrickClient.retrieveAccountTransactions(anyString(), anyString(), anyString())).thenReturn(getTransactionsResponseDto);

        var result = accountService.retrieveAccountTransactions("accountId", "from", "to");
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getList());
        Assertions.assertEquals(1, result.getList().size());
        Assertions.assertEquals("12345", result.getList().get(0).getTransactionId());
    }
}
