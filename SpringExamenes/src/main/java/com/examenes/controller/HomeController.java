package com.examenes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenes.configuration.ApplicationContextProvider;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
public class HomeController {

	@GetMapping(value = "/")
    public Object home() {
        return "Hello from MS Examenes";
    }
	@Timed(value = "getPing")
	@ApiOperation(value = "ping para comprobar que el servicio esta up",response = String.class)
	@GetMapping(value = "/ping")
    public Object ping() {
        return "Hello from Examenes ping";
    }
	@GetMapping(value = "/version")
    public Object version() {
        return ApplicationContextProvider.getApplicationContext().getBean("buildInfo");
    }

}
