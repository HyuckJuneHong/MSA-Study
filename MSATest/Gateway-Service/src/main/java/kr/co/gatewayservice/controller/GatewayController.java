package kr.co.gatewayservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/actuator/info")
@RestController
public class GatewayController {


    @GetMapping()
    public String test(){
        return "gateway-test";
    }
}
