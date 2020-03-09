package com.terry.home.test.bitcoinwatcher.schedule;

import com.terry.home.test.bitcoinwatcher.dto.CryptoSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PollingSchedule {

    @Autowired
    @Qualifier(value = "sourceA")
    private CryptoSource sourceA;

    @Autowired
    @Qualifier(value = "sourceB")
    private CryptoSource sourceB;

    @Autowired
    @Qualifier(value = "sourceC")
    private CryptoSource sourceC;

    @Scheduled(fixedRate = 5000)
    public synchronized void taskA() {
        sourceA.update();
    }

    @Scheduled(fixedRate = 5000)
    public synchronized void taskB() {
        sourceB.update();
    }

    @Scheduled(fixedRate = 5000)
    public synchronized void taskC() {
        sourceC.update();
    }

}
