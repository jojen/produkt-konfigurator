package org.jojen.service;

import com.github.slugify.Slugify;
import com.mortennobel.imagescaling.DimensionConstrain;
import com.mortennobel.imagescaling.ResampleOp;
import org.jojen.model.Image;
import org.jojen.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ImageRepository imageRepository;

    public String getMediaPath(Image i){
        return getMediaDir()+File.separator+i.getId();
    }

    public String getMediaThumbnailPath(Image i, Integer width, Integer height){
        return getMediaThumbsDir()+File.separator+width+"x"+height+"-"+i.getId();
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
        image.setFilename(new Slugify().slugify(file.getOriginalFilename()));
        image.setMimetype(file.getContentType());
        imageRepository.save(image);

        byte[] bytes = file.getBytes();
        Path path = Paths.get(getMediaPath(image));

        Files.write(path, bytes);
    }

    public File createThumbnail(Image i , Integer width, Integer height) throws IOException {
        // TODO only valid sizes should be available
        BufferedImage image = ImageIO.read(new FileInputStream((getMediaPath(i))));
        ResampleOp rop = new ResampleOp(DimensionConstrain.createMaxDimension(width, -1));
        rop.setNumberOfThreads(4);
        BufferedImage b = rop.filter(image, null);
        String thumbnailPath = getMediaThumbnailPath(i,width,height);
        FileOutputStream fos = new FileOutputStream(thumbnailPath);
        ImageIO.write(b, "png", fos);
        return new File(thumbnailPath);
    }
}
