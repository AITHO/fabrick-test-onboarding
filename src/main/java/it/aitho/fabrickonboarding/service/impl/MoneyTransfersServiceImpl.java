package it.aitho.fabrickonboarding.service.impl;

import it.aitho.fabrickonboarding.client.FabrickClient;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersDto;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersPayloadDto;
import it.aitho.fabrickonboarding.service.MoneyTransfersService;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransfersServiceImpl implements MoneyTransfersService {

    private final FabrickClient fabrickClient;

    public MoneyTransfersServiceImpl(FabrickClient fabrickClient) {
        this.fabrickClient = fabrickClient;
    }

    public MoneyTransfersPayloadDto makeBankTransfer(String accountId, MoneyTransfersDto moneyTransfersRequest, String timezone) {
        return fabrickClient.makeBankTransfer(accountId, moneyTransfersRequest, timezone).getPayload();
    }
}
