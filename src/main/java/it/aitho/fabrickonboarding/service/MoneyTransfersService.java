package it.aitho.fabrickonboarding.service;

import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersRequestDto;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersPayload;

public interface MoneyTransfersService {
    MoneyTransfersPayload makeBankTransfer(String accountId, MoneyTransfersRequestDto request, String timezone);
}