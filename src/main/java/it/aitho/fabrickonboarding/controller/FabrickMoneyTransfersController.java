package it.aitho.fabrickonboarding.controller;

import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersDto;
import it.aitho.fabrickonboarding.service.MoneyTransfersService;
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

    private final MoneyTransfersService moneyTransfersService;

    public FabrickMoneyTransfersController(MoneyTransfersService moneyTransfersService) {
        this.moneyTransfersService = moneyTransfersService;
    }

    @PostMapping("{accountId}")
    public ResponseEntity<String> doMoneyTransfer(@PathVariable("accountId") String accountId, @Valid @RequestBody MoneyTransfersDto dto) {
        return ResponseEntity.ok(this.moneyTransfersService.makeBankTransfer(accountId, dto));
    }

}
