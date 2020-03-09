package com.terry.home.test.bitcoinwatcher.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.terry.home.test.bitcoinwatcher.dto.CryptoSource;
import com.terry.home.test.bitcoinwatcher.dto.CryptoSourceView;
import com.terry.home.test.bitcoinwatcher.validation.ParameterValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CryptoService {

    private static SimpMessagingTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier(value = "sourceA")
    private CryptoSource sourceA;

    @Autowired
    @Qualifier(value = "sourceB")
    private CryptoSource sourceB;

    @Autowired
    @Qualifier(value = "sourceC")
    private CryptoSource sourceC;

    public static void setTemplate(SimpMessagingTemplate template) {
        CryptoService.template = template;
    }

    public static void send(String topic, CryptoSourceView message) {
        if (template != null && message != null) {
            template.convertAndSend(topic, message);
        }
    }

    public CryptoSourceView getPrice(int id) {
        if (id == 1) {
            return sourceA.getView();
        }
        if (id == 2) {
            return sourceB.getView();
        }
        if (id == 3) {
            return sourceC.getView();
        }

        throw new ParameterValidationException("id", "無效的id");
    }

    @SuppressWarnings("unchecked")
    public List<ArrayList<Number>> getHistoricalPrice(int id, long start, long end) throws IOException {

        if (id == 1) {
            return Collections.emptyList();
        }

        if (id == 2) {
            return objectMapper.convertValue(sourceB.readHistorical(start, end), List.class);
        }

        if (id == 3) {
            JsonNode jsonNode = sourceC.readHistorical(start, end);
            List<ArrayList<Number>> list = objectMapper.convertValue(jsonNode, List.class);
            return list.parallelStream().map(i -> {
                ArrayList<Number> arrayList = new ArrayList<>();
                Number number = i.get(0);
                arrayList.add(number.longValue() * 1000);
                arrayList.add(i.get(3));
                return arrayList;
            }).collect(Collectors.toList());
        }

        throw new ParameterValidationException("id", "無效的id");
    }

}
