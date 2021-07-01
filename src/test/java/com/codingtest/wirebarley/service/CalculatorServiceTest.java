package com.codingtest.wirebarley.service;

import com.codingtest.wirebarley.domain.RatioResult;
import com.codingtest.wirebarley.dto.response.CalculatorResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CalculatorServiceTest {

    @Autowired
    private CalculatorService calculatorService;

    @Test
    void getExchangeResult() {
        String nation = "KWR";
        String source = "USD";

        CalculatorResponseDto result = calculatorService.getExchangeResult(nation, source);

        assertThat(result.getNation()).isEqualTo(nation);
        assertThat(result.getSource()).isEqualTo(source);
        assertThat(result.getExchangeRate()).isGreaterThanOrEqualTo("0");
    }

    @Test
    void getExchangeRate() {
        String nation = "KWR";
        String exchangeRate = calculatorService.getExchangeRate(nation);

        assertThat(exchangeRate).isGreaterThanOrEqualTo("0");
    }

    @Test
    void getRatioResult() {
        RatioResult result = calculatorService.getRatioResult();
        assertThat(result).isNotNull();
    }

}