package com.terry.home.test.bitcoinwatcher.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.terry.home.test.bitcoinwatcher.dto.CryptoSource;
import com.terry.home.test.bitcoinwatcher.dto.SourceA;
import com.terry.home.test.bitcoinwatcher.dto.SourceB;
import com.terry.home.test.bitcoinwatcher.dto.SourceC;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;

@Configuration
public class WebConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate build = builder.build();
        build.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().set("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
            return execution.execute(request, body);
        });
        return build;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean(value = "sourceA")
    public CryptoSource sourceA() {
        return new SourceA(1, "https://min-api.cryptocompare.com");
    }

    @Bean(value = "sourceB")
    public CryptoSource sourceB() {
        return new SourceB(2, "https://api.coingecko.com/api/v3");
    }

    @Bean(value = "sourceC")
    public CryptoSource sourceC() {
        return new SourceC(3, "https://api.coinbase.com/v2");
    }

}
