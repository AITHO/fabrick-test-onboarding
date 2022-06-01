package it.aitho.fabrickonboarding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.aitho.fabrickonboarding.dto.moneytransfers.Creditor;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersDto;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersPayloadDto;
import it.aitho.fabrickonboarding.service.MoneyTransfersService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FabrickMoneyTransfersController.class)
class FabrickMoneyTransfersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MoneyTransfersService moneyTransfersService;

    @Test
    public void doMoneyTransferTest() throws Exception {
        Mockito.when(moneyTransfersService.makeBankTransfer(anyString(), any(), anyString()))
                .thenReturn(mockMoneyTransfersPayloadDto());

        ObjectMapper mapper = new ObjectMapper();
        var jsonRequest = mapper.writeValueAsString(mockMoneyTransfersDto());

        mockMvc.perform(post("/money-transfers/123456")
                .contentType(APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("test")));
    }

    private MoneyTransfersDto mockMoneyTransfersDto() {
        MoneyTransfersDto moneyTransfersDto = new MoneyTransfersDto();
        moneyTransfersDto.setCreditor(new Creditor());
        moneyTransfersDto.setDescription("test");
        moneyTransfersDto.setAmount(22.22);
        moneyTransfersDto.setCurrency("EUR");

        return moneyTransfersDto;
    }

    private MoneyTransfersPayloadDto mockMoneyTransfersPayloadDto() {
        MoneyTransfersPayloadDto mockMoneyTransfersDto = new MoneyTransfersPayloadDto();
        mockMoneyTransfersDto.setDescription("test");
        return mockMoneyTransfersDto;
    }
}
