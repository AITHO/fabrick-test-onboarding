package it.aitho.fabrickonboarding.service.impl;

import it.aitho.fabrickonboarding.client.FabrickClient;
import it.aitho.fabrickonboarding.dto.accountbalance.AccountBalanceResponseDto;
import it.aitho.fabrickonboarding.dto.accountbalance.AccountBalancePayload;
import it.aitho.fabrickonboarding.dto.transactions.GetTransactionsPayload;
import it.aitho.fabrickonboarding.dto.transactions.GetTransactionsResponseDto;
import it.aitho.fabrickonboarding.dto.transactions.Transaction;
import it.aitho.fabrickonboarding.enums.FabrickResponseStatus;
import it.aitho.fabrickonboarding.mapper.TransactionsMapper;
import it.aitho.fabrickonboarding.repository.TransactionRepository;
import it.aitho.fabrickonboarding.repository.TransactionTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
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
    @MockBean
    private TransactionRepository transactionRepository;
    @MockBean
    private TransactionTypeRepository transactionTypeRepository;
    @Autowired
    private TransactionsMapper transactionsMapper;

    @BeforeEach
    void init() {
        accountService = new AccountServiceImpl(fabrickClient, transactionRepository, transactionTypeRepository, transactionsMapper);
    }

    @Test
    void retrieveAccountBalanceTest() {
        var accountBalanceResponse = new AccountBalanceResponseDto();
        accountBalanceResponse.setStatus(FabrickResponseStatus.OK);
        AccountBalancePayload payload = AccountBalancePayload.builder().build();
        payload.setBalance(15.0);
        accountBalanceResponse.setPayload(payload);
        Mockito.when(fabrickClient.retrieveAccountBalance(anyString())).thenReturn(accountBalanceResponse);

        var result = accountService.retrieveAccountBalance("accountId");
        Assertions.assertNotNull(result);
        Assertions.assertEquals(15.0, result.getBalance());
    }

    @Test
    void retrieveTransactionsTest() {
        GetTransactionsResponseDto getTransactionsResponse = GetTransactionsResponseDto.builder().build();
        getTransactionsResponse.setStatus(FabrickResponseStatus.OK);
        var getTransactionsPayload = GetTransactionsPayload.builder().build();
        var transaction = Transaction.builder().transactionId("12345").build();

        List<Transaction> list = List.of(transaction);
        getTransactionsPayload.setList(list);

        getTransactionsResponse.setPayload(getTransactionsPayload);

        Mockito.when(fabrickClient.retrieveAccountTransactions(anyString(), anyString(), anyString())).thenReturn(getTransactionsResponse);

        var result = accountService.retrieveAccountTransactions("accountId", "from", "to");
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getList());
        Assertions.assertEquals(1, result.getList().size());
        Assertions.assertEquals("12345", result.getList().get(0).getTransactionId());
    }
}
