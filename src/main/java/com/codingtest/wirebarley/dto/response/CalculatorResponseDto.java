package com.codingtest.wirebarley.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CalculatorResponseDto {

    private String nation;

    private String source;

    private String exchangeRate;

}
