package it.aitho.fabrickonboarding.controller;

import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersDto;
import it.aitho.fabrickonboarding.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("money-transfers")
public class FabrickMoneyTransfersController {
    private final AccountService accountService;

    public FabrickMoneyTransfersController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("{accountId}")
    public ResponseEntity<MoneyTransfersDto> doMoneyTransfer(@PathVariable("accountId") String accountId, @Valid @RequestBody MoneyTransfersDto dto) {
        return ResponseEntity.ok(dto);
    }

}
