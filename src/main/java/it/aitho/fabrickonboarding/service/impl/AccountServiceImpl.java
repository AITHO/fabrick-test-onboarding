package it.aitho.fabrickonboarding.service.impl;

import it.aitho.fabrickonboarding.client.FabrickClient;
import it.aitho.fabrickonboarding.dto.accountbalance.AccountBalancePayload;
import it.aitho.fabrickonboarding.dto.transactions.GetTransactionsResponseDto;
import it.aitho.fabrickonboarding.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final FabrickClient fabrickClient;

    public AccountServiceImpl(FabrickClient fabrickClient) {
        this.fabrickClient = fabrickClient;
    }

    @Override
    public AccountBalancePayload retrieveAccountBalance(String accountId) {
        return fabrickClient.retrieveAccountBalance(accountId).getPayload();
    }

    @Override
    public GetTransactionsResponseDto retrieveAccountTransactions(String accountId, String fromAccountingDate, String toAccountingDate) {
        return fabrickClient.retrieveAccountTransactions(accountId, fromAccountingDate, toAccountingDate);
    }
}
