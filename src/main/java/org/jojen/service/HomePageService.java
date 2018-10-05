package org.jojen.service;

import org.jojen.model.HomePage;
import org.jojen.repo.HomePageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
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
