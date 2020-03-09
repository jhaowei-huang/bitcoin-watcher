package com.terry.home.test.bitcoinwatcher.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.terry.home.test.bitcoinwatcher.utils.DateTimeUtils;
import org.springframework.web.client.HttpClientErrorException;
import java.io.IOException;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceC extends CryptoSource {

    private final static String BTC_USD = "/prices/BTC-USD/spot";

    private final static String BTC_JPY = "/prices/BTC-USD/spot";

    private final static String BTC_TWD = "/prices/BTC-USD/spot";

    public SourceC(int id, String baseUri) {
        super(id, baseUri);
        addObserver(new CryptoSourceObserver());
    }

    @Override
    public JsonNode readPrice() throws IOException {
        return read(getBaseUrl() + BTC_USD);
    }

    @Override
    public JsonNode readHistorical(Long start, Long end) throws IOException {
        try {
            return read("https://api.pro.coinbase.com/products/BTC-USD/candles?start="
                + DateTimeUtils.ISO8601StringFormat(new Date(start * 1000)) + "&end="
                + DateTimeUtils.ISO8601StringFormat(new Date(end * 1000)) + "&granularity=300");
        } catch (HttpClientErrorException e) {
            return JsonNodeFactory.instance.arrayNode();
        }

    }

    @Override
    public void update() {
        if (!this.isStatus()) {
            proceed();
            return;
        }
        try {
            JsonNode usd = readPrice().get("data").get("amount");
            this.setPrice(usd.asDouble());
        } catch (Exception e) {
            lock();
        }
    }

}
