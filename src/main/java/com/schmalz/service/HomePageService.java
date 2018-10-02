package com.schmalz.service;

import com.schmalz.model.HomePage;
import com.schmalz.repo.HomePageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageService {
    @Autowired
    HomePageRepository homePageRepository;

    public HomePage getHomePage() {
        List<HomePage> list = homePageRepository.findAll();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        // wir legen lazy die erste an
        HomePage homePage = new HomePage();
        homePageRepository.save(homePage);
        return homePage;
    }
}
