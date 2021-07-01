package com.codingtest.wirebarley.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
public class RatioResult {

    private boolean success;

    private String terms;

    private String privacy;

    private Long timestamp;

    private String source;

    private Map<String, Double> quotes;

}
