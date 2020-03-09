package com.terry.home.test.bitcoinwatcher.dto;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceB extends CryptoSource {

    private int lock = 10;

    public SourceB(int id, String uri) {
        super(id, uri);
        addObserver(new CryptoSourceObserver());
    }

    @Override
    public JsonNode readPrice() throws IOException {
        return read(getBaseUrl() + "/simple/price?ids=bitcoin&vs_currencies=USD");
    }

    @Override
    public JsonNode readHistorical(Long start, Long end) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getBaseUrl()).append("/coins/bitcoin/market_chart/range?vs_currency=usd").append("&from=")
            .append(start).append("&to=").append(end);
        return read(sb.toString()).get("prices");
    }

    @Override
    public void update() {
        if (!this.isStatus()) {
            proceed();
            return;
        }

        try {
            JsonNode root = readPrice();
            JsonNode bitcoin = root.get("bitcoin");
            JsonNode usd = bitcoin.get("usd");
            this.setPrice(usd.asDouble());
        } catch (Exception e) {
            lock();
        }
    }

}
