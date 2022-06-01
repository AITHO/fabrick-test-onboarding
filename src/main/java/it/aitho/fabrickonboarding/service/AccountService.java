package it.aitho.fabrickonboarding.service;

import it.aitho.fabrickonboarding.dto.accountbalance.AccountBalancePayload;
import it.aitho.fabrickonboarding.dto.transactions.GetTransactionsPayload;

public interface AccountService {
    AccountBalancePayload retrieveAccountBalance(String accountId);

    GetTransactionsPayload retrieveAccountTransactions(String accountId, String fromAccountingDate, String toAccountingDate);
}
