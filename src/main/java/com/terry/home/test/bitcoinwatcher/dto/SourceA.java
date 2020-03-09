package com.terry.home.test.bitcoinwatcher.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.terry.home.test.bitcoinwatcher.validation.CurrentSourceInvalidException;
import java.io.IOException;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceA extends CryptoSource {

    public SourceA(int id, String baseUri) {
        super(id, baseUri);
        addObserver(new CryptoSourceObserver());
    }

    @Override
    public JsonNode readPrice() throws IOException {
        return read(getBaseUrl() + "/data/price?fsym=BTC&tsyms=USD");
    }

    @Override
    public JsonNode readHistorical(Long start, Long end) throws IOException {
        return null;
    }

    @Override
    public void update() {
        if (!this.isStatus()) {
            proceed();
            return;
        }

        try {
            JsonNode root = readPrice();
            JsonNode usd = root.get("USD");
            this.setPrice(usd.asDouble());
        } catch (Exception e) {
            lock();
        }
    }

}
