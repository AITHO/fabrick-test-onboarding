package it.aitho.fabrickonboarding.service;

import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersDto;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersPayloadDto;

public interface MoneyTransfersService {
    MoneyTransfersPayloadDto makeBankTransfer(String accountId, MoneyTransfersDto request, String timezone);
}