package org.jojen.controller;

import org.jojen.repo.ProductRepository;
import org.jojen.service.HomePageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
        model.addAttribute("homePage", homePageService.getHomePage());
        model.addAttribute("products", productRepository.findAll());
        return "home";
    }

    @RequestMapping(value = "/{product}")
    public String podukt(Model model, @PathVariable("product") String product) {
        model.addAttribute("homePage", homePageService.getHomePage());
        model.addAttribute("self", productRepository.findByTitleUrlFriendly(product).get());
        return "product";
    }


}
