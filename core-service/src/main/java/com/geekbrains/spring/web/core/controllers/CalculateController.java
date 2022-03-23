package com.geekbrains.spring.web.core.controllers;

import org.springframework.web.bind.annotation.*;
import com.geekbrains.spring.web.core.services.CalculateService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/calculate")
@RequiredArgsConstructor
public class CalculateController {

    @GetMapping("")
    public Double performСomplexСalculation(@RequestParam Double firstNumber,@RequestParam Double secondNumber) {
        CalculateService service = CalculateService.getInstance();
        return service.performСomplexСalculation(firstNumber,secondNumber);
    }
}
