package it.aitho.fabrickonboarding.client;

import it.aitho.fabrickonboarding.dto.AccountBalanceDto;
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

    private static final  String BASE_URL = "https://sandbox.platfr.io";
    private static final  String GET_BALANCE_PATH = "/api/gbs/banking/v4.0/accounts/{accountId}/balance";
    private static final  String API_KEY = "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP";

    public FabrickClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public AccountBalanceDto retrieveAccountBalance(String accountId) {

        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));
        headers.put("Auth-Schema", List.of("S2S"));
        headers.put("Api-Key", List.of(API_KEY));

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        Map<String, String> params = new HashMap<>();
        params.put("accountId", accountId);

        var response = restTemplate.exchange(BASE_URL+ GET_BALANCE_PATH, HttpMethod.GET, entity, AccountBalanceDto.class, params);
        return response.getBody();
    }
}
