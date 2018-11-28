package org.jojen.controller;

import org.jojen.model.Product;
import org.jojen.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class ApiController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BuildProperties buildProperties;

    @Autowired
    PriceService priceService;

    @RequestMapping(value = "/build-info", method = RequestMethod.GET)
    public String buildInfo() {
        return buildProperties.get("name") + "<br>" + buildProperties.get("time");
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String validate(@ModelAttribute("self") Product product) {
        return priceService.validate(product);
    }

}
