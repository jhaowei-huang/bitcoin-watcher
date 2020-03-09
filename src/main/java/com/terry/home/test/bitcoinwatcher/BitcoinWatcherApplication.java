package com.terry.home.test.bitcoinwatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BitcoinWatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitcoinWatcherApplication.class, args);
    }

}
