package com.schmalz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ApiController {
    @Autowired
    BuildProperties buildProperties;

    @RequestMapping(value = "/build-info", method = RequestMethod.GET)
    public String buildInfo() {
        return buildProperties.get("name") + "<br>" + buildProperties.get("time");
    }
}
