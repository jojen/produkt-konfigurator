package com.schmalz.controller;

import com.schmalz.repo.ProductRepository;
import com.schmalz.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by JGE on 03.04.2017.
 */
@Controller
public class UIController {

    public static final String MESSAGE = "Hello World UI!";

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


}
