package com.terry.home.test.bitcoinwatcher.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message implements Serializable {

    @JsonIgnore
    private String messageUuid = UUID.randomUUID().toString();

    // 唯一識別 uuid
    private String uuid;

    // 大頭貼
    private String code;

    // 顏色
    private String color;

    // 名字
    private String name;

    // 訊息內容
    private String content;

    private long time = System.currentTimeMillis();

}
