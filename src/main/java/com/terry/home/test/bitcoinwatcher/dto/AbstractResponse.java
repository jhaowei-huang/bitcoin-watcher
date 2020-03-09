/*
 * Copyright (c) 2019. Create by Terry Huang (黃昭維)
 */

package com.terry.home.test.bitcoinwatcher.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractResponse implements Serializable {

    private Boolean status;

    private Integer httpStatus;

    private String requestUrl;

    private String method;

    private long timestamp = System.currentTimeMillis();

}
