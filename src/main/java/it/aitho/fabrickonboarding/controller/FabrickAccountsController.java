package it.aitho.fabrickonboarding.controller;

import it.aitho.fabrickonboarding.dto.accountbalance.AccountBalancePayload;
import it.aitho.fabrickonboarding.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts")
public class FabrickAccountsController {

    private final AccountService accountService;

    public FabrickAccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("{accountId}")
    public ResponseEntity<AccountBalancePayload> accountBalance(@PathVariable("accountId") String accountId) {
        return ResponseEntity.ok(accountService.retrieveAccountBalance(accountId));
    }
}
