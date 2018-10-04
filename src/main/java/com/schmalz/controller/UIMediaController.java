package com.schmalz.controller;

import com.schmalz.model.Image;
import com.schmalz.repo.ImageRepository;
import com.schmalz.service.ImageService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

import static com.schmalz.model.Image.getApplicationDir;


/**
 * Created by JGE on 03.04.2017.
 */
@Controller
@RequestMapping("/media")
public class UIMediaController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageService imageService;


    @RequestMapping(value = "/{id}/{filename}")
    public ResponseEntity media(@PathVariable(value = "id") ObjectId id) throws FileNotFoundException {
        Optional<Image> image = imageRepository.findById(id);

        if (image.isPresent()) {
            String imagepath = imageService.getMediaDir() + id;
            File f = new File(imagepath);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "max-age=31536000");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(f.length())
                    .contentType(image.get().getMediaType())
                    .body(new InputStreamResource(new FileInputStream(f)));
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/thumb/{id}/{width}/{height}/{filename}")
    public ResponseEntity thumb(@PathVariable(value = "id") ObjectId id,
                                @PathVariable(value = "width") Integer width,
                                @PathVariable(value = "height") Integer height) throws FileNotFoundException {
        Optional<Image> image = imageRepository.findById(id);

        if (image.isPresent()) {
            String imagepath = imageService.getMediaThumbsDir() + id + "-" + width + "x" + height;
            File f = new File(imagepath);
            if (!f.exists()) {

            }

            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "max-age=31536000");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(f.length())
                    .contentType(image.get().getMediaType())
                    .body(new InputStreamResource(new FileInputStream(f)));
        }

        return ResponseEntity.notFound().build();
    }

}
