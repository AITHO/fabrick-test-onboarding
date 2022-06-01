package it.aitho.fabrickonboarding.client;

import it.aitho.fabrickonboarding.dto.accountbalance.AccountBalanceDto;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersDto;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FabrickClient {

    private final RestTemplate restTemplate;

    @Value("${api.fabrick.host}")
    private String host;
    @Value("${api.fabrick.path.get-balance}")
    private String getBalancePath;
    @Value("${api.fabrick.path.create-money-transfers}")
    private String createMoneyTransfersPath;
    @Value("${api.fabrick.apiKey}")
    private String apiKey;
    @Value("${api.fabrick.authSchema}")
    private String authSchema;

    public FabrickClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public AccountBalanceDto retrieveAccountBalance(String accountId) {
        HttpEntity<String> entity = new HttpEntity<>(null, fabrickHeaders());

        Map<String, String> params = new HashMap<>();
        params.put("accountId", accountId);

        var response = restTemplate.exchange(host + getBalancePath, HttpMethod.GET, entity, AccountBalanceDto.class, params);
        return response.getBody();
    }

    public MoneyTransfersResponseDto makeBankTransfer(String accountId, MoneyTransfersDto moneyTransfersDto, String timezone) {
        var httpHeaders = fabrickHeaders();
        httpHeaders.put("X-Time-Zone", List.of(timezone));
        HttpEntity<MoneyTransfersDto> entity = new HttpEntity<>(moneyTransfersDto, httpHeaders);

        Map<String, String> params = new HashMap<>();
        params.put("accountId", accountId);

        var response = restTemplate.exchange(host + createMoneyTransfersPath, HttpMethod.POST, entity, MoneyTransfersResponseDto.class, params);
        return response.getBody();
    }

    private HttpHeaders fabrickHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));
        headers.put("Auth-Schema", List.of(authSchema));
        headers.put("Api-Key", List.of(apiKey));
        return headers;
    }
}
