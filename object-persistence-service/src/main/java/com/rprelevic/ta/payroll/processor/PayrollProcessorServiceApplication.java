package com.rprelevic.ta.payroll.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PayrollProcessorServiceApplication {

    public static void main(String[] args) {
        log.info("Starting {} ...", PayrollProcessorServiceApplication.class);
        SpringApplication.run(PayrollProcessorServiceApplication.class);
    }

}
