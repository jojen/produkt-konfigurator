package org.jojen.controller;

import org.jojen.model.Product;
import org.jojen.repo.ProductRepository;
import org.jojen.service.HomePageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


/**
 * Created by JGE on 03.04.2017.
 */
@Controller
public class UIController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    HomePageService homePageService;

    @Autowired
    ProductRepository productRepository;


    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("home", homePageService.getHomePage());
        model.addAttribute("products", productRepository.findAll());
        return "home";
    }

    @RequestMapping(value = "/admin")
    public String admin() {
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/{product}")
    public String product(Model model, @PathVariable("product") String product) {
        model.addAttribute("home", homePageService.getHomePage());
        Optional<Product> p = productRepository.findByTitleUrlFriendly(product);
        if (p.isPresent()) {
            model.addAttribute("self", p.get());
            return "product";
        }
        return "error/404";
    }


}
