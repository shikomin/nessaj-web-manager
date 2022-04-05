package com.nessaj.manager.controller;

import com.nessaj.manager.common.constants.ManagerConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/healthcheck")
    @ResponseBody
    public String healthCheck(){
        return ManagerConstant.MANAGER_HEALTHCHECK_OK;
    }

}
