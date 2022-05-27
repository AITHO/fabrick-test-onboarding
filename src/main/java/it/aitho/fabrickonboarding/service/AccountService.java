package it.aitho.fabrickonboarding.service;

import it.aitho.fabrickonboarding.dto.AccountBalancePayload;

public interface AccountService {
    AccountBalancePayload retrieveAccountBalance(String accountId);
}
