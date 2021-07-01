package com.codingtest.wirebarley.controller;

import com.codingtest.wirebarley.service.CalculatorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/calculator/form")
    public void form(@RequestParam(value = "nation", defaultValue = "KRW") String nation, @RequestParam(value = "source", defaultValue = "USD") String source, Model model) {
        log.info("nation:::::" + nation);
        log.info("source:::::" + source);

        model.addAttribute("dto", calculatorService.getExchangeResult(nation, source));

    }

}
