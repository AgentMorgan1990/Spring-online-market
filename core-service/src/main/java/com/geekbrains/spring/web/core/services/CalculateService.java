package com.geekbrains.spring.web.core.services;


public class CalculateService {
    private static volatile CalculateService instance;

    private CalculateService() {

    }

    public static CalculateService getInstance() {
        CalculateService localInstance = instance;
        if (localInstance == null) {
            synchronized (CalculateService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CalculateService();
                }
            }
        }
        return localInstance;
    }

        //Например, можем посчитать скидку, которая зависит от нескольких параметров
    public Double performСomplexСalculation(Double firstNumber, Double secondNumber) {
        //рандомные вычисления
        return firstNumber * (secondNumber + 12) / 256 + 14 * 0.98;
    }


}