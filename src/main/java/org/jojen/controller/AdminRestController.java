package org.jojen.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.slugify.Slugify;
import org.jojen.model.Product;
import org.jojen.repo.ImageRepository;
import org.jojen.repo.ProductRepository;
import org.jojen.service.HomePageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class AdminRestController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/product/attribute/schema.json", method = RequestMethod.GET)
    public JsonNode attributeSchema(Model model, @RequestParam(value = "id") String id)  {
        Product p = productRepository.findById(id).get();
        return p.getAttributeSchema();
    }
}
