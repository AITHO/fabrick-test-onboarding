package it.aitho.fabrickonboarding.service;

import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersDto;

public interface MoneyTransfersService {
    String makeBankTransfer(String accountId, MoneyTransfersDto request, String timezone);
}