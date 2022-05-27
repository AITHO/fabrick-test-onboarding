package it.aitho.fabrickonboarding.controller;

import it.aitho.fabrickonboarding.dto.AccountBalancePayload;
import it.aitho.fabrickonboarding.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FabrickOnboardingController {
    private final AccountService accountService;

    public FabrickOnboardingController(AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<AccountBalancePayload> accountBalance(@PathVariable("accountId") String accountId) {
        return ResponseEntity.ok(accountService.retrieveAccountBalance(accountId));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("ok fabrick!");
    }
}
