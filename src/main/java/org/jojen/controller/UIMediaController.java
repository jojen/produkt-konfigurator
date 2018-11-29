package org.jojen.controller;

import org.jojen.model.Image;
import org.jojen.repo.ImageRepository;
import org.jojen.service.ImageService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;


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
    public ResponseEntity media(@PathVariable(value = "id") String id) throws FileNotFoundException {
        Optional<Image> image = imageRepository.findById(id);

        if (image.isPresent()) {
            File f = new File(imageService.getMediaPath(image.get()));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "max-age=31536000");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(f.length())
                    .contentType(image.get().getMediaType(false))
                    .body(new InputStreamResource(new FileInputStream(f)));
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/thumb/{id}/{size}/{filename}")
    public ResponseEntity thumb(@PathVariable(value = "id") String id,
                                @PathVariable(value = "size") String size) throws IOException {
        Optional<Image> image = imageRepository.findById(id);

        if (image.isPresent()) {
            File f = imageService.getThumbnail(image.get(), size);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "max-age=31536000");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(f.length())
                    .contentType(image.get().getMediaType(true))
                    .body(new InputStreamResource(new FileInputStream(f)));
        }

        return ResponseEntity.notFound().build();
    }

}
