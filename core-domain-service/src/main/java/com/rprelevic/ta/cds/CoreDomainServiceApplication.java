package com.rprelevic.ta.cds;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@EnableCaching
@SpringBootApplication
public class CoreDomainServiceApplication {

    public static void main(String[] args) {
        log.info("Starting {} ...", CoreDomainServiceApplication.class);
        SpringApplication.run(CoreDomainServiceApplication.class);
    }

}
