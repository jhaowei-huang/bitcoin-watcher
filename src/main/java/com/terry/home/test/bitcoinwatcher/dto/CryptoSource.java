package com.terry.home.test.bitcoinwatcher.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.Observable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CryptoSource extends Observable {

    private String topic;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected RestTemplate restTemplate;

    private int id;

    private String baseUrl;

    private Double price = null;

    private CryptoSourceView view;

    private boolean status = true;

    private int lock = 10;

    CryptoSource(int id, String baseUrl) {
        this.id = id;
        this.baseUrl = baseUrl;
        this.topic = "/topic/price/" + this.id;
    }

    JsonNode read(String uri) throws IOException {
        String response = restTemplate.getForObject(uri, String.class);
        return objectMapper.readTree(response);
    }

    public abstract void update();

    public abstract JsonNode readPrice() throws IOException;

    public abstract JsonNode readHistorical(Long start, Long end) throws IOException;

    public synchronized void setPrice(Double price) {
        if (price == null) {
            return;
        }

        if (!price.equals(this.price)) {
            this.price = price;
            setChanged();
            notifyObservers();
        }
    }

    protected void lock() {
        this.status = false;
        this.lock = 10;
    }

    protected void unlock() {
        this.status = true;
    }

    protected void proceed() {
        this.lock -= 1;
        if (this.lock < 0) {
            this.status = true;
        }
    }

}
