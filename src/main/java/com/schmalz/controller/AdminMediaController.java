package com.schmalz.controller;

import com.schmalz.repo.ImageRepository;
import com.schmalz.service.HomePageService;
import com.schmalz.service.ImageService;
import com.unboundid.util.json.JSONObject;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Controller
@RequestMapping("/admin")
public class AdminMediaController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    HomePageService homePageService;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/media", method = RequestMethod.GET)
    public String page(Model model) {
        model.addAttribute("homePage", homePageService.getHomePage());
        model.addAttribute("images", imageRepository.findAll());
        return "admin/media";
    }

    @RequestMapping(value = "/media/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") ObjectId id) {
        imageRepository.deleteById(id);
        return "redirect:/admin/products";
    }

    @RequestMapping(value = "/media/update", method = RequestMethod.POST)
    public @ResponseBody
    Object upload(@RequestParam("file") MultipartFile file,
                  HttpServletRequest request) {
        if (file.isEmpty()) {
            request.setAttribute("message", "Please select a file to upload");
            return "uploadStatus";
        }
        try {
            imageService.saveImage(file);
            request.setAttribute("message", "You have successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            log.error("Could not get upload media file {}", file.getOriginalFilename());
        }
        return getSuccessMessage().toString();
    }


    private JSONObject getSuccessMessage() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject("{\"success\":true}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
