package it.aitho.fabrickonboarding.client;

import it.aitho.fabrickonboarding.dto.AccountBalanceDto;
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
    @Value("${api.fabrick.apiKey}")
    private String apiKey;
    @Value("${api.fabrick.authSchema}")
    private String authSchema;

    public FabrickClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public AccountBalanceDto retrieveAccountBalance(String accountId) {
        HttpEntity<String> entity = new HttpEntity<>(null, fabickHeaders());

        Map<String, String> params = new HashMap<>();
        params.put("accountId", accountId);

        var response = restTemplate.exchange(host + getBalancePath, HttpMethod.GET, entity, AccountBalanceDto.class, params);
        return response.getBody();
    }

    private HttpHeaders fabickHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));
        headers.put("Auth-Schema", List.of(authSchema));
        headers.put("Api-Key", List.of(apiKey));
        return headers;
    }
}
