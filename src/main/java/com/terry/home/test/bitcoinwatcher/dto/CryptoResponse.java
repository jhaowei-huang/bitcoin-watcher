package com.terry.home.test.bitcoinwatcher.dto;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import java.io.Serializable;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CryptoResponse extends AbstractResponse {

    private Serializable response;

    private CryptoResponse(String url) {
        this.setStatus(true);
        this.setHttpStatus(HttpStatus.OK.value());
        this.setRequestUrl(url);
        this.setMethod(HttpMethod.GET.name());
    }

    public CryptoResponse(String url, Collection<CryptoSourceView> view) {
        this(url);
        this.setResponse((Serializable) view);
    }

    public CryptoResponse(String url, CryptoSourceView view) {
        this(url);
        this.setResponse(view);
    }

}
