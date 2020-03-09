package com.terry.home.test.bitcoinwatcher.controller;

import com.terry.home.test.bitcoinwatcher.dto.CryptoResponse;
import com.terry.home.test.bitcoinwatcher.dto.CryptoSourceView;
import com.terry.home.test.bitcoinwatcher.dto.ErrorResponse;
import com.terry.home.test.bitcoinwatcher.service.CryptoService;
import com.terry.home.test.bitcoinwatcher.validation.ParameterValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class HomeController {

    @Autowired
    private CryptoService cryptoService;

    @GetMapping({"/price", "/price/"})
    public CryptoResponse price(@RequestParam Integer id, HttpServletRequest request) {
        return new CryptoResponse(request.getRequestURI(), cryptoService.getPrice(id));
    }

    @GetMapping({"/historical/price", "/historical/price/"})
    public CryptoResponse historicalPrice(@RequestParam Integer id, @RequestParam Long start,
        @RequestParam(required = false) Long end, HttpServletRequest request) throws IOException {
        request.getRequestURI();
        end = Optional.ofNullable(end).orElse(System.currentTimeMillis() / 1000);
        List<CryptoSourceView> cryptoSourceViews = cryptoService.getHistoricalPrice(id, start, end).parallelStream()
            .map(d -> new CryptoSourceView(id, d)).collect(Collectors.toList());
        return new CryptoResponse(request.getRequestURI(), cryptoSourceViews);
        // 嘗試使用 WebFlux non-blocking response
        // return Flux.fromStream(cryptoService.getHistoricalPrice(id, start, end).stream()).parallel()
        // .map(d -> new CryptoSourceView(id, d));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorResponse handleException(MissingServletRequestParameterException e, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        map.put(e.getParameterName(), e.getMessage());
        return new ErrorResponse(request.getRequestURI(), map);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponse handleException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        map.put(e.getName(), e.getMessage());
        return new ErrorResponse(request.getRequestURI(), map);
    }

    @ExceptionHandler(ParameterValidationException.class)
    public ErrorResponse handleException(ParameterValidationException e, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        map.put(e.getParameterName(), e.getMessage());
        return new ErrorResponse(request.getRequestURI(), map);
    }

}
