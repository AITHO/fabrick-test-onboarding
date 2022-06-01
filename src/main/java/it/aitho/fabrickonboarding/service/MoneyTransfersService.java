package it.aitho.fabrickonboarding.service;

import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersDto;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersResponseDto;

public interface MoneyTransfersService {
    MoneyTransfersResponseDto makeBankTransfer(String accountId, MoneyTransfersDto request, String timezone);
}