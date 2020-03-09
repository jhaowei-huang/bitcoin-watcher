package com.terry.home.test.bitcoinwatcher.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CryptoSourceView implements Serializable {

    private int id;

    private Double price;

    private long time;

    public CryptoSourceView(CryptoSource cryptoSource) {
        this.id = cryptoSource.getId();
        this.price = cryptoSource.getPrice();
        this.time = System.currentTimeMillis();
    }

    public CryptoSourceView(int id, ArrayList<Number> flux) {
        this.id = id;
        this.time = flux.get(0).longValue();
        this.price = BigDecimal.valueOf(flux.get(1).doubleValue()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}
