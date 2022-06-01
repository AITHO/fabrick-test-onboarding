package it.aitho.fabrickonboarding.service;

import it.aitho.fabrickonboarding.dto.accountbalance.AccountBalancePayload;
import it.aitho.fabrickonboarding.dto.transactions.GetTransactionsResponseDto;

public interface AccountService {
    AccountBalancePayload retrieveAccountBalance(String accountId);

    GetTransactionsResponseDto retrieveAccountTransactions(String accountId, String fromAccountingDate, String toAccountingDate);
}
