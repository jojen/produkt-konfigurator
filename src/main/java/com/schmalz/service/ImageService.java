package com.schmalz.service;

import com.github.slugify.Slugify;
import com.schmalz.model.Image;
import com.schmalz.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;


    public String getMediaDir() {
        String path = System.getProperty("user.home") + File.separator + "pk" + File.separator + "media" + File.separator;
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            System.out.print("Could not get media dir");
        }
        return path;
    }

    public String getMediaThumbsDir() {
        String path = getMediaDir() + File.separator + "thumbs";
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            System.out.print("Could not get media thumbs dir");
        }
        return path;
    }

    public void saveImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setFilename(new Slugify().slugify(file.getOriginalFilename()));
        image.setMimetype(file.getContentType());
        imageRepository.save(image);

        byte[] bytes = file.getBytes();
        Path path = Paths.get(getMediaDir() + image.getId().toString());

        Files.write(path, bytes);
    }
}
