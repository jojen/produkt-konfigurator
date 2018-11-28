package org.jojen.service;

import com.github.slugify.Slugify;
import com.mortennobel.imagescaling.DimensionConstrain;
import com.mortennobel.imagescaling.ResampleOp;
import org.apache.commons.io.FilenameUtils;

import org.jojen.model.Image;
import org.jojen.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    @Value("${image.thumbnail.s}")
    Integer sizeS;

    @Value("${image.thumbnail.m}")
    Integer sizeM;

    @Value("${image.thumbnail.l}")
    Integer sizeL;

    @Autowired
    ImageRepository imageRepository;

    public String getMediaPath(Image i) {
        return getMediaDir() + File.separator + i.getId();
    }

    public String getMediaThumbnailPath(Image i, String size) {
        return getMediaThumbsDir() + File.separator + i.getId() + "-" + size;
    }

    private String getMediaDir() {
        String path = System.getProperty("user.home") + File.separator + "pk" + File.separator + "media" + File.separator;
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            System.out.print("Could not get media dir");
        }
        return path;
    }

    private String getMediaThumbsDir() {
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
        String filename = file.getOriginalFilename();
        String extention = FilenameUtils.getExtension(file.getOriginalFilename());
        image.setExtention(extention);
        filename = filename.replace("." + extention, "");
        image.setFilename(new Slugify().slugify(filename));
        image.setMimetype(file.getContentType());


        imageRepository.save(image);

        byte[] bytes = file.getBytes();
        Path path = Paths.get(getMediaPath(image));

        Files.write(path, bytes);
    }

    public File getThumbnail(Image i, String size) throws IOException {
        File f = new File(getMediaThumbnailPath(i, size));
        if (!f.exists()) {
            BufferedImage image = ImageIO.read(new FileInputStream((getMediaPath(i))));
            ResampleOp rop = new ResampleOp(DimensionConstrain.createMaxDimension(9999, resolveSize(size)));
            rop.setNumberOfThreads(4);
            BufferedImage b = rop.filter(image, null);
            String thumbnailPath = getMediaThumbnailPath(i, size);
            FileOutputStream fos = new FileOutputStream(thumbnailPath);
            ImageIO.write(b, "png", fos);
        }
        return f;
    }

    private Integer resolveSize(String size) {
        switch (size) {
            case "m":
                return sizeM;
            case "l":
                return sizeL;
            default:
                return sizeS;
        }
    }

    public void delete(Image image) throws IOException {
        getThumbnail(image, "s").delete();
        getThumbnail(image, "m").delete();
        getThumbnail(image, "l").delete();
        new File(getMediaPath(image)).delete();
        imageRepository.delete(image);
    }
}
