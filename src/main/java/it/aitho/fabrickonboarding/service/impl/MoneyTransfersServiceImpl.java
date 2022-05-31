package it.aitho.fabrickonboarding.service.impl;

import it.aitho.fabrickonboarding.client.FabrickClient;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersDto;
import it.aitho.fabrickonboarding.service.MoneyTransfersService;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransfersServiceImpl implements MoneyTransfersService {

    private final FabrickClient fabrickClient;

    public MoneyTransfersServiceImpl(FabrickClient fabrickClient) {
        this.fabrickClient = fabrickClient;
    }

    public String makeBankTransfer(String accountId, MoneyTransfersDto moneyTransfersRequest) {
        return fabrickClient.makeBankTransfer(accountId, moneyTransfersRequest);
    }
}
