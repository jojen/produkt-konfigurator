package com.schmalz.controller;

import com.github.slugify.Slugify;
import com.schmalz.model.Product;
import com.schmalz.repo.ProductRepository;
import com.schmalz.service.HomePageService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminProductController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    HomePageService homePageService;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String page(Model model) {
        model.addAttribute("homePage", homePageService.getHomePage());
        model.addAttribute("products", productRepository.findAll());
        return "admin/products";
    }


    @RequestMapping(value = "/product/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       @RequestParam(value = "id", required = false) ObjectId id) {
        if (id != null) {
            model.addAttribute("self", productRepository.findById(id).get());
        } else {
            model.addAttribute("self", new Product());
        }
        model.addAttribute("homePage", homePageService.getHomePage());
        return "admin/product.edit";
    }

    @RequestMapping(value = "/product/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") ObjectId id) {
        productRepository.deleteById(id);
        return "redirect:/admin/products";
    }

    @RequestMapping(value = "/product/update", method = RequestMethod.POST)
    public String update(Model model, @ModelAttribute("self") @Validated Product p) {
        p.setTitleUrlFriendly(new Slugify().slugify(p.getTitle()));
        productRepository.save(p);
        return "redirect:/admin/products";
    }
}
