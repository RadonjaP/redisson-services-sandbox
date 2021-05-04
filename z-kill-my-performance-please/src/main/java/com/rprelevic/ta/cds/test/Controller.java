package com.rprelevic.ta.cds.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/run")
    public void runOverThem(@RequestParam("delay") Integer delay, @RequestParam("loops") Integer loops) throws InterruptedException {
        log.info("Sharpening sword....");

        int loop = 0;
        while(loop <= loops) {
            restTemplate.execute("http://localhost:8081/process", HttpMethod.GET, null, null);

            Thread.sleep(delay);
            loop = loops == 0 ? 0 : loop+1;
        }
        log.info("Execution finished...");
    }


}
