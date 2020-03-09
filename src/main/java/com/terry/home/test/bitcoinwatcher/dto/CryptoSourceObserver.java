package com.terry.home.test.bitcoinwatcher.dto;

import com.terry.home.test.bitcoinwatcher.service.CryptoService;
import java.util.Observable;
import java.util.Observer;

public class CryptoSourceObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        CryptoSource cryptoSource = (CryptoSource) o;
        cryptoSource.setView(new CryptoSourceView(cryptoSource));
        CryptoService.send(cryptoSource.getTopic(), cryptoSource.getView());
    }

}
