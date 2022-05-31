package it.aitho.fabrickonboarding.service;

import it.aitho.fabrickonboarding.dto.accountbalance.AccountBalancePayload;

public interface AccountService {
    AccountBalancePayload retrieveAccountBalance(String accountId);
}
