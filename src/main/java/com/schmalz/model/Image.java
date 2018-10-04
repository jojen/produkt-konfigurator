package com.schmalz.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.MediaType;
import org.springframework.util.MimeType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Document
@Getter
@Setter
@ToString
public class Image {
    @Id
    private ObjectId id;
    private String filename;
    private String mimetype;

    public MediaType getMediaType() {
        if (getMimetype() != null && getMimetype().contains("/")) {
            return new MediaType(getMimetype().split("/")[0], getMimetype().split("/")[1]);
        }
        return null;
    }
}
