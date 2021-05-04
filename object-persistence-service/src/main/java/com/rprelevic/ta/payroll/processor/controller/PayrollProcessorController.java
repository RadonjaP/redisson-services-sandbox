package com.rprelevic.ta.payroll.processor.controller;


import com.rprelevic.ta.payroll.processor.service.PayrollProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayrollProcessorController {

    @Autowired
    private PayrollProcessorService payrollProcessorService;

    @GetMapping
    public void process() throws Exception {
        payrollProcessorService.process( );
    }

}
