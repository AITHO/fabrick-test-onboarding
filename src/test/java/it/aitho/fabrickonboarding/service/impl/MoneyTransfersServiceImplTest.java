package it.aitho.fabrickonboarding.service.impl;

import it.aitho.fabrickonboarding.client.FabrickClient;
import it.aitho.fabrickonboarding.dto.moneytransfers.Amount;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersRequestDto;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersPayload;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersResponseDto;
import it.aitho.fabrickonboarding.enums.FabrickResponseStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class MoneyTransfersServiceImplTest {

    @MockBean
    private FabrickClient fabrickClient;

    @InjectMocks
    private MoneyTransfersServiceImpl moneyTransfersService;

    @BeforeEach
    void init() {
        moneyTransfersService = new MoneyTransfersServiceImpl(fabrickClient);
    }

    @Test
    void makeBankTransferTest() {
        MoneyTransfersResponseDto moneyTransfersResponseDto = mockMoneyTransfersResponse();
        Mockito.when(fabrickClient.makeBankTransfer(anyString(), any(), anyString())).thenReturn(moneyTransfersResponseDto);

        var result = moneyTransfersService.makeBankTransfer("123", new MoneyTransfersRequestDto(), "");
        Assertions.assertNotNull(result);
        Assertions.assertEquals(120, result.getAmount().getCreditorAmount());
    }

    private MoneyTransfersResponseDto mockMoneyTransfersResponse() {
        Amount amount = new Amount();
        amount.setCreditorAmount(120);

        MoneyTransfersPayload payload = new MoneyTransfersPayload();
        payload.setAmount(amount);

        MoneyTransfersResponseDto moneyTransfersResponseDto = new MoneyTransfersResponseDto();
        moneyTransfersResponseDto.setPayload(payload);
        moneyTransfersResponseDto.setStatus(FabrickResponseStatus.OK);

        return moneyTransfersResponseDto;
    }
}