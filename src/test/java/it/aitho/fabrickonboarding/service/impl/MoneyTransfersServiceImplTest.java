package it.aitho.fabrickonboarding.service.impl;

import it.aitho.fabrickonboarding.client.FabrickClient;
import it.aitho.fabrickonboarding.dto.moneytransfers.Amount;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersDto;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersPayloadDto;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersResponse;
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
        MoneyTransfersResponse moneyTransfersResponse = mockMoneyTransfersResponse();
        Mockito.when(fabrickClient.makeBankTransfer(anyString(), any(), anyString())).thenReturn(moneyTransfersResponse);

        var result = moneyTransfersService.makeBankTransfer("123", new MoneyTransfersDto(), "");
        Assertions.assertNotNull(result);
        Assertions.assertEquals(120, result.getAmount().getCreditorAmount());
    }

    private MoneyTransfersResponse mockMoneyTransfersResponse() {
        Amount amount = new Amount();
        amount.setCreditorAmount(120);

        MoneyTransfersPayloadDto payload = new MoneyTransfersPayloadDto();
        payload.setAmount(amount);

        MoneyTransfersResponse moneyTransfersResponse = new MoneyTransfersResponse();
        moneyTransfersResponse.setPayload(payload);
        moneyTransfersResponse.setStatus(FabrickResponseStatus.OK);

        return moneyTransfersResponse;
    }
}