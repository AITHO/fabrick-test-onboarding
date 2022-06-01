package it.aitho.fabrickonboarding.client;

import it.aitho.fabrickonboarding.dto.accountbalance.AccountBalanceDto;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersDto;
import it.aitho.fabrickonboarding.dto.moneytransfers.MoneyTransfersResponse;
import it.aitho.fabrickonboarding.dto.transactions.GetTransactionsResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FabrickClient {

    private static final String ACCOUNT_ID = "accountId";
    private final RestTemplate restTemplate;

    @Value("${api.fabrick.host}")
    private String host;
    @Value("${api.fabrick.path.get-balance}")
    private String getBalancePath;
    @Value("${api.fabrick.path.create-money-transfers}")
    private String createMoneyTransfersPath;
    @Value("${api.fabrick.path.get-transactions}")
    private String getTransactionsPath;
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
        params.put(ACCOUNT_ID, accountId);

        var response = restTemplate.exchange(host + getBalancePath, HttpMethod.GET, entity, AccountBalanceDto.class, params);
        return response.getBody();
    }

    public MoneyTransfersResponse makeBankTransfer(String accountId, MoneyTransfersDto moneyTransfersDto, String timezone) {
        var httpHeaders = fabrickHeaders();
        httpHeaders.put("X-Time-Zone", List.of(timezone));
        HttpEntity<MoneyTransfersDto> entity = new HttpEntity<>(moneyTransfersDto, httpHeaders);

        Map<String, String> params = new HashMap<>();
        params.put(ACCOUNT_ID, accountId);
        var response = restTemplate.exchange(host + createMoneyTransfersPath, HttpMethod.POST, entity, MoneyTransfersResponse.class, params);
        return response.getBody();
    }

    private HttpHeaders fabrickHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));
        headers.put("Auth-Schema", List.of(authSchema));
        headers.put("Api-Key", List.of(apiKey));
        return headers;
    }

    public GetTransactionsResponseDto retrieveAccountTransactions(String accountId, String fromAccountingDate, String toAccountingDate) {
        HttpEntity<String> entity = new HttpEntity<>(null, fabrickHeaders());

        Map<String, String> params = new HashMap<>();
        params.put(ACCOUNT_ID, accountId);
        params.put("fromAccountingDate", fromAccountingDate);
        params.put("toAccountingDate", toAccountingDate);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(host + getTransactionsPath)
                .queryParam("fromAccountingDate", "{fromAccountingDate}")
                .queryParam("toAccountingDate", "{toAccountingDate}")
                .encode()
                .toUriString();
        var response = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, GetTransactionsResponseDto.class, params);
        return response.getBody();
    }
}
